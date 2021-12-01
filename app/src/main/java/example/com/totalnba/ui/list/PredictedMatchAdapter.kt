package example.com.totalnba.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.R
import example.com.totalnba.data.network.model.PredictedMatch
import example.com.totalnba.ui.detail.PredictedDetailFragment
import example.com.totalnba.util.backgroundResolverId
import example.com.totalnba.util.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

typealias ItemClickedlambda = (v: View, position: Int) -> Unit

class PredictedMatchAdapter(var onItemClicked: ItemClickedlambda): RecyclerView.Adapter<PredictedMatchViewHolder>() {

    var predictions: List<PredictedMatch> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictedMatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.predicted_list_item, parent, false)
        val viewHolder = PredictedMatchViewHolder(view)

        view.setOnClickListener { v -> onItemClicked(v, viewHolder.adapterPosition) }

        return viewHolder
    }

    override fun onBindViewHolder(holder: PredictedMatchViewHolder, position: Int) {
        val prediction = predictions.get(position)
        holder.configureWith(prediction)
    }

    override fun getItemCount(): Int = predictions.size

    fun updateData(data: List<PredictedMatch>) {
        this.predictions = data
        notifyDataSetChanged()
    }

}