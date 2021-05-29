package mustafaozhan.github.com.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.github.mustafaozhan.basemob.fragment.BaseVBFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import mustafaozhan.github.com.ui.databinding.FragmentWeatherBinding
import mustafaozhan.github.com.weather.WeatherViewModel
import javax.inject.Inject

class WeatherFragment : BaseVBFragment<FragmentWeatherBinding>() {

    @Inject
    lateinit var weatherViewModel: WeatherViewModel

    override fun getViewBinding() = FragmentWeatherBinding.inflate(layoutInflater)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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
