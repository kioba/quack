package io.github.kioba.platform.database

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver


public interface DatabaseScope {

  public fun <R> createDatabase(
    schema: SqlDriver.Schema,
    f: (SqlDriver) -> R,
  ): R where R : Transacter
}