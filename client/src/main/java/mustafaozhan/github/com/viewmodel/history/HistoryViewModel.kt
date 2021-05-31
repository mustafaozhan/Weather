package mustafaozhan.github.com.viewmodel.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mustafaozhan.github.com.data.db.HistoryRepository
import mustafaozhan.github.com.model.History
import mustafaozhan.github.com.ui.history.HistoryEffect
import mustafaozhan.github.com.ui.history.HistoryEvent
import mustafaozhan.github.com.ui.history.HistoryState
import mustafaozhan.github.com.ui.history.HistoryState.Companion.update

class HistoryViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel(), HistoryEvent {

    // region SEED
    private var _state = MutableStateFlow(HistoryState())
    var state = _state.asStateFlow()

    private var _effect = MutableSharedFlow<HistoryEffect>()
    var effect = _effect.asSharedFlow()

    fun getEvent() = this as HistoryEvent
    // endregion

    init {
        viewModelScope.launch {
            historyRepository.collectHistories()
                .collect {
                    _state.update(it)
                }
        }
    }

    // region events
    override fun onItemClick(history: History) {
        viewModelScope.launch {
            _effect.emit(HistoryEffect.OpenForecast(history.text))
        }
    }
    // endregion
}
