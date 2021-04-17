package example.com.totalnba

import android.app.Application
import example.com.totalnba.di.AppComponent
import example.com.totalnba.di.ApplicationModule
import example.com.totalnba.di.DaggerAppComponent
import timber.log.Timber

lateinit var injector: AppComponent
class TotalNbaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        injector = DaggerAppComponent.builder()
            .build()
    }

}