package example.com.totalnba.ui.list

import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.network.model.PredictedMatch
import example.com.totalnba.data.network.TotalNbaApi
import example.com.totalnba.util.disposedBy
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PredictedListViewModel  @Inject constructor(
    private val api: TotalNbaApi

) : BaseViewModel() {

    internal val predictions = BehaviorRelay.createDefault(listOf<PredictedMatch>())
    private val bag = CompositeDisposable()

    init {
        gettingDatas()
    }

    fun getAllPredictions(): Single<List<PredictedMatch>> {
        return api.getPredictedMatches()
    }

    fun gettingDatas(){
        getAllPredictions().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { list -> predictions.accept(list) }
            .disposedBy(bag)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()

    }

}