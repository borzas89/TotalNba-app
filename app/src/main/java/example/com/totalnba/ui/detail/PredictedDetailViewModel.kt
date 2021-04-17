package example.com.totalnba.ui.detail

import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.network.TotalNbaApi
import javax.inject.Inject

class PredictedDetailViewModel  @Inject constructor(
    private val api: TotalNbaApi

) : BaseViewModel() {



}