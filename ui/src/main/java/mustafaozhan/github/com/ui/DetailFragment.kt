package mustafaozhan.github.com.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.navigation.fragment.navArgs
import com.github.mustafaozhan.basemob.fragment.BaseVBFragment
import kotlinx.coroutines.flow.collect
import mustafaozhan.github.com.ui.databinding.FragmentDetailBinding
import mustafaozhan.github.com.util.format
import mustafaozhan.github.com.util.getWeatherIconByName
import mustafaozhan.github.com.util.showDetailOrHide
import mustafaozhan.github.com.viewmodel.detail.DetailEffect
import mustafaozhan.github.com.viewmodel.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseVBFragment<FragmentDetailBinding>() {
    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModel()

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

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
                    txtMinTemp.showDetailOrHide(R.string.txt_min_degree, minDegree)
                    txtMaxTemp.showDetailOrHide(R.string.txt_max_degree, maxDegree)
                    txtHumidity.showDetailOrHide(R.string.txt_humidity, humidity)
                    txtWind.showDetailOrHide(R.string.txt_wind, wind)
                    txtSeaLevel.showDetailOrHide(R.string.txt_sea_level, seaLevel)
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
