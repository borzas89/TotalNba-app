package example.com.totalnba.ui.list

import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.network.model.PredictedMatch
import example.com.totalnba.data.network.TotalNbaApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PredictedListViewModel  @Inject constructor(
    private val api: TotalNbaApi

) : BaseViewModel() {

    val predictions = BehaviorRelay.createDefault(listOf<PredictedMatch>())
    private val bag = CompositeDisposable()

    init {
        api.getPredictedMatches().observeOn(AndroidSchedulers.mainThread())
                .compose(applySingleTransformers())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = { result ->
                            when (result) {
                                result -> predictions.accept(result)
                            }

                        }, onError = {

                }
        )
    }


    override fun onCleared() {
        super.onCleared()
        bag.clear()

    }

}