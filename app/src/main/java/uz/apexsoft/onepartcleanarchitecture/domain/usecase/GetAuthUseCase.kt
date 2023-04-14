package uz.apexsoft.onepartcleanarchitecture.domain.usecase

import uz.apexsoft.onepartcleanarchitecture.domain.models.LoginParam

interface GetAuthUseCase {
    operator fun invoke(param: LoginParam): Boolean
}