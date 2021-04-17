package example.com.totalnba.data.network.model

import com.google.gson.annotations.SerializedName

class Overall {

    @SerializedName("teamName")
    val teamName: String? = null

    @SerializedName("overall")
    val overall: Double? = null

    @SerializedName("awayOverall")
    val awayOverall: Double? = null

    @SerializedName("homeOverall")
    val homeOverall: Double? = null

    @SerializedName("teamAvg")
    val teamAvg: Double? = null

    @SerializedName("teamHomeAvg")
    val teamHomeAvg: Double? = null

    @SerializedName("teamAwayAvg")
    val teamAwayAvg: Double? = null

}