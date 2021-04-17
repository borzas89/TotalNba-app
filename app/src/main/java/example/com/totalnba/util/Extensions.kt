package example.com.totalnba.util

import example.com.totalnba.R
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

fun roundingDouble(value: Double): String{
    return BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toString()
}
