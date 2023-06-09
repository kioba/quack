package io.github.kioba.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulers constructor() : ISchedulers {
  override val io: Scheduler
    get() = Schedulers.io()

  override val computation: Scheduler
    get() = Schedulers.computation()

  override val main: Scheduler
    get() = AndroidSchedulers.mainThread()

}

class TestSchedulers constructor() : ISchedulers {
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
