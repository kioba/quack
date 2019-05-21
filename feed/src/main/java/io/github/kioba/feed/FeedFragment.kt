package io.github.kioba.feed

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.Transition
import androidx.transition.Transition.EpicenterCallback
import androidx.transition.TransitionSet
import arrow.core.Option
import arrow.core.toOption
import dagger.android.support.AndroidSupportInjection
import io.github.kioba.core.registerForDispose
import io.github.kioba.core.setTransitionInterpolator
import io.github.kioba.detail.DetailFragment
import io.github.kioba.feed.mvi_models.FeedIntent
import io.github.kioba.feed.mvi_models.FeedState
import io.github.kioba.feed.mvi_models.InitialFeedIntent
import io.github.kioba.feed.recycler_views.ErrorFeedDataHolder
import io.github.kioba.feed.recycler_views.FeedAdapter
import io.github.kioba.feed.recycler_views.NavigationControl
import io.github.kioba.feed.recycler_views.PostDataHolder
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

interface MainNavigation {
  fun navigateToDetails(sharedElement: Pair<View, String>, fragment: Fragment)
}

class FeedFragment : Fragment(), NavigationControl {

  private val adapter = FeedAdapter(this)

  private val disposables = CompositeDisposable()

  @Inject
  lateinit var viewModel: IFeedViewModel

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
    savedInstanceState: Bundle?
  ): View =
    inflater.inflate(R.layout.fragment_feed, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    postponeEnterTransition()

    feed_recycler.layoutManager = LinearLayoutManager(requireContext())
    feed_recycler.adapter = adapter
    feed_recycler.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

    feed_bar.setNavigationOnClickListener {
      AboutBottomSheet().show(fragmentManager!!, "AboutBottomSheet")
    }

    (view.parent as? ViewGroup)?.doOnPreDraw {
      startPostponedEnterTransition()
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
  private fun intents(): Flowable<FeedIntent> = Flowable.just(InitialFeedIntent)

  /**
   * rendering function for the viewState
   * @param state: view state
   */
  private fun render(state: FeedState) {
    if (state.feedLoading) {
      adapter.feed = List(7) { PostDataHolder(Option.empty(), Option.empty(), Option.empty()) }
    } else {
      if (state.feedError != null) {
        adapter.feed = listOf(ErrorFeedDataHolder())
      } else {
        adapter.feed = state.combined.map { PostDataHolder(it.post.toOption(), it.user, it.avatar) }
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

  override fun animateToDetail(post: Post, view: View, viewRect: Rect) {
    exitTransition = SlideExplode().apply {
      duration = transitionDuration
      interpolator = transitionInterpolator
      epicenterCallback = object : EpicenterCallback() {
        override fun onGetEpicenter(transition: Transition) = viewRect
      }
    }

    val sharedElementTransition = TransitionSet()
      .addTransition(ChangeBounds())
      .addTransition(ChangeTransform())
      .addTransition(ChangeImageTransform()).apply {
        duration = transitionDuration
        setTransitionInterpolator(transitionInterpolator)
      }

    val fragment = DetailFragment.post(post)
      .apply {
      sharedElementEnterTransition = sharedElementTransition
      sharedElementReturnTransition = sharedElementTransition
    }

    (activity as? MainNavigation)?.navigateToDetails(view to "transition_name", fragment)

  }

  companion object {
    const val transitionDuration = 500L
    val transitionInterpolator = FastOutSlowInInterpolator()
  }
}
