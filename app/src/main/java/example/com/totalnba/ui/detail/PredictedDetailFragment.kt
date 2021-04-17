package example.com.totalnba.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.com.totalnba.R
import example.com.totalnba.arch.BaseFragment
import example.com.totalnba.arch.applyArgs
import example.com.totalnba.arch.getViewModelFromFactory
import example.com.totalnba.arch.requireString


class PredictedDetailFragment: BaseFragment<PredictedDetailViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

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
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_predicted_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArguments()
    }





}