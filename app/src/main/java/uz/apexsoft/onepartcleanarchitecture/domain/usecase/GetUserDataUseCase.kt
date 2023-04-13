package uz.apexsoft.onepartcleanarchitecture.domain.usecase

import uz.apexsoft.onepartcleanarchitecture.domain.models.UserData

interface GetUserDataUseCase {
    operator fun invoke(): UserData
}