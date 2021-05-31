package mustafaozhan.github.com.data.db

import kotlinx.coroutines.flow.Flow
import mustafaozhan.github.com.model.History

interface HistoryRepository {
    fun collectHistories(): Flow<List<History>>
    suspend fun insertHistory(history: History)
}
