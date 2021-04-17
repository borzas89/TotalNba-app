package example.com.totalnba.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("match_id")
    val matchId: String? = null

    @SerializedName("league_id")
    val leagueId: String? = null

    @SerializedName("league_name")
    val leagueName: Any? = null

    @SerializedName("quarter_count")
    val quarterCount: Int? = null

    @SerializedName("match_time")
     val matchTime: Int? = null

    @SerializedName("status")
    val status: Int? = null

    @SerializedName("home_name")
    val homeName: String? = null

    @SerializedName("away_name")
    val awayName: String? = null

    @SerializedName("home_score")
    val homeScore: Int? = null

    @SerializedName("away_score")
    val awayScore: Int? = null

    @SerializedName("explain")
    val explain: String? = null

    @SerializedName("neutral")
    val neutral: Boolean? = null
}