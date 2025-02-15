package dev.kioba.platform.network

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

internal class NetworkScopeImpl : NetworkScope {

  companion object {
    private const val api: String = "https://jsonplaceholder.org"
  }

  private val logInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

  private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(logInterceptor)
    .build()

  @PublishedApi
  internal val retrofit: Retrofit =
    Retrofit
      .Builder()
      .baseUrl(api)
      .addConverterFactory(MoshiConverterFactory.create())
      .addConverterFactory(ScalarsConverterFactory.create())
      .addCallAdapterFactory(EitherCallAdapterFactory.create())
      .client(okHttpClient)
      .build()

  override fun <A> createApi(clazz: Class<A>): A =
    retrofit.create(clazz)
}
