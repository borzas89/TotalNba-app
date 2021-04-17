package example.com.totalnba.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import example.com.totalnba.R
import example.com.totalnba.arch.BaseFragment
import example.com.totalnba.arch.applyArgs
import example.com.totalnba.arch.getViewModelFromFactory
import example.com.totalnba.arch.requireString
import example.com.totalnba.databinding.FragmentPredictedDetailBinding
import example.com.totalnba.util.backgroundResolverId
import example.com.totalnba.util.disposedBy
import example.com.totalnba.util.imageResolverId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PredictedDetailFragment: BaseFragment<PredictedDetailViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    private lateinit var binding : FragmentPredictedDetailBinding

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

        viewModel.homeTeam.set(homeTeamName)
        viewModel.awayTeam.set(awayTeamName)
        viewModel.matchTitle.set("$homeTeamName vs $awayTeamName")

        viewModel.getOverallsByTeams(homeTeamName, awayTeamName)

//        getResultsByTeamName()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArguments()

        binding.imageHome.setImageResource(imageResolverId(homeTeamName))
        binding.imageAway.setImageResource(imageResolverId(awayTeamName))

    }


    fun getResultsByTeamName(){
        viewModel.getResultsByTeamName(homeTeamName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe{ homeResults -> viewModel.homeResults}
            .disposedBy(bag)
    }




}