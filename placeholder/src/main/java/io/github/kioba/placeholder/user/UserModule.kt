package io.github.kioba.placeholder.user

import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject


interface IUserModule {
  fun insertUser(user: DatabaseUser): Completable
  fun insertUsers(users: List<DatabaseUser>): Completable
  fun userStream(userId: Int): Flowable<DatabaseUser>
  fun usersStream(): Flowable<List<DatabaseUser>>
}

class UserModule @Inject constructor(private val userDao: UserDao) : IUserModule {

  override fun insertUser(user: DatabaseUser): Completable =
    Completable.fromAction { userDao.insertUser(user) }

  override fun insertUsers(users: List<DatabaseUser>): Completable =
    Completable.fromAction { userDao.insertUser(users) }

  override fun userStream(userId: Int): Flowable<DatabaseUser> = userDao.getUserById(userId)
  override fun usersStream(): Flowable<List<DatabaseUser>> = userDao.getUsers()
}
