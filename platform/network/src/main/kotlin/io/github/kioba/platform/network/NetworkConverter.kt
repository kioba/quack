package io.github.kioba.platform.network

public interface NetworkConverter<R, N> {
  public fun N.fromNetworkToDomain(): R
}