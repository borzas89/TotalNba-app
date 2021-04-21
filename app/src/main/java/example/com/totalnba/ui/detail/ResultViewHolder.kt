package example.com.totalnba.ui.detail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.R
import example.com.totalnba.data.network.model.PredictedMatch
import example.com.totalnba.data.network.model.Result
import example.com.totalnba.util.backgroundResolverId
import example.com.totalnba.util.imageResolverId
import example.com.totalnba.util.roundingDouble

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val teamHome: TextView
    val teamAway: TextView

    val awayScore: TextView

    val homeScore: TextView


    init {

        teamHome = itemView.findViewById(R.id.teamHome)
        teamAway = itemView.findViewById(R.id.teamAway)
        homeScore = itemView.findViewById(R.id.scoreHome)
        awayScore = itemView.findViewById(R.id.scoreAway)

    }

    fun configureWith(result: Result) {
        teamHome.text = result.homeName
        teamAway.text = result.awayName
        homeScore.text = result.homeScore.toString()
        awayScore.text = result.awayScore.toString()

    }
}