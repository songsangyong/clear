package com.example.cleanatest

//도메인 계층 (Domain Layer)
//유즈케이스 (UseCase):
//비즈니스 로직을 처리하며,
//실제 데이터 액세스는 리포지토리 인터페이스를 통해 이루어집니다.
class GetUserUseCase(private val userRepository: UserRepository) {
    fun execute(userId: Int): User? {
        return userRepository.getUserById(userId)
    }
}