package io.github.kioba.android.database

import android.content.Context
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.github.kioba.platform.database.DatabaseScope

internal class DatabaseScopeImpl internal constructor(
  private val context: Context,
) : DatabaseScope {
  override fun <R : Transacter> createDatabase(
    schema: SqlDriver.Schema,
    f: (SqlDriver) -> R,
  ): R =
    f(AndroidSqliteDriver(schema, context))
}

public fun buildDatabaseScope(
  context: Context,
): DatabaseScope =
  DatabaseScopeImpl(context.applicationContext)