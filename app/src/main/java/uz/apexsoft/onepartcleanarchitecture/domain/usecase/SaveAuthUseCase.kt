package uz.apexsoft.onepartcleanarchitecture.domain.usecase

import uz.apexsoft.onepartcleanarchitecture.domain.models.RegistrationParam

interface SaveAuthUseCase {
    operator fun invoke(param: RegistrationParam): Boolean
}