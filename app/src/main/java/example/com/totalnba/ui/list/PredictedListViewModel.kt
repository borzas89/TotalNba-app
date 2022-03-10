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
    private val api: TotalNbaApi,
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

    fun getWeeksList(): List<String> {
        return listOf("Week 0", "Week 1", "Week 2", "Week 3", "Week 4", "Week 5",
            "Week 6", "Week 7", "Week 8", "Week 9", "Week 10",
            "Week 11", "Week 12", "Week 13", "Week 14", "Week 15",
            "Week 16", "Week 17", "Week 18", "Week 19", "Week 20", "Week 21")
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