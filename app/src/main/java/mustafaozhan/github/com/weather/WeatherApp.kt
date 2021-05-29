package mustafaozhan.github.com.weather

import android.app.Application
import mustafaozhan.github.com.di.koin.clientModule
import mustafaozhan.github.com.di.koin.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)
            modules(clientModule)
            modules(dataModules)
        }
    }
}
