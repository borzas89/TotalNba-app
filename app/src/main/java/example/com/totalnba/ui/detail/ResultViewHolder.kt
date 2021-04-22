package example.com.totalnba.ui.detail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.R
import example.com.totalnba.data.network.model.Result
import java.text.SimpleDateFormat
import java.util.*

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val eventDate: TextView
    val teamHome: TextView
    val teamAway: TextView
    val awayScore: TextView
    val homeScore: TextView


    init {

        eventDate = itemView.findViewById(R.id.eventDate)
        teamHome = itemView.findViewById(R.id.teamHome)
        teamAway = itemView.findViewById(R.id.teamAway)
        homeScore = itemView.findViewById(R.id.scoreHome)
        awayScore = itemView.findViewById(R.id.scoreAway)

    }

    fun configureWith(result: Result) {
//        eventDate.text = result!!.matchTime
        teamHome.text = result.homeName
        teamAway.text = result.awayName
        homeScore.text = result.homeScore.toString()
        awayScore.text = result.awayScore.toString()

    }
}