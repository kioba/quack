package io.github.kioba.detail

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import arrow.core.toOption
import dagger.android.support.AndroidSupportInjection
import io.github.kioba.core.registerForDispose
import io.github.kioba.detail.mvi_models.DetailIntent
import io.github.kioba.detail.mvi_models.DetailViewState
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class DetailFragment : Fragment() {

  private val adapter = DetailAdapter()

  private val disposables = CompositeDisposable()

  @Inject
  lateinit var viewModel: IDetailViewModel

  override fun onAttach(context: Context) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.fragment_detail, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    detail_recycler.layoutManager = LinearLayoutManager(requireContext())
    detail_recycler.adapter = adapter

    detail_content.alpha = 0f
    ObjectAnimator.ofFloat(detail_content, View.ALPHA, 0f, 1f).apply {
      startDelay = 150
      duration = 850
      start()
    }
  }

  override fun onStart() {
    super.onStart()

    viewModel.state()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(this::render, this::streamError)
      .registerForDispose(disposables)
    viewModel.bind(intents())

  }

  override fun onStop() {
    super.onStop()

    disposables.clear()
  }


  /**
   * FeedIntent events generated from the View
   * @return stream of view intents
   */
  private fun intents(): Flowable<DetailIntent> = Flowable.empty()

  /**
   * rendering function for the viewState
   * @param state: view state
   */
  private fun render(state: DetailViewState) {
    state.post.toOption().fold(
      ifEmpty = {},
      ifSome = {}
    )

    when {
      state.isUserLoading -> {
      }
      state.userError != null -> {
      }
      else -> {

      }
    }

    when {
      state.isCommentsLoading -> {
      }
      state.commentError != null -> {
      }
      else -> {

      }
    }
  }

  /**
   * report stream errors for crashlytics
   * @param error: ErrorType which have been triggered inside the stream
   */
  private fun streamError(error: Throwable) {
    throw error
  }

  companion object {
    fun post(post: Post): DetailFragment {
      return DetailFragment()
    }
  }
}

