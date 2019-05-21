package io.github.kioba.placeholder.user

import io.github.kioba.core.ISchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject


interface IUserModule {
  fun insertUser(user: DatabaseUser): Completable
  fun insertUsers(users: List<DatabaseUser>): Completable
  fun userStream(userId: Int): Flowable<DatabaseUser>
  fun usersStream(): Flowable<List<DatabaseUser>>
  fun getUser(userId: Int): Maybe<DatabaseUser>
  fun getUsers(): Maybe<List<DatabaseUser>>
}

class UserModule @Inject constructor(
  appSchedulers: ISchedulers,
  private val userDao: UserDao
) : IUserModule {

  val io = appSchedulers.io
  override fun getUser(userId: Int): Maybe<DatabaseUser> =
    userDao.getUserByIdMaybe(userId).subscribeOn(io)

  override fun getUsers(): Maybe<List<DatabaseUser>> = userDao.getUsersMaybe().subscribeOn(io)

  override fun insertUser(user: DatabaseUser): Completable =
    userDao.insertUser(user).subscribeOn(io)

  override fun insertUsers(users: List<DatabaseUser>) = userDao.insertUser(users).subscribeOn(io)

  override fun userStream(userId: Int): Flowable<DatabaseUser> =
    userDao.getUserById(userId).subscribeOn(io)

  override fun usersStream(): Flowable<List<DatabaseUser>> = userDao.getUsers().subscribeOn(io)
}
