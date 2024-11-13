package com.example.cleanatest

//데이터 계층 (Data Layer)
//데이터 소스 (Data Source):
//데이터베이스 또는 API와 같은 실제 데이터 저장소를 관리합니다.
class LocalDataSource {
    private val users = mutableMapOf<Int, User>()

    fun getUserById(id: Int): User? = users[id]

    fun saveUser(user: User) {
        users[user.id] = user
    }
}