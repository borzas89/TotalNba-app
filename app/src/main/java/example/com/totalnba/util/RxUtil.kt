package example.com.totalnba.util

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.processors.FlowableProcessor

fun <T> FlowableProcessor<T>.wrap(): Flowable<T> = Flowable.create({ source ->
    val disposable = subscribe(source::onNext, source::onError)

    source.setCancellable(disposable::dispose)
}, BackpressureStrategy.BUFFER)

fun Unit.safe() = Unit