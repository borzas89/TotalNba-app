package example.com.totalnba.ui.list

import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.network.TotalNbaApi
import example.com.totalnba.data.network.model.PredictedMatch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class PredictedListViewModel  @Inject constructor(
    private val api: TotalNbaApi

) : BaseViewModel() {

    val predictions = BehaviorRelay.createDefault(listOf<PredictedMatch>())
    val filterWeek : BehaviorSubject<String> = BehaviorSubject.createDefault("Week 1")
    private val bag = CompositeDisposable()

    fun getWeeksList(): List<String> {
        return listOf("Week 0", "Week 1", "Week 2", "Week 3", "Week 4", "Week 5",
            "Week 6", "Week 7", "Week 8", "Week 9", "Week 10",
            "Week 11", "Week 12", "Week 13", "Week 14", "Week 15",
            "Week 16", "Week 17", "Week 18")
    }

    init {
        filterWeek.subscribe {
            gettingMatchesByWeek()
        }.addTo(bag)
    }

    fun gettingMatchesByWeek(){
        api.getPredictedMatchesByWeek(filterWeek.value!!).observeOn(AndroidSchedulers.mainThread())
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