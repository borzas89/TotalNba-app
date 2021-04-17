package example.com.totalnba.ui.list

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.R
import example.com.totalnba.data.network.model.PredictedMatch
import example.com.totalnba.util.backgroundResolverId
import example.com.totalnba.util.imageResolverId
import example.com.totalnba.util.roundingDouble

class PredictedMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val frameLayout: FrameLayout

    val predictedMatchTitle: TextView

    val imageHome: ImageView

    val imageAway: ImageView

    val predictedScore: TextView

    val awayScore: TextView

    val homeScore: TextView


    init {
       frameLayout = itemView.findViewById(R.id.list_frame_item)
       predictedMatchTitle = itemView.findViewById(R.id.predictedMatchTitle)
       predictedScore = itemView.findViewById(R.id.predictedScore)
       imageHome = itemView.findViewById(R.id.imageHome)
       imageAway= itemView.findViewById(R.id.imageAway)
       homeScore = itemView.findViewById(R.id.homeScore)
       awayScore = itemView.findViewById(R.id.awayScore)

    }

    fun configureWith(predictions: PredictedMatch) {

        frameLayout.setBackgroundResource(backgroundResolverId(predictions.homeTeam.toString()))
        predictedMatchTitle.text = predictions.homeTeam + " vs " + predictions.awayTeam
        homeScore.text = predictions.predictedHomeScore.toString()
        awayScore.text = predictions.predictedAwayScore.toString()

        predictedScore.text = predictions.predictedScore?.let { roundingDouble(it) }

        imageHome.setImageResource(imageResolverId(predictions.homeTeam.toString()))
        imageAway.setImageResource(imageResolverId(predictions.awayTeam.toString()))

    }


}