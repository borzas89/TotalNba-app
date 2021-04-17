package example.com.totalnba.data.network

import example.com.totalnba.data.network.model.Overall
import example.com.totalnba.data.network.model.PredictedMatch
import io.reactivex.Single
import retrofit2.http.GET

interface TotalNbaApi {

    @GET("nba_predicted_scores.json")
//    @GET("api/predicted-matches/")
    fun getPredictedMatches(): Single<List<PredictedMatch>>

    @GET("nba_overalls.json")
//    @GET("api/api/results/all-overalls/")
    fun getAverageOveralls(): Single<List<Overall>>

}