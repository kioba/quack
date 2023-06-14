package io.github.kioba.feed.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import io.github.kioba.core.registerForDispose
import io.github.kioba.feed.data.FeedEffectsScope
import io.github.kioba.feed.data.IFeedViewModel
import io.github.kioba.feed.model.FeedIntent
import io.github.kioba.feed.model.FeedState
import io.github.kioba.feed.model.InitialFeedIntent
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

interface MainNavigation {
  fun navigateToDetails(sharedElement: Pair<View, String>, fragment: Fragment)
}

class FeedFragment : Fragment() {

  private val disposables = CompositeDisposable()

  @Inject
  lateinit var viewModel: IFeedViewModel

  @Inject
  lateinit var effectsScope: FeedEffectsScope

  override fun onAttach(context: Context) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View =
    ComposeView(requireContext())

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
  private fun intents(): Flowable<FeedIntent> = Flowable.just(InitialFeedIntent)

  /**
   * rendering function for the viewState
   * @param state: view state
   */
  private fun render(state: FeedState) {
    (view as? ComposeView)?.apply {
      setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
      setContent { FeedUi(state = state) }
    }
  }

  /**
   * report stream errors for crashlytics
   * @param error: ErrorType which have been triggered inside the stream
   */
  private fun streamError(error: Throwable) {
    throw error
  }
}
