package dev.kioba.domain.post.api

import dev.kioba.domain.post.api.converter.toDomain
import dev.kioba.domain.post.api.model.Post
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.persistence.post.streamPost
import dev.kioba.persistence.post.streamPosts
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.cacheOnlyFlowableStrategy
import dev.kioba.platform.domain.useCaseFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


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
