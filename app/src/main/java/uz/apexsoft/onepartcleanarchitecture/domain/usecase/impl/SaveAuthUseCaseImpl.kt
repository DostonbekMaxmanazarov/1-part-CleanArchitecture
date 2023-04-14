package uz.apexsoft.onepartcleanarchitecture.domain.usecase.impl

import uz.apexsoft.onepartcleanarchitecture.domain.models.RegistrationParam
import uz.apexsoft.onepartcleanarchitecture.domain.repository.AuthRepository
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.SaveAuthUseCase
import uz.apexsoft.onepartcleanarchitecture.domain.utils.isValidationEmail
import uz.apexsoft.onepartcleanarchitecture.domain.utils.isValidationPassword
import uz.apexsoft.onepartcleanarchitecture.domain.utils.isValidationPhoneNumber

class SaveAuthUseCaseImpl(
    private val authRepository: AuthRepository
) : SaveAuthUseCase {

    override fun invoke(param: RegistrationParam): Boolean {
        val oldAuthenticationData = authRepository.getAuthentication()

        if (param.email.isValidationEmail() &&
            param.phoneNumber.isValidationPhoneNumber() &&
            param.password.isValidationPassword() &&
            param.password == param.confirmPassword) {

            if (oldAuthenticationData.phoneNumber == param.phoneNumber &&
                oldAuthenticationData.password == param.password) {
                return true
            }
            return authRepository.saveAuthentication(saveParam = param)
        }
        return false
    }
}