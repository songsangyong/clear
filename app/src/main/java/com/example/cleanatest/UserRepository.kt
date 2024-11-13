package com.example.cleanatest

//도메인 계층 (Domain Layer)
//리포지토리 인터페이스 (Repository Interface):
//데이터 소스와 상호작용하는 메서드만 선언하고, 실제 구현은 하지 않습니다.
interface UserRepository {
    fun getUserById(id: Int): User?
    fun saveUser(user: User)
}