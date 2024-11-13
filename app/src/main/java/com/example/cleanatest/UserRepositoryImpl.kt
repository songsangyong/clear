package com.example.cleanatest

//데이터 계층 (Data Layer)
//리포지토리 구현체 (Repository Implementation):
//실제 데이터 소스와 상호작용하는 클래스입니다.
//UserRepository 인터페이스를 구현하고, 데이터 액세스를 담당합니다.
class UserRepositoryImpl(private val localDataSource: LocalDataSource) : UserRepository {
    override fun getUserById(id: Int): User? {
        return localDataSource.getUserById(id)
    }

    override fun saveUser(user: User) {
        localDataSource.saveUser(user)
    }
}