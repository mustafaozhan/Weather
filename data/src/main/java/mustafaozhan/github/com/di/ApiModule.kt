package mustafaozhan.github.com.di

import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.data.api.ApiFactory
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.data.api.ApiRepositoryImpl
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    internal fun providesApiRepositoryImpl(
        apiFactory: ApiFactory
    ): ApiRepositoryImpl = ApiRepositoryImpl(apiFactory)

    @Provides
    @Singleton
    internal fun providesApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository = apiRepositoryImpl
}
