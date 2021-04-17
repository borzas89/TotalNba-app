package example.com.totalnba.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.R
import example.com.totalnba.arch.BaseFragment
import example.com.totalnba.arch.getViewModelFromFactory
import example.com.totalnba.arch.navigator
import example.com.totalnba.ui.detail.PredictedDetailFragment
import example.com.totalnba.util.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PredictedListFragment: BaseFragment<PredictedListViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

    lateinit var adapter: PredictedMatchAdapter
    lateinit var recyclerView: RecyclerView
    private val bag = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_predicted_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.predictionRecyclerView);

        attachUI()

        loadData()
    }

    private fun loadData(){
        viewModel.predictions.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { list -> adapter.updateData(list) }
            .disposedBy(bag)
    }

    private fun attachUI() {
        val linearLayoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(dividerItemDecoration)

        initializeListView()
    }

    private fun initializeListView() {
        adapter = PredictedMatchAdapter() { view, position -> rowTapped(position) }
        recyclerView.adapter = adapter
    }

    private fun rowTapped(position: Int) {
        println(adapter.predictions.get(position))

        val homeTeam = adapter.predictions.get(position).homeTeam
        val awayTeam = adapter.predictions.get(position).awayTeam

        navigator?.add(PredictedDetailFragment.newInstance(homeTeam!!,awayTeam!!))
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.clear()
    }
}