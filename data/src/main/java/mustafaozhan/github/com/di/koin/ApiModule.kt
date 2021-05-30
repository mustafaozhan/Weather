package mustafaozhan.github.com.di.koin

import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.data.api.ApiRepositoryImpl
import mustafaozhan.github.com.data.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    factory<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single<ApiRepository> { ApiRepositoryImpl(get()) }
}
