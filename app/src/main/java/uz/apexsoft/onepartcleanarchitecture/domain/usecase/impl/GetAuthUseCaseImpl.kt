package uz.apexsoft.onepartcleanarchitecture.domain.usecase.impl

import uz.apexsoft.onepartcleanarchitecture.domain.models.LoginParam
import uz.apexsoft.onepartcleanarchitecture.domain.repository.AuthRepository
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.GetAuthUseCase

class GetAuthUseCaseImpl(
    private val authRepository: AuthRepository
) : GetAuthUseCase {
    override fun invoke(param: LoginParam): Boolean {
        val authentication = authRepository.getAuthentication()
        if (param.password == authentication.password && param.phoneNumber == authentication.phoneNumber) return true
        return false
    }
}