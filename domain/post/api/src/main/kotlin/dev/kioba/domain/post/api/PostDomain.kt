package dev.kioba.domain.post.api

import arrow.core.Either
import arrow.core.right
import dev.kioba.domain.post.api.converter.toDomain
import dev.kioba.domain.post.api.converter.toEntity
import dev.kioba.domain.post.api.model.Post
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.network.post.fetchPosts
import dev.kioba.persistence.post.insertPosts
import dev.kioba.persistence.post.readPosts
import dev.kioba.persistence.post.streamPost
import dev.kioba.persistence.post.streamPosts
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.UseCaseScope
import dev.kioba.platform.domain.cacheOnlyFlowableStrategy
import dev.kioba.platform.domain.syncFirstStrategy
import dev.kioba.platform.domain.useCase
import dev.kioba.platform.domain.useCaseFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


public suspend fun EffectContext.syncPosts(): Either<Throwable, List<Post>> =
  useCase { fetchAndSavePosts() }

private suspend fun UseCaseScope.fetchAndSavePosts(): Either<Throwable, List<Post>> =
  syncFirstStrategy(
    sync = { fetchPosts().map { it.map { response -> response.toDomain() } } },
    insert = { insertPosts(it.map { post -> post.toEntity() }) },
    read = { readPosts().map { it.toDomain() }.right() },
  )

public fun EffectContext.listenPosts(): Flow<List<Post>> =
  useCaseFlow {
    cacheOnlyFlowableStrategy {
      streamPosts()
        .map {
          it.map { entity -> entity.toDomain() }
        }
    }
  }
    .catch { emit(emptyList()) }

public fun EffectContext.listenPost(
  id: PostId,
): Flow<Post> =
  useCaseFlow {
    cacheOnlyFlowableStrategy {
      streamPost(id.value)
        .map { entity -> entity.toDomain() }
    }
  }
