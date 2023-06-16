package io.github.kioba.placeholder.user

import io.github.kioba.core.ISchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject


class UserModule @Inject constructor(
  appSchedulers: ISchedulers,
  private val userDao: UserDao,
) {

  val io = appSchedulers.io
  fun getUser(userId: Long): Maybe<DatabaseUser> =
    userDao.getUserByIdMaybe(userId).subscribeOn(io)

  fun getUsers(): Maybe<List<DatabaseUser>> = userDao.getUsersMaybe().subscribeOn(io)

  fun insertUser(user: DatabaseUser): Completable =
    userDao.insertUser(user).subscribeOn(io)

  fun insertUsers(users: List<DatabaseUser>) = userDao.insertUser(users).subscribeOn(io)

  fun userStream(userId: Long): Flowable<DatabaseUser> =
    userDao.getUserById(userId).subscribeOn(io)

  fun usersStream(): Flowable<List<DatabaseUser>> = userDao.getUsers().subscribeOn(io)
}
