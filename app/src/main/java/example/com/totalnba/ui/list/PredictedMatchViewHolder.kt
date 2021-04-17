package example.com.totalnba.ui.list

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.R
import example.com.totalnba.data.PredictedMatch

class PredictedMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val context = itemView.context

    val titleTextView: TextView


    init {

        titleTextView = itemView.findViewById(R.id.predictedMatch)

    }

    fun configureWith(predictions: PredictedMatch) {

        titleTextView.text = predictions.homeTeam

    }


}