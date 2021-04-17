package example.com.totalnba.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import example.com.totalnba.ui.detail.PredictedDetailViewModel
import example.com.totalnba.ui.list.PredictedListViewModel
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PredictedListViewModel::class)
    abstract fun bindPredictedListViewModel(listViewModel: PredictedListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PredictedDetailViewModel::class)
    abstract fun bindPredictedDetailViewModel(detailViewModel: PredictedDetailViewModel): ViewModel

}