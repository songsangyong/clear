package com.example.cleanatest

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.cleanatest.databinding.ActivityMainBinding

//도메인 계층 (Domain Layer): 비즈니스 로직과 규칙이 있는 계층입니다. 비즈니스 규칙에 따라 데이터를 처리하는 UseCase와 Model, 그리고 Repository 인터페이스가 포함됩니다.
//데이터 계층 (Data Layer): 도메인 계층에서 정의한 Repository 인터페이스를 구현하여 실제 데이터를 제공하는 계층입니다. 네트워크나 로컬 데이터베이스와 통신하여 데이터를 관리합니다.
//프레젠테이션 계층 (Presentation Layer): UI와 비즈니스 로직을 연결하는 계층입니다. ViewModel을 통해 UseCase를 호출하고, 데이터를 UI에 전달합니다.
//프레임워크 및 드라이버 계층 (Framework & Drivers): 실제 앱에서 사용하는 라이브러리, 네트워크 통신, 데이터베이스 설정 등이 들어가는 계층입니다.
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val userViewModel: UserViewModel by lazy {
        val localDataSource = LocalDataSource()
        val userRepository = UserRepositoryImpl(localDataSource)
        val getUserUseCase = GetUserUseCase(userRepository)
        val saveUserUseCase = SaveUserUseCase(userRepository)
        ViewModelProvider(
            this,
            UserViewModelFactory(getUserUseCase, saveUserUseCase)
        )[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            saveButton.setOnClickListener {
                val user = User(id = 1, name = nameEditText.text.toString())
                //  UserViewModel을 통해 유저 정보 요청
                userViewModel.saveUser(user)
            }

            loadButton.setOnClickListener {
                userViewModel.loadUser(1)
            }

            userViewModel.user.observe(this@MainActivity) { user ->
                user?.let {
                    userInfoTextView.text = "ID: ${it.id}, Name: ${it.name}"
                } ?: run {
                    userInfoTextView.text = "User not found"
                }
            }
        }
    }
}