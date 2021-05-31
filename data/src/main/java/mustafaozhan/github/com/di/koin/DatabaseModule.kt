package mustafaozhan.github.com.di.koin

import mustafaozhan.github.com.data.db.AppDatabase
import mustafaozhan.github.com.data.db.HistoryRepository
import mustafaozhan.github.com.data.db.HistoryRepositoryImpl
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.createAppDatabase(get()) }
    single { get<AppDatabase>().historyDao() }

    single<HistoryRepository> { HistoryRepositoryImpl(get()) }
}
