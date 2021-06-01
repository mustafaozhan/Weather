package mustafaozhan.github.com.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.navigation.fragment.navArgs
import com.github.mustafaozhan.basemob.fragment.BaseVBFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import mustafaozhan.github.com.model.WeatherStatus
import mustafaozhan.github.com.ui.databinding.FragmentDetailBinding
import mustafaozhan.github.com.util.format
import mustafaozhan.github.com.util.getWeatherIconByName
import mustafaozhan.github.com.util.gone
import mustafaozhan.github.com.util.showDetailOrHide
import mustafaozhan.github.com.viewmodel.detail.DetailEffect
import mustafaozhan.github.com.viewmodel.detail.DetailViewModel
import javax.inject.Inject

class DetailFragment : BaseVBFragment<FragmentDetailBinding>() {

    @Inject
    lateinit var detailViewModel: DetailViewModel

    private val args: DetailFragmentArgs by navArgs()

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

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
        detailViewModel.setData(args.forecast)
    }

    private fun initViews() = with(binding) {
        toolbarFragmentDetail.setOnClickListener { detailViewModel.event.onBackClick() }
    }

    private fun observeStates() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        detailViewModel.state.collect {
            with(it) {
                with(binding) {
                    imgForecast.getWeatherIconByName(it.imgName)
                    txtDate.text = date.format()
                    txtDescription.showDetailOrHide(R.string.txt_description, description)
                    txtTemp.showDetailOrHide(R.string.txt_temperature, temperature)
                    txtMinTemp.showDetailOrHide(R.string.txt_min_degree, minDegree)
                    txtMaxTemp.showDetailOrHide(R.string.txt_max_degree, maxDegree)
                    txtHumidity.showDetailOrHide(R.string.txt_humidity, humidity)
                    txtWind.showDetailOrHide(R.string.txt_wind, wind)
                    txtSeaLevel.showDetailOrHide(R.string.txt_sea_level, seaLevel)
                    txtVisibility.showDetailOrHide(R.string.txt_visibility, visibility)
                    txtPressure.showDetailOrHide(R.string.txt_pressure, pressure)

                    when (it.status) {
                        WeatherStatus.HOT -> txtStatus.text = getString(R.string.txt_hot)
                        WeatherStatus.COLD -> txtStatus.text = getString(R.string.txt_cold)
                        WeatherStatus.UNKNOWN -> txtStatus.gone()
                    }
                }
            }
        }
    }

    private fun observeEffect() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        detailViewModel.effect.collect { viewEffect ->
            when (viewEffect) {
                DetailEffect.Back -> getBaseActivity()?.onBackPressed()
            }
        }
    }
}
