package example.com.totalnba.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.com.totalnba.R
import example.com.totalnba.arch.BaseFragment
import example.com.totalnba.arch.getViewModelFromFactory
import example.com.totalnba.arch.navigator
import example.com.totalnba.databinding.FragmentPredictedListBinding
import example.com.totalnba.ui.detail.PredictedDetailFragment
import example.com.totalnba.util.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PredictedListFragment: BaseFragment<PredictedListViewModel>(), AdapterView.OnItemSelectedListener{

    override fun provideViewModel() = getViewModelFromFactory()
    private lateinit var binding : FragmentPredictedListBinding

    lateinit var adapter: PredictedMatchAdapter
    lateinit var recyclerView: RecyclerView
    private val bag = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding  = DataBindingUtil.inflate(
                inflater, R.layout.fragment_predicted_list, container, false)
        val view: View = binding.root
        binding.viewmodel = provideViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.predictionRecyclerView

        creatingWeekSelector()

        attachUI()

        loadData()
    }

    private fun creatingWeekSelector() {
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item, viewModel.getWeeksList()
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.weekSpinner.adapter = arrayAdapter
        binding.weekSpinner.setSelection(1)
        binding.weekSpinner.onItemSelectedListener = this

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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        viewModel.filterWeek.onNext("Week " + position)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onDestroyView(){
        super.onDestroyView()
        bag.clear()
    }

}