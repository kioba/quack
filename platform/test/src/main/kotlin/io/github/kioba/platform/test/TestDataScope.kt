package io.github.kioba.platform.test

public inline fun testDataProvider(
  block: TestDataScope.() -> Unit,
) {
  TestDataScope.block()
}

public object TestDataScope