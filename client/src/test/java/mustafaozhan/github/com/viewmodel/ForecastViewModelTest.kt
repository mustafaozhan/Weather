package mustafaozhan.github.com.viewmodel

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.data.db.HistoryRepository
import mustafaozhan.github.com.error.HttpRequestException
import mustafaozhan.github.com.model.City
import mustafaozhan.github.com.model.Forecast
import mustafaozhan.github.com.model.ForecastResponse
import mustafaozhan.github.com.model.History
import mustafaozhan.github.com.util.Result
import mustafaozhan.github.com.util.after
import mustafaozhan.github.com.util.before
import mustafaozhan.github.com.viewmodel.forecast.ForecastData
import mustafaozhan.github.com.viewmodel.forecast.ForecastEffect
import mustafaozhan.github.com.viewmodel.forecast.ForecastViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ForecastViewModelTest : BaseViewModelTest<ForecastViewModel>() {
    override lateinit var viewModel: ForecastViewModel

    @MockK
    private lateinit var apiRepository: ApiRepository

    @MockK
    private lateinit var historyRepository: HistoryRepository

    private val mockCity = "Berlin"
    private val mockHistory = History(mockCity)
    private val mockForecast = Forecast()
    private val mockResponse = ForecastResponse(
        city = City(country = "DE", name = "Berlin"),
        list = listOf(mockForecast),
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coEvery {
            historyRepository.insertHistory(mockHistory)
        } returns Unit

        viewModel = ForecastViewModel(apiRepository, historyRepository)
    }

    @Test
    fun `show city not found error when api response 404`() {
        coEvery {
            apiRepository.getForecast(mockCity)
        } coAnswers {
            Result.Error(HttpRequestException("", "", Throwable(), 404))
        }

        viewModel.effect.before {
            viewModel.event.onQueryChange(mockCity)
        }.after {
            Assert.assertTrue(it == ForecastEffect.CityNotFound)
        }
    }

    @Test
    fun `show generic error when api throw an exception`() {
        coEvery {
            apiRepository.getForecast(mockCity)
        } coAnswers {
            Result.Error(NullPointerException())
        }

        viewModel.effect.before {
            viewModel.event.onQueryChange(mockCity)
        }.after {
            Assert.assertTrue(it == ForecastEffect.Error)
        }
    }

    @Test
    fun `update state when api call successful`() {
        coEvery {
            apiRepository.getForecast(mockCity)
        } coAnswers {
            Result.Success(mockResponse)
        }

        testCoroutineRule.runBlockingTest {

            viewModel.event.onQueryChange(mockCity)

            viewModel.state.firstOrNull().let {
                Assert.assertEquals(mockResponse.city?.name, it?.cityName)
                Assert.assertEquals(mockResponse.city?.country, it?.country)
                Assert.assertEquals(mockResponse.list, it?.forecastList)
            }
        }
    }

    @Test
    fun `use default query if data set to null`() {
        coEvery {
            apiRepository.getForecast(mockCity)
        } returns (Result.Success(mockResponse))

        viewModel.setData(null)
        Assert.assertTrue(viewModel.data.query == ForecastData.DEFAULT_QUERY)
    }

    @Test
    fun `use provided data if data set properly`() {
        val someDifferentText = "something"
        coEvery {
            historyRepository.insertHistory(History(someDifferentText))
        } returns Unit

        coEvery {
            apiRepository.getForecast(someDifferentText)
        } returns (Result.Success(ForecastResponse()))

        viewModel.setData("something")
        Assert.assertTrue(viewModel.data.query == someDifferentText)
    }

    // event
    @Test
    fun onQueryChange() {
        coEvery {
            apiRepository.getForecast(mockCity)
        } returns (Result.Success(mockResponse))

        testCoroutineRule.runBlockingTest {
            viewModel.event.onQueryChange(mockCity)

            Assert.assertEquals(viewModel.data.query, mockCity)
        }
    }

    @Test
    fun onItemClick() {
        viewModel.effect.before {
            viewModel.event.onItemClick(mockForecast)
        }.after {
            Assert.assertTrue(it == ForecastEffect.OpenDetailScreen(mockForecast))
        }
    }

    @Test
    fun onHistoryClick() {
        viewModel.effect.before {
            viewModel.event.onHistoryClick()
        }.after {
            Assert.assertTrue(it == ForecastEffect.OpenHistory)
        }
    }
}
