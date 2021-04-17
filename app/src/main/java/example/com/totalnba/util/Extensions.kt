package example.com.totalnba.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposedBy(bag: CompositeDisposable) {
    bag.add(this)
}