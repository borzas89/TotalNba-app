package example.com.totalnba.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.R
import example.com.totalnba.data.network.model.Result

class ResultAdapter(): RecyclerView.Adapter<ResultViewHolder>() {
    var results: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_list_item, parent, false)
        val viewHolder = ResultViewHolder(view)


        return viewHolder
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = results.get(position)
        holder.configureWith(result)
    }

    override fun getItemCount(): Int = results.size

    fun updateData(data: List<Result>) {
        this.results = data
        notifyDataSetChanged()
    }

}