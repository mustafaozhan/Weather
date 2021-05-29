package mustafaozhan.github.com.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.github.mustafaozhan.basemob.fragment.BaseVBFragment
import kotlinx.coroutines.flow.collect
import mustafaozhan.github.com.ui.databinding.FragmentWeatherBinding
import mustafaozhan.github.com.weather.WeatherViewModel

class WeatherFragment : BaseVBFragment<FragmentWeatherBinding>() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun getViewBinding() = FragmentWeatherBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()
    }

    private fun observeStates() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        weatherViewModel.state.collect {
            with(it) {
                binding.txt.text = helloWorldText
            }
        }
    }
}
