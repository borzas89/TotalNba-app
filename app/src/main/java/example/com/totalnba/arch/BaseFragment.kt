package example.com.totalnba.arch

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProviders


abstract class BaseFragment< VM : BaseViewModel> : InjectedFragment() {

    protected lateinit var viewModel: VM

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = provideViewModel()

    }

    abstract fun provideViewModel(): VM


}

inline fun <F : BaseFragment<VM>,  reified VM : BaseViewModel> F.getViewModelFromFactory(): VM {
    return ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
}