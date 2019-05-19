package io.github.kioba.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppSchedulers @Inject constructor() : ISchedulers {
  override val io: Scheduler
    get() = Schedulers.io()

  override val computation: Scheduler
    get() = Schedulers.computation()

  override val main: Scheduler
    get() = AndroidSchedulers.mainThread()

}

class TestSchedulers @Inject constructor() : ISchedulers {
  override val io: Scheduler
    get() = Schedulers.trampoline()

  override val computation: Scheduler
    get() = Schedulers.trampoline()

  override val main: Scheduler
    get() = Schedulers.trampoline()

}

interface ISchedulers {
  val main: Scheduler
  val io: Scheduler
  val computation: Scheduler
}
