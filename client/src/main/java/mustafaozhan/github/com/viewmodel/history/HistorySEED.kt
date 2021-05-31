package mustafaozhan.github.com.ui.history

import kotlinx.coroutines.flow.MutableStateFlow
import mustafaozhan.github.com.model.History

data class HistoryState(
    val historyList: List<History> = listOf()
) {
    companion object {
        fun MutableStateFlow<HistoryState>.update(
            historyList: List<History> = value.historyList
        ) {
            value = value.copy(
                historyList = historyList
            )
        }
    }
}

interface HistoryEvent {
    fun onItemClick(history: History)
}

sealed class HistoryEffect {
    data class OpenForecast(val text: String) : HistoryEffect()
}
