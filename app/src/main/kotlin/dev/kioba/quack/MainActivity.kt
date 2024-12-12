package dev.kioba.quack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.kioba.feature.feed.ui.FeedFragment

internal class MainActivity : AppCompatActivity() {
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
