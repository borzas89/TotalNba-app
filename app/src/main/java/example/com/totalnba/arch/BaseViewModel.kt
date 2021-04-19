package example.com.totalnba.arch

import androidx.annotation.CallSuper
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import example.com.totalnba.util.wrap
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor

open class BaseViewModel  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val loadingProcessor = BehaviorProcessor.create<Boolean>()

    val isLoading = loadingProcessor.value ?: false

    val isLoadingStuff = ObservableField<Boolean>()

    val isLoadingFlowable
        get() = loadingProcessor.wrap()

    @CallSuper
    open fun showLoader() = loadingProcessor.onNext(true )

    @CallSuper
    open fun stopLoader() = loadingProcessor.onNext(false)


    protected fun <U> applySingleTransformers(): SingleTransformer<U, U> {
        return SingleTransformer { upstream ->
            upstream
                    .doOnSubscribe {
                        isLoadingStuff.set(true)
                        showLoader() }
                    .doFinally {
                        isLoadingStuff.set(false)
                        stopLoader() }
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}