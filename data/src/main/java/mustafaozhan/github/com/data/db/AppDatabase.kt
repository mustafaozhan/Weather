package mustafaozhan.github.com.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mustafaozhan.github.com.data.entity.History

@Suppress("MagicNumber")
@Database(entities = [(History::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        private const val DATABASE_NAME = "application_database.sqlite"

        internal fun createAppDatabase(
            context: Context
        ): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
