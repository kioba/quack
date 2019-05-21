package io.github.kioba.jsonplaceholder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import io.github.kioba.core.execute
import io.github.kioba.feed.FeedFragment
import io.github.kioba.feed.MainNavigation

class MainActivity : AppCompatActivity(), MainNavigation {

  val feedFragment = FeedFragment()

  override fun navigateToDetails(sharedElement: Pair<View, String>, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
      .setReorderingAllowed(true)
      .replace(R.id.main_content, fragment)
      .addToBackStack(null)
      .addSharedElement(sharedElement.first, sharedElement.second)
      .commit()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    setContentView(R.layout.activity_main)

    supportFragmentManager.execute {
      replace(R.id.main_content, feedFragment)
    }
  }

}
