package io.github.kioba.feed.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import io.github.kioba.android.database.buildDatabaseScope
import io.github.kioba.feed.data.FeedEffectsScope
import io.github.kioba.feed.data.feedScope
import io.github.kioba.placeholder.PlaceholderSdk
import io.github.kioba.placeholder.persistence.DaoScope
import io.github.kioba.platform.android.compose.renderWith
import javax.inject.Inject

interface MainNavigation {
  fun navigateToDetails(sharedElement: Pair<View, String>, fragment: Fragment)
}

class FeedFragment : Fragment() {

  @Inject
  lateinit var sdk: PlaceholderSdk

  @Inject
  lateinit var daoScope: DaoScope

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View =
    renderWith(scope = {
      feedScope(FeedEffectsScope(sdk, buildDatabaseScope(requireContext())))
    }) { state ->
      FeedUi(state = state)
    }
}
