package example.com.totalnba.ui.list

import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.PredictedMatch
import example.com.totalnba.data.network.TotalNbaApi
import io.reactivex.Single
import javax.inject.Inject

class PredictedListViewModel  @Inject constructor(
    private val api: TotalNbaApi

) : BaseViewModel() {

    internal val predictions = BehaviorRelay.createDefault(listOf<PredictedMatch>())

    fun getAllPredictions(): Single<List<PredictedMatch>> {
        return api.getPredictedMatches()
    }


}