package mustafaozhan.github.com.viewmodel

import io.mockk.MockKAnnotations
import mustafaozhan.github.com.model.Forecast
import mustafaozhan.github.com.model.Main
import mustafaozhan.github.com.model.Weather
import mustafaozhan.github.com.model.Wind
import mustafaozhan.github.com.util.after
import mustafaozhan.github.com.util.before
import mustafaozhan.github.com.viewmodel.detail.DetailEffect
import mustafaozhan.github.com.viewmodel.detail.DetailViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailViewModelTest : BaseViewModelTest<DetailViewModel>() {
    override lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = DetailViewModel()
    }

    @Test
    fun `setting data updates the UI`() {
        val mockWeather = Weather(
            icon = "someIcon",
            description = "someDescription"
        )
        val mockMain = Main(
            tempMin = 10.0,
            tempMax = 20.0,
            humidity = 50,
            seaLevel = 1000
        )
        val mockWind = Wind(speed = 100.0)
        val mockDate = "SomeDate"

        val mockForecast = Forecast(
            weather = listOf(mockWeather),
            main = mockMain,
            wind = mockWind,
            dtTxt = mockDate
        )

        viewModel.setData(mockForecast)

        Assert.assertTrue(viewModel.data.forecast == mockForecast)

        with(viewModel.state.value) {
            Assert.assertTrue(imgName == mockWeather.icon)
            Assert.assertTrue(description == mockWeather.description)
            Assert.assertTrue(date == mockDate)
            Assert.assertTrue(minDegree == mockMain.tempMin?.toString())
            Assert.assertTrue(maxDegree == mockMain.tempMax?.toString())
            Assert.assertTrue(humidity == mockMain.humidity?.toString())
            Assert.assertTrue(seaLevel == mockMain.seaLevel?.toString())
            Assert.assertTrue(wind == mockWind.speed?.toString())
        }
    }

    // event
    @Test
    fun onBackClick() {
        viewModel.effect.before {
            viewModel.event.onBackClick()
        }.after {
            Assert.assertTrue(it == DetailEffect.Back)
        }
    }
}
