package io.github.kioba.placeholder

import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.reactivex.Flowable

interface IPlaceholderSdk {
  fun getFeed(): Flowable<List<Post>>
}
