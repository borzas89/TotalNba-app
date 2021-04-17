package example.com.totalnba.data.network.model


import com.google.gson.annotations.SerializedName;

class PredictedMatch {

    @SerializedName("homeTeam")
    val homeTeam: String? = null

    @SerializedName("awayTeam")
    val awayTeam: String? = null

    @SerializedName("predictedScore")
    val predictedScore: Double? = null

    @SerializedName("predictedHomeScore")
    val predictedHomeScore: Double? = null

    @SerializedName("predictedAwayScore")
    val predictedAwayScore: Double? = null
}