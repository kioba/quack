package dev.kioba.android.database

import android.content.Context
import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.kioba.platform.database.DatabaseScope

internal class DatabaseScopeImpl internal constructor(
  private val context: Context,
) : DatabaseScope {
  override fun <R : Transacter> createDatabase(
    schema: SqlSchema<QueryResult.Value<Unit>>,
    f: (SqlDriver) -> R,
  ): R =
    f(AndroidSqliteDriver(schema, context))
}

public fun buildDatabaseScope(
  context: Context,
): DatabaseScope =
  DatabaseScopeImpl(context.applicationContext)