package example.com.totalnba.ui.list

import androidx.databinding.ObservableField
import com.jakewharton.rxrelay2.BehaviorRelay
import example.com.totalnba.arch.BaseViewModel
import example.com.totalnba.data.network.TotalNbaApi
import example.com.totalnba.data.network.model.PredictedMatch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class PredictedListViewModel @Inject constructor(
    private val api: TotalNbaApi
) : BaseViewModel() {

    val predictions = BehaviorRelay.createDefault(listOf<PredictedMatch>())
    val filterWeek: BehaviorSubject<String> = BehaviorSubject.createDefault("Week 1")
    val filterDay: BehaviorSubject<String> = BehaviorSubject.createDefault(
        formattedToday())
    val errorTitle = ObservableField<String>()

    fun formattedToday(): String {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
        return formatter.format(LocalDate.now().atStartOfDay())
    }

    init {
        filterDay.subscribe {
            gettingMatchesByDay()
        }.addTo(disposables)
    }

    fun gettingMatchesByDay() {
        api.getPredictedMatchesByDay(filterDay.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .compose(applySingleTransformers())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { result ->
                    errorTitle.set("")
                    when (result) {
                        result -> predictions.accept(result)
                    }

                }, onError = {
                    errorTitle.set("Something went wrong, try again later..")
                }
            )
    }

    fun gettingMatchesByWeek() {
        api.getPredictedMatchesByWeek(filterWeek.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .compose(applySingleTransformers())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { result ->
                    errorTitle.set("")
                    when (result) {
                        result -> predictions.accept(result)
                    }

                }, onError = {
                    errorTitle.set("Something went wrong, try again later..")
                }
            )
    }
}