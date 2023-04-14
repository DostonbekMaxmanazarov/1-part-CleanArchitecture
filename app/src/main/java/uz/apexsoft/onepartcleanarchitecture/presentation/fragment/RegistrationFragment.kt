package uz.apexsoft.onepartcleanarchitecture.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.apexsoft.onepartcleanarchitecture.R
import uz.apexsoft.onepartcleanarchitecture.presentation.utils.extensions.replaceFragment
import uz.apexsoft.onepartcleanarchitecture.data.mapper.impl.AuthenticationRequestMapToDomain
import uz.apexsoft.onepartcleanarchitecture.data.mapper.impl.SaveAuthenticationParamMapToStorage
import uz.apexsoft.onepartcleanarchitecture.data.repository.AuthRepositoryImpl
import uz.apexsoft.onepartcleanarchitecture.data.storage.AuthStorageSharedPref
import uz.apexsoft.onepartcleanarchitecture.data.storage.impl.AuthStorageSharedPrefImpl
import uz.apexsoft.onepartcleanarchitecture.databinding.FragmentRegistrationBinding
import uz.apexsoft.onepartcleanarchitecture.domain.models.RegistrationParam
import uz.apexsoft.onepartcleanarchitecture.domain.repository.AuthRepository
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.SaveAuthUseCase
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.impl.SaveAuthUseCaseImpl

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }

    private val saveAuthParamMapToStorage = SaveAuthenticationParamMapToStorage()
    private val authRequestMapToDomain = AuthenticationRequestMapToDomain()

    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage, saveAuthParamMapToStorage = saveAuthParamMapToStorage, authRequestMapToDomain = authRequestMapToDomain)
    }

    private val saveAuthUseCase: SaveAuthUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveAuthUseCaseImpl(authRepository = authRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegistrationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initClick()
    }

    private fun initClick() = binding.apply {
        btnSaveData.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            val registrationParams = RegistrationParam(
                email = email,
                password = password,
                phoneNumber = phoneNumber,
                confirmPassword = confirmPassword
            )
            val success = saveAuthUseCase(param = registrationParams)
            if (success) Toast.makeText(requireContext(), R.string.success, Toast.LENGTH_SHORT).show()
            else Toast.makeText(requireContext(), R.string.failed, Toast.LENGTH_SHORT).show()
        }

        tvLogin.setOnClickListener {
            replaceFragment(container = R.id.container, fragment = LoginFragment(), addToBackStack = true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}