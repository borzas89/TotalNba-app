package example.com.totalnba.di

import dagger.Component
import example.com.totalnba.arch.InjectedFragment
import example.com.totalnba.data.network.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelModule::class,
    ApplicationModule::class,
    NetworkModule::class

])
interface AppComponent {

    fun inject(injectedFragment: InjectedFragment)

}