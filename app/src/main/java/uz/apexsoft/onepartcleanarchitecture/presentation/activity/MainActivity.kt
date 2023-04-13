package uz.apexsoft.onepartcleanarchitecture.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.apexsoft.onepartcleanarchitecture.data.repository.UserRepositoryImpl
import uz.apexsoft.onepartcleanarchitecture.data.storage.UserStorage
import uz.apexsoft.onepartcleanarchitecture.data.storage.impl.UserStorageSharedPrefImpl
import uz.apexsoft.onepartcleanarchitecture.databinding.ActivityMainBinding
import uz.apexsoft.onepartcleanarchitecture.domain.models.SaveUserDataParam
import uz.apexsoft.onepartcleanarchitecture.domain.repository.UserRepository
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.GetUserDataUseCase
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.SaveUserDataUseCase
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.impl.GetUserDataUseCaseImpl
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.impl.SaveUserDataUseCaseImpl

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val userStorage: UserStorage by lazy(LazyThreadSafetyMode.NONE) {
        UserStorageSharedPrefImpl(context = this)
    }
    private val userRepository: UserRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = userStorage)
    }
    private val getUserDataUseCase: GetUserDataUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserDataUseCaseImpl(userRepository = userRepository)
    }
    private val saveUserDataUseCase: SaveUserDataUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserDataUseCaseImpl(userRepository = userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initClickView()
    }

    private fun initClickView() = binding.apply {
        btnGetUserData.setOnClickListener {
            val result = getUserDataUseCase()
            tvFirstName.text = result.firstName
            tvLastName.text = result.lastName
        }

        btnSaveData.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val param = SaveUserDataParam(firstName = firstName, lastName = lastName)
            val result = saveUserDataUseCase(param = param)
            tvResult.text = "$result"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}