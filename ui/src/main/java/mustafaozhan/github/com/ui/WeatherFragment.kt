package mustafaozhan.github.com.ui

import com.github.mustafaozhan.basemob.fragment.BaseVBFragment
import mustafaozhan.github.com.ui.databinding.FragmentWeatherBinding

internal class WeatherFragment : BaseVBFragment<FragmentWeatherBinding>() {
    override fun getViewBinding() = FragmentWeatherBinding.inflate(layoutInflater)
}
