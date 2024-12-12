package dev.kioba.platform.database

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

public interface DatabaseScope {

  public fun <R> createDatabase(
    schema: SqlSchema<QueryResult.Value<Unit>>,
    f: (SqlDriver) -> R,
  ): R where R : Transacter
}
