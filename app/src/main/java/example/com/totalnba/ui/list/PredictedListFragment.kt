package example.com.totalnba.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
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
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

class PredictedListFragment : BaseFragment<PredictedListViewModel>(),
    OnDateSelectedListener {

    override fun provideViewModel() = getViewModelFromFactory()
    private lateinit var binding: FragmentPredictedListBinding

    lateinit var adapter: PredictedMatchAdapter
    lateinit var recyclerView: RecyclerView
    private val bag = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_predicted_list, container, false)
        val view: View = binding.root
        binding.viewmodel = provideViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.predictionRecyclerView
        binding.calendarViewSingle.setOnDateChangedListener(this)
        binding.calendarViewSingle.setDateSelected(CalendarDay.today(),true)

        attachUI()

        loadData()

    }

    private fun loadData() {
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

        navigator?.add(PredictedDetailFragment.newInstance(homeTeam!!, awayTeam!!))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        bag.clear()
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        val format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
        val formatter = format1.format(date.date.atStartOfDay())

        viewModel.filterDay.onNext(formatter)
    }

}