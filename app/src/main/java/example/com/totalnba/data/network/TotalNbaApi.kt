package example.com.totalnba.data.network

import example.com.totalnba.data.network.model.Overall
import example.com.totalnba.data.network.model.PredictedMatch
import example.com.totalnba.data.network.model.Result
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface TotalNbaApi {

//    @GET("nba_predicted_scores.json")
    @GET("api/predicted-matches/")
    fun getPredictedMatches(): Single<List<PredictedMatch>>

//    @GET("nba_overalls.json")
    @GET("api/api/results/all-overalls/")
    fun getAverageOveralls(): Single<List<Overall>>

    // http://localhost:8080/api/overalls?homeName=Detroit%20Pistons&awayName=Boston%20Celtics
    @GET("api/overalls")
    fun getOverallByTeams(@Query("homeName") homeName: String,
                          @Query("awayName") awayName: String): Single<List<Overall>>

    @GET("api/results/homeTeam")
    fun getHomeResultsByTeamName(@Query("homeTeam") homeName: String): Single<List<Result>>

    @GET("api/results/awayTeam")
    fun getAwayResultsByTeamName(@Query("awayTeam") awayName: String): Single<List<Result>>

    @GET("api/results/all-results-by-team")
    fun getResultsByTeamName(@Query("teamName") teamName: String): Single<List<Result>>

}