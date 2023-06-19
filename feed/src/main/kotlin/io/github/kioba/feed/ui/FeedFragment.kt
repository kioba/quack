package io.github.kioba.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.kioba.android.database.buildDatabaseScope
import io.github.kioba.feed.data.FeedEffectsScope
import io.github.kioba.feed.data.feedScope
import io.github.kioba.platform.android.compose.renderWith

class FeedFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View =
    renderWith(scope = {
      feedScope(FeedEffectsScope(buildDatabaseScope(requireContext())))
    }) { state ->
      FeedUi(state = state)
    }
}
