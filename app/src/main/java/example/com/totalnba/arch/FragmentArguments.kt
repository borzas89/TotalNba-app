@file:Suppress("NOTHING_TO_INLINE")
package example.com.totalnba.arch

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T : Fragment> T.applyArgs(argSetup: Bundle.() -> Unit): T = apply {
    val bundle = Bundle()
    bundle.argSetup()
    arguments = bundle
}

inline fun Fragment.requireArguments(): Bundle {
    return arguments ?: throw IllegalStateException("Fragment has no arguments Bundle.")
}


inline fun Bundle.requireString(key: String): String {
    return if (containsKey(key)) getString( key ).toString() else throw IllegalStateException("Bundle has no key $key")
}