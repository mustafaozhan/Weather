package mustafaozhan.github.com.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.github.mustafaozhan.basemob.adapter.BaseVBRecyclerViewAdapter
import com.github.mustafaozhan.basemob.fragment.BaseVBFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import mustafaozhan.github.com.model.Forecast
import mustafaozhan.github.com.ui.databinding.FragmentForecastBinding
import mustafaozhan.github.com.ui.databinding.ItemForecastBinding
import mustafaozhan.github.com.util.format
import mustafaozhan.github.com.util.getWeatherIconByName
import mustafaozhan.github.com.util.showLoading
import mustafaozhan.github.com.util.showSnack
import mustafaozhan.github.com.viewmodel.forecast.ForecastEffect
import mustafaozhan.github.com.viewmodel.forecast.ForecastEvent
import mustafaozhan.github.com.viewmodel.forecast.ForecastViewModel
import javax.inject.Inject

class ForecastFragment : BaseVBFragment<FragmentForecastBinding>() {

    @Inject
    lateinit var forecastViewModel: ForecastViewModel

    private val args: ForecastFragmentArgs by navArgs()

    private lateinit var forecastAdapter: ForecastAdapter

    override fun getViewBinding() = FragmentForecastBinding.inflate(layoutInflater)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        initViews()
        observeStates()
        observeEffect()
    }

    private fun setData() {
        forecastViewModel.setData(args.history)
    }

    private fun initViews() {
        forecastAdapter = ForecastAdapter(forecastViewModel.event)
        setSpanByOrientation(resources.configuration.orientation)
        with(binding) {
            recyclerViewForecast.adapter = forecastAdapter
            layoutForecastToolbar.searchView.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(newText: String): Boolean {
                    forecastViewModel.event.onQueryChange(newText)
                    return true
                }

                override fun onQueryTextChange(newText: String) = false
            })
            layoutForecastToolbar.imgHistory.setOnClickListener {
                forecastViewModel.event.onHistoryClick()
            }
        }
    }

    private fun observeStates() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        forecastViewModel.state.collect {
            with(it) {
                forecastAdapter.submitList(it.forecastList)

                binding.loadingView.showLoading(it.isLoading)

                binding.txtCityName.text = if (cityName.isEmpty() || country.isEmpty()) {
                    cityName
                } else {
                    getString(R.string.txt_location, cityName, country)
                }
            }
        }
    }

    private fun observeEffect() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        forecastViewModel.effect.collect { viewEffect ->
            when (viewEffect) {
                ForecastEffect.Error -> showSnack(
                    requireView(),
                    R.string.txt_unexpected_error
                )
                ForecastEffect.CityNotFound -> showSnack(
                    requireView(),
                    R.string.txt_city_not_found
                )
                is ForecastEffect.OpenDetailScreen -> navigate(
                    R.id.forecastFragment,
                    ForecastFragmentDirections.actionForecastFragmentToDetailFragment(viewEffect.forecast)
                )
                ForecastEffect.OpenHistory -> navigate(
                    R.id.forecastFragment,
                    ForecastFragmentDirections.actionForecastFragmentToHistoryFragment()
                )
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setSpanByOrientation(newConfig.orientation)
    }

    private fun setSpanByOrientation(orientation: Int) {
        binding.recyclerViewForecast.layoutManager = GridLayoutManager(
            requireContext(),
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) SPAN_LANDSCAPE else SPAN_PORTRAIT
        )
    }

    companion object {
        internal const val SPAN_PORTRAIT = 2
        internal const val SPAN_LANDSCAPE = 4
    }
}

class ForecastAdapter(
    private val event: ForecastEvent
) : BaseVBRecyclerViewAdapter<Forecast, ItemForecastBinding>(ForecastDiffer()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CalculatorVBViewHolder(
        ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class CalculatorVBViewHolder(itemBinding: ItemForecastBinding) :
        BaseVBViewHolder<Forecast, ItemForecastBinding>(itemBinding) {

        override fun onItemBind(item: Forecast) = with(itemBinding) {
            imgForecast.getWeatherIconByName(item.weather?.firstOrNull()?.icon)
            txtTemperature.text = txtTemperature.context.getString(
                R.string.txt_temperature,
                item.main?.temp?.toInt()?.toString()
            )
            txtFellsLike.text = txtFellsLike.context.getString(
                R.string.txt_feels_like,
                item.main?.feelsLike?.toInt()?.toString()
            )
            txtWeatherStatus.text = item.weather?.firstOrNull()?.main
            txtWeatherTime.text = item.dtTxt?.format()

            itemBinding.root.setOnClickListener { event.onItemClick(item) }
        }
    }

    class ForecastDiffer : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast) = false
        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast) = false
    }
}
