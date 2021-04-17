package example.com.totalnba.util

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import example.com.totalnba.R
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.math.BigDecimal
import java.math.RoundingMode

fun Disposable.disposedBy(bag: CompositeDisposable) {
    bag.add(this)
}

fun imageResolverId(key: String) : Int {
    val teamsMap: MutableMap<String, Int> = HashMap()
    teamsMap["Atlanta Hawks"] = R.drawable.atl
    teamsMap["Boston Celtics"] =   R.drawable.bos
    teamsMap["Brooklyn Nets"] =  R.drawable.bkn
    teamsMap["Charlotte Hornets"] = R.drawable.cha
    teamsMap["Chicago Bulls"] =  R.drawable.chi
    teamsMap["Cleveland Cavaliers"] = R.drawable.cle
    teamsMap["Dallas Mavericks"] = R.drawable.dal
    teamsMap["Denver Nuggets"] = R.drawable.den
    teamsMap["Detroit Pistons"] =  R.drawable.det
    teamsMap["Golden State Warriors"] = R.drawable.gsw
    teamsMap["Houston Rockets"] = R.drawable.hou
    teamsMap["Indiana Pacers"] = R.drawable.ind
    teamsMap["LA Clippers"] = R.drawable.lac
    teamsMap["Los Angeles Lakers"] = R.drawable.lal
    teamsMap["Memphis Grizzlies"] = R.drawable.mem
    teamsMap["Miami Heat"] = R.drawable.mia
    teamsMap["Milwaukee Bucks"] =  R.drawable.mil
    teamsMap["Minnesota Timberwolves"] = R.drawable.min
    teamsMap["New Orleans Pelicans" ] = R.drawable.nop
    teamsMap["New York Knicks" ] = R.drawable.nyk
    teamsMap["Oklahoma City Thunder" ] =  R.drawable.okc
    teamsMap["Orlando Magic"] =  R.drawable.orl
    teamsMap["Philadelphia 76ers"] = R.drawable.phi
    teamsMap["Phoenix Suns"] = R.drawable.phx
    teamsMap["Portland Trail Blazers"] =  R.drawable.por
    teamsMap["Sacramento Kings"] =  R.drawable.sac
    teamsMap["San Antonio Spurs" ] = R.drawable.sas
    teamsMap["Toronto Raptors"] = R.drawable.tor
    teamsMap["Utah Jazz" ] = R.drawable.uta
    teamsMap["Washington Wizards"] =  R.drawable.was

    return teamsMap.get(key)!!.toInt()
}

fun backgroundResolverId(key: String) : Int {
    val colorsMap: MutableMap<String, Int> = HashMap()
    colorsMap["Atlanta Hawks"] = R.color.team_red
    colorsMap["Boston Celtics"] = R.color.team_green
    colorsMap["Brooklyn Nets"] =  R.color.black
    colorsMap["Charlotte Hornets"] = R.color.team_blue_dark
    colorsMap["Chicago Bulls"] =  R.color.team_red_dark
    colorsMap["Cleveland Cavaliers"] = R.color.team_wine
    colorsMap["Dallas Mavericks"] = R.color.team_blue_light
    colorsMap["Denver Nuggets"] = R.color.team_blue_dark
    colorsMap["Detroit Pistons"] =  R.color.team_red_light
    colorsMap["Golden State Warriors"] = R.color.team_blue_light
    colorsMap["Houston Rockets"] = R.color.team_red_dark
    colorsMap["Indiana Pacers"] = R.color.team_blue_dark
    colorsMap["LA Clippers"] = R.color.team_red
    colorsMap["Los Angeles Lakers"] = R.color.team_purple
    colorsMap["Memphis Grizzlies"] = R.color.team_blue_grey
    colorsMap["Miami Heat"] = R.color.team_wine_dark
    colorsMap["Milwaukee Bucks"] =  R.color.team_green_dark
    colorsMap["Minnesota Timberwolves"] = R.color.team_blue_dark
    colorsMap["New Orleans Pelicans" ] = R.color.team_blue_dark
    colorsMap["New York Knicks" ] = R.color.team_orange
    colorsMap["Oklahoma City Thunder" ] =  R.color.team_blue
    colorsMap["Orlando Magic"] =  R.color.team_blue
    colorsMap["Philadelphia 76ers"] = R.color.team_blue_light
    colorsMap["Phoenix Suns"] = R.color.team_orange_dark
    colorsMap["Portland Trail Blazers"] =  R.color.team_red
    colorsMap["Sacramento Kings"] =  R.color.team_purple_light
    colorsMap["San Antonio Spurs" ] = R.color.team_black
    colorsMap["Toronto Raptors"] = R.color.team_red_light
    colorsMap["Utah Jazz" ] = R.color.team_blue_dark
    colorsMap["Washington Wizards"] =  R.color.team_blue_dark

    return colorsMap.get(key)!!.toInt()
}

fun roundingDouble(value: Double): String{
    return BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toString()
}

fun <T> ObservableField<T>.toFlowable(): Flowable<T> {
    return Flowable.create({ emitter ->
        get()?.let { nextValue -> emitter.onNext(nextValue) }

        val callback = object : Observable.OnPropertyChangedCallback() {

            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                get()?.let { nextValue -> emitter.onNext(nextValue) }
            }
        }

        addOnPropertyChangedCallback(callback)
        emitter.setCancellable { removeOnPropertyChangedCallback(callback) }
    }, BackpressureStrategy.BUFFER)
}
