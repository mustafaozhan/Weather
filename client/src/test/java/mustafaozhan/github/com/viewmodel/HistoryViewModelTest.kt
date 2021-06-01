package mustafaozhan.github.com.viewmodel

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import mustafaozhan.github.com.data.db.HistoryRepository
import mustafaozhan.github.com.model.History
import mustafaozhan.github.com.ui.history.HistoryEffect
import mustafaozhan.github.com.util.after
import mustafaozhan.github.com.util.before
import mustafaozhan.github.com.viewmodel.history.HistoryViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HistoryViewModelTest : BaseViewModelTest<HistoryViewModel>() {
    override lateinit var viewModel: HistoryViewModel

    @MockK
    private lateinit var historyRepository: HistoryRepository

    private val mockHistory1 = History("first")
    private val mockHistory2 = History("second")
    private val mockHistoryList = listOf(mockHistory1, mockHistory2)

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coEvery {
            historyRepository.collectHistories()
        } coAnswers {
            flow {
                emit(mockHistoryList)
            }
        }
        viewModel = HistoryViewModel(historyRepository)
    }

    @Test
    fun `collecting database objects when viewModel initialised updates UI`() {
        testCoroutineRule.runBlockingTest {
            Assert.assertTrue(mockHistoryList == viewModel.state.value.historyList)
        }
    }

    // event
    @Test
    fun onItemClick() {
        viewModel.effect.before {
            viewModel.getEvent().onItemClick(mockHistory1)
        }.after {
            Assert.assertTrue(it == HistoryEffect.OpenForecast(mockHistory1.text))
        }
    }
}
