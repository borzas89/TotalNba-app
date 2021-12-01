package example.com.totalnba.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import example.com.totalnba.R
import example.com.totalnba.arch.BaseFragment
import example.com.totalnba.arch.applyArgs
import example.com.totalnba.arch.getViewModelFromFactory
import example.com.totalnba.arch.requireString
import example.com.totalnba.databinding.FragmentPredictedDetailBinding
import example.com.totalnba.util.disposedBy
import example.com.totalnba.util.imageResolverId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PredictedDetailFragment: BaseFragment<PredictedDetailViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    private lateinit var binding : FragmentPredictedDetailBinding

    lateinit var adapter: ResultAdapter
    lateinit var recyclerView: RecyclerView

    private val bag = CompositeDisposable()

    companion object {
        private const val HOME_TEAM_NAME = "HOME_TEAM_NAME"
        private const val AWAY_TEAM_NAME = "AWAY_TEAM_NAME"

        private var homeTeamName: String = ""
        private var awayTeamName: String = ""

        fun newInstance(homeTeamName: String, awayTeamName: String): PredictedDetailFragment {
            return PredictedDetailFragment().applyArgs {
                putString(HOME_TEAM_NAME, homeTeamName)
                putString(AWAY_TEAM_NAME, awayTeamName)
            }
        }
    }

    private fun initArguments() {
        homeTeamName = requireArguments().requireString(HOME_TEAM_NAME)
        awayTeamName = requireArguments().requireString(AWAY_TEAM_NAME)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {

        binding  = DataBindingUtil.inflate(
                inflater, R.layout.fragment_predicted_detail, container, false)
        val view: View = binding.root
        binding.viewmodel = provideViewModel()
        return view
    }

    override fun onStart() {
        super.onStart()

        viewModel.awayTeam.set(awayTeamName)
        viewModel.homeTeam.set(homeTeamName)
        viewModel.matchTitle.set("$awayTeamName vs $homeTeamName")

        viewModel.getOverallsByTeams(homeTeamName, awayTeamName)

        viewModel.getHomeResults(homeTeamName)
        viewModel.getAwayResults(awayTeamName)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArguments()

        recyclerView = binding.resultRecyclerView

        attachUI()

        binding.imageHome.setImageResource(imageResolverId(homeTeamName))
        binding.imageAway.setImageResource(imageResolverId(awayTeamName))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Away team"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Home team"))

        binding.tabLayout.getTabAt(0)!!.select()
        loadAwayResults()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0 -> loadAwayResults()
                    1 -> loadHomeResults()
                    else -> loadHomeResults()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    private fun loadHomeResults(){
        viewModel.homeResultList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{ homeResults -> adapter.updateData(homeResults)}
                .disposedBy(bag)

    }

    private fun loadAwayResults(){
        viewModel.awayResultList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{ awayResults -> adapter.updateData(awayResults)}
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
        adapter = ResultAdapter()
        recyclerView.adapter = adapter
    }

    override fun onDestroyView(){
        super.onDestroyView()
        bag.clear()
    }
}