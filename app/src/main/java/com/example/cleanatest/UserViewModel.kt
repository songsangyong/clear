package com.example.cleanatest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//프레젠테이션 계층 (Presentation Layer)
//ViewModel: UI와 비즈니스 로직을 연결합니다.
// ViewModel은 UseCase를 호출하여 데이터를 가져오고,
// LiveData를 통해 UI에 데이터를 전달합니다.
class UserViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun loadUser(userId: Int) {
        //  getUserUseCase를 호출하여 유저정보요청
        val result = getUserUseCase.execute(userId)
        _user.value = result
    }

    fun saveUser(user: User) {
        saveUserUseCase.execute(user)
        _user.value = user
    }
}