package mustafaozhan.github.com.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.recyclerview.widget.DiffUtil
import com.github.mustafaozhan.basemob.adapter.BaseVBRecyclerViewAdapter
import com.github.mustafaozhan.basemob.fragment.BaseVBFragment
import kotlinx.coroutines.flow.collect
import mustafaozhan.github.com.forecast.ForecastEvent
import mustafaozhan.github.com.forecast.ForecastViewModel
import mustafaozhan.github.com.model.Forecast
import mustafaozhan.github.com.ui.databinding.FragmentForecastBinding
import mustafaozhan.github.com.ui.databinding.ItemForecastBinding
import mustafaozhan.github.com.util.format
import mustafaozhan.github.com.util.getWeatherIconByName
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : BaseVBFragment<FragmentForecastBinding>() {

    private val forecastViewModel: ForecastViewModel by viewModel()
    private lateinit var forecastAdapter: ForecastAdapter

    override fun getViewBinding() = FragmentForecastBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeStates()
    }

    private fun initViews() {
        forecastAdapter = ForecastAdapter(forecastViewModel.event)
        binding.recyclerViewForecast.adapter = forecastAdapter
    }

    private fun observeStates() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        forecastViewModel.state.collect {
            with(it) {
                forecastAdapter.submitList(it.forecastList)

                binding.txtCityName.text = if (cityName.isEmpty() || country.isEmpty()) {
                    cityName
                } else {
                    getString(R.string.txt_location, cityName, country)
                }
            }
        }
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
        }
    }

    class ForecastDiffer : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast) = false
        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast) = false
    }
}
