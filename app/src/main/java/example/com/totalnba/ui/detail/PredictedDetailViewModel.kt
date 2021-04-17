package example.com.totalnba.ui.detail

import androidx.databinding.ObservableField
import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.network.TotalNbaApi
import example.com.totalnba.data.network.model.Overall
import example.com.totalnba.data.network.model.Result
import example.com.totalnba.util.disposedBy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PredictedDetailViewModel  @Inject constructor(
    private val api: TotalNbaApi

) : BaseViewModel() {

    val overallList = BehaviorRelay.createDefault(listOf<Overall>())

    val homeTeam = ObservableField<String>()
    val awayTeam = ObservableField<String>()

    val homeOverall = ObservableField<Overall>()
    val awayOverall = ObservableField<Overall>()

    val matchTitle = ObservableField<String>()

    val homeResults : List<Result> = listOf<Result>()

    private val bag = CompositeDisposable()

    fun getOverallsByTeams(homeName: String, awayName: String) {
        api.getOverallByTeams(homeName,awayName)
            .subscribeOn(Schedulers.io())
            .subscribe { overall -> overallList.accept(overall)
               homeOverall.set(overall[0])
               awayOverall.set(overall[1])
            }
            .disposedBy(bag)
    }


    fun getResultsByTeamName(teamName: String) : Flowable<List<Result>> {
    return api.getResultsByTeamName(teamName)
            .subscribeOn(Schedulers.io())
            .toFlowable()
    }


    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }



}