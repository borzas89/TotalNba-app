package example.com.totalnba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import example.com.totalnba.arch.Navigator
import example.com.totalnba.ui.list.PredictedListFragment

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            add(PredictedListFragment())
        }
    }

    override fun add(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContent, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun pop() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            pop()
        } else {
            finish()
        }
    }
}