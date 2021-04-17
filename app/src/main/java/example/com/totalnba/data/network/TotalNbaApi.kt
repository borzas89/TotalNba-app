package example.com.totalnba.data.network

import example.com.totalnba.data.PredictedMatch
import io.reactivex.Single
import retrofit2.http.GET

interface TotalNbaApi {

    @GET("nba_predicted_scores.json")
    fun getPredictedMatches(): Single<List<PredictedMatch>>
}