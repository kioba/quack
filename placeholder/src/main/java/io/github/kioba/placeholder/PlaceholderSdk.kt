package io.github.kioba.placeholder

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.kioba.placeholder.json_placeholder.JsonPlaceholderApi
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PlaceholderSdk : IPlaceholderSdk {

  companion object {
    private const val api: String = "https://jsonplaceholder.typicode.com"
  }

  private val logInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

  private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(logInterceptor)
    .build()

  private val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(api)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

  private val jsonPlaceholderApi: JsonPlaceholderApi = retrofit.create(
    JsonPlaceholderApi::class.java
  )

  override fun getFeed(): Flowable<List<Post>> {
    return jsonPlaceholderApi
      .getFeed()
      .toFlowable()
  }
}
