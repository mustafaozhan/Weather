package mustafaozhan.github.com.di.koin

import mustafaozhan.github.com.api.ApiFactory
import mustafaozhan.github.com.api.ApiRepository
import org.koin.dsl.module

val dataModule = module {
    factory { ApiFactory() }
    single { ApiRepository(get()) }
}
