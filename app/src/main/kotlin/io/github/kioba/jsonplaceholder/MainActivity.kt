package io.github.kioba.quack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.kioba.feature.feed.ui.FeedFragment

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .apply {
          replace(R.id.main_content, FeedFragment())
        }.commit()
    }
  }

}
