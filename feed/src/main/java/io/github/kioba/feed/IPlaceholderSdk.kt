package io.github.kioba.feed

import io.reactivex.Flowable

interface IPlaceholderSdk {
  fun getFeed(): Flowable<List<Int>>

}
