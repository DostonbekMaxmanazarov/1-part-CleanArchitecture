package uz.apexsoft.onepartcleanarchitecture.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.apexsoft.onepartcleanarchitecture.R
import uz.apexsoft.onepartcleanarchitecture.data.mapper.impl.AuthenticationRequestMapToDomain
import uz.apexsoft.onepartcleanarchitecture.data.mapper.impl.SaveAuthenticationParamMapToStorage
import uz.apexsoft.onepartcleanarchitecture.data.repository.AuthRepositoryImpl
import uz.apexsoft.onepartcleanarchitecture.data.storage.AuthStorageSharedPref
import uz.apexsoft.onepartcleanarchitecture.data.storage.impl.AuthStorageSharedPrefImpl
import uz.apexsoft.onepartcleanarchitecture.databinding.FragmentLoginBinding
import uz.apexsoft.onepartcleanarchitecture.domain.models.LoginParam
import uz.apexsoft.onepartcleanarchitecture.domain.repository.AuthRepository
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.GetAuthUseCase
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.impl.GetAuthUseCaseImpl

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) { AuthStorageSharedPrefImpl(context = requireContext()) }
    private val saveAuthParamMapToStorage = SaveAuthenticationParamMapToStorage()
    private val authRequestMapToDomain = AuthenticationRequestMapToDomain()
    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(
            authStorage = authStorage,
            saveAuthParamMapToStorage = saveAuthParamMapToStorage,
            authRequestMapToDomain = authRequestMapToDomain
        )
    }
    private val getAuthUseCase: GetAuthUseCase by lazy(LazyThreadSafetyMode.NONE) { GetAuthUseCaseImpl(authRepository = authRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initClickView()
    }

    private fun initClickView() = binding.apply {
        btnLogin.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val password = etPassword.text.toString()
            val loginParam = LoginParam(
                phoneNumber = phoneNumber,
                password = password
            )
            val success = getAuthUseCase(param = loginParam)
            if (success) Toast.makeText(requireContext(), R.string.success, Toast.LENGTH_SHORT).show()
            else Toast.makeText(requireContext(), R.string.failed, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
