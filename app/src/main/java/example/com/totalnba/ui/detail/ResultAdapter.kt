package example.com.totalnba.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.data.network.model.Result
import example.com.totalnba.databinding.ResultListItemBinding
import example.com.totalnba.util.DateUtil.formatShortDate

class ResultAdapter(): RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    var results: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResultListItemBinding.inflate(inflater)
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = results[position]
        holder.configureWith(result)
    }

    override fun getItemCount(): Int = results.size

    fun updateData(data: List<Result>) {
        this.results = data
        notifyDataSetChanged()
    }

    inner class ResultViewHolder(private val binding: ResultListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun configureWith(result: Result) {
            binding.teamHome.text = result.homeName
            binding.teamAway.text = result.awayName
            binding.scoreHome.text = result.homeScore.toString()
            binding.scoreAway.text = result.awayScore.toString()
        }
    }
}