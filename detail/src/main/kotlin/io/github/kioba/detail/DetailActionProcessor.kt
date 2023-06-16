package io.github.kioba.detail

import android.content.Context
import io.github.kioba.android.database.buildDatabaseScope
import io.github.kioba.core.IActionProcessor
import io.github.kioba.core.ISchedulers
import io.github.kioba.detail.mvi_models.DetailCommentError
import io.github.kioba.detail.mvi_models.DetailCommentLoading
import io.github.kioba.detail.mvi_models.DetailCommentSuccess
import io.github.kioba.detail.mvi_models.DetailIntent
import io.github.kioba.detail.mvi_models.DetailPostSuccess
import io.github.kioba.detail.mvi_models.DetailResult
import io.github.kioba.detail.mvi_models.DetailUserError
import io.github.kioba.detail.mvi_models.DetailUserLoading
import io.github.kioba.detail.mvi_models.DetailUserSuccess
import io.github.kioba.detail.mvi_models.InitialDetailIntent
import io.github.kioba.placeholder.PlaceholderSdk
import io.github.kioba.placeholder.EffectScope
import io.github.kioba.placeholder.buildEffects
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import javax.inject.Inject

typealias IDetailActionProcessor = IActionProcessor<DetailIntent, DetailResult>

class DetailActionProcessor @Inject constructor(
  schedulers: ISchedulers,
  sdk: PlaceholderSdk,
  context: Context,
) : EffectScope by buildEffects(buildDatabaseScope(context)), IDetailActionProcessor {

  private val loadComments = FlowableTransformer<InitialDetailIntent, DetailResult> {
    it.switchMap { initialIntent ->
      sdk.getComments(initialIntent.post.id)
        .map<DetailResult>(::DetailCommentSuccess)
        .onErrorReturn(::DetailCommentError)
        .subscribeOn(schedulers.io)
        .observeOn(schedulers.main)
        .startWith(DetailCommentLoading)
    }
  }

  private val loadUser = FlowableTransformer<InitialDetailIntent, DetailResult> {
    it.switchMap { initialIntent ->
      sdk.getUser(initialIntent.post.userId)
        .map<DetailResult>(::DetailUserSuccess)
        .onErrorReturn(::DetailUserError)
        .subscribeOn(schedulers.io)
        .observeOn(schedulers.main)
        .startWith(DetailUserLoading)
    }
  }

  override val actionTransformer = FlowableTransformer<DetailIntent, DetailResult> { stream ->
    stream.publish { shared ->
      Flowable.merge(
        shared.ofType(InitialDetailIntent::class.java).map { DetailPostSuccess(it.post) },
        shared.ofType(InitialDetailIntent::class.java).compose(loadUser),
        shared.ofType(InitialDetailIntent::class.java).compose(loadComments)
      )
    }
  }
}
