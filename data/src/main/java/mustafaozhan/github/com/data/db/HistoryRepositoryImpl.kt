package mustafaozhan.github.com.data.db

import kotlinx.coroutines.flow.map
import mustafaozhan.github.com.data.mappers.toEntity
import mustafaozhan.github.com.data.mappers.toModel
import mustafaozhan.github.com.model.History

class HistoryRepositoryImpl(private val historyDao: HistoryDao) : HistoryRepository {
    override fun collectHistories() = historyDao.collectHistories()
        .map { historyList ->
            historyList.map { history ->
                history.toModel()
            }
        }

    override suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history.toEntity())
    }
}
