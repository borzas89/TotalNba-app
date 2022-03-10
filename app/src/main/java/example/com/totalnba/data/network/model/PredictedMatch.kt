package example.com.totalnba.data.network.model

import com.google.gson.annotations.SerializedName;
import java.util.Date

class PredictedMatch {

    @SerializedName("homeTeamName")
    val homeTeam: String? = null

    @SerializedName("awayTeamName")
    val awayTeam: String? = null

    @SerializedName("homeTeamAlias")
    val homeAlias: String? = null

    @SerializedName("awayTeamAlias")
    val awayAlias: String? = null

    @SerializedName("predictedScore")
    val predictedScore: Double? = null

    @SerializedName("predictedHomeScore")
    val predictedHomeScore: Double? = null

    @SerializedName("predictedAwayScore")
    val predictedAwayScore: Double? = null

    @SerializedName("predictedTotal")
    val predictedTotal: Double? = null

    @SerializedName("spread")
    val spread: Double? = null

    @SerializedName("weekName")
    val weekName: String? = null

    @SerializedName("weekNumber")
    val weekNumber: Int? = null

    @SerializedName("matchDate")
    val matchDate: Date? = null

}