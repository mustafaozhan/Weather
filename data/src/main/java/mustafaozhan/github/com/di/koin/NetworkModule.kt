package mustafaozhan.github.com.di.koin

import com.squareup.moshi.Moshi
import mustafaozhan.github.com.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT: Long = 3

val networkModule = module {
    single { provideMoshi() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}

private fun provideMoshi(): Moshi = Moshi.Builder().build()

private fun provideHttpClient() = OkHttpClient.Builder()
    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
    .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
    .build()

private fun provideRetrofit(
    moshi: Moshi,
    httpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_RUL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(httpClient)
    .build()
