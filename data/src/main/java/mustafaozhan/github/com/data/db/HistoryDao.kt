package mustafaozhan.github.com.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mustafaozhan.github.com.data.entity.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun collectHistories(): Flow<MutableList<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History)
}
