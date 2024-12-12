package dev.kioba.platform.network

public interface NetworkScope {
  public fun <A> createApi(clazz: Class<A>): A
}

public inline fun <reified A> NetworkScope.createApi(): A =
  createApi(A::class.java)
