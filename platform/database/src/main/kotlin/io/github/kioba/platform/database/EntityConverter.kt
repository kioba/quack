package io.github.kioba.platform.database

public interface EntityConverter<R, P> {
  public fun P.fromEntityToDomain(): R
  public fun R.fromDomainToEntity(): P
}
