package example.com.totalnba.ui.detail

import androidx.databinding.ObservableField
import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.network.TotalNbaApi
import example.com.totalnba.data.network.model.Overall
import example.com.totalnba.data.network.model.Result
import example.com.totalnba.util.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PredictedDetailViewModel @Inject constructor(
    private val api: TotalNbaApi
    ) : BaseViewModel() {

    val overallList = BehaviorRelay.createDefault(listOf<Overall>())

    val homeResultList = BehaviorRelay.createDefault(listOf<Result>())
    val awayResultList = BehaviorRelay.createDefault(listOf<Result>())

    val homeTeam = ObservableField<String>()
    val awayTeam = ObservableField<String>()

    val homeOverall = ObservableField<Overall>()
    val awayOverall = ObservableField<Overall>()

    val matchTitle = ObservableField<String>()
    val errorTitle = ObservableField<String>()

    fun getOverallsByTeams(homeName: String, awayName: String) {
        api.getOverallByTeams(homeName, awayName)
            .subscribeOn(Schedulers.io())
            .subscribe { overall ->
                overallList.accept(overall)
                homeOverall.set(overall[0])
                awayOverall.set(overall[1])
            }
            .disposedBy(disposables)
    }

    fun getHomeResults(homeTeam: String) {
        api.getResultsByTeamName(homeTeam).observeOn(AndroidSchedulers.mainThread())
            .compose(applySingleTransformers())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { result ->
                    when (result) {
                        result -> homeResultList.accept(result)
                    }

                }, onError = {
                    errorTitle.set("Something went wrong, try again later..")
                }
            )

    }

    fun getAwayResults(awayTeam: String) {
        api.getResultsByTeamName(awayTeam).observeOn(AndroidSchedulers.mainThread())
            .compose(applySingleTransformers())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { result ->
                    when (result) {
                        result -> awayResultList.accept(result)
                    }

                }, onError = {

                }
            )
    }
}