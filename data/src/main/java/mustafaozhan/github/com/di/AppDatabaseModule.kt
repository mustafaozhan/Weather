package mustafaozhan.github.com.di

import android.content.Context
import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.data.db.AppDatabase
import mustafaozhan.github.com.data.db.HistoryDao
import mustafaozhan.github.com.data.db.HistoryRepository
import mustafaozhan.github.com.data.db.HistoryRepositoryImpl
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Provides
    @Singleton
    internal fun providesHistoryDao(database: AppDatabase) =
        database.historyDao()

    @Provides
    @Singleton
    internal fun providesAppDatabase(
        applicationContext: Context
    ): AppDatabase = AppDatabase.createAppDatabase(applicationContext)

    @Provides
    @Singleton
    internal fun providesHistoryRepositoryImpl(
        historyDao: HistoryDao
    ): HistoryRepositoryImpl = HistoryRepositoryImpl(historyDao)

    @Provides
    @Singleton
    internal fun providesHistoryRepository(
        historyRepositoryImpl: HistoryRepositoryImpl
    ): HistoryRepository = historyRepositoryImpl
}
