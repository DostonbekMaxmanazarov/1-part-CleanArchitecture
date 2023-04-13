package uz.apexsoft.onepartcleanarchitecture.domain.usecase

import uz.apexsoft.onepartcleanarchitecture.domain.models.SaveUserDataParam

interface SaveUserDataUseCase {
    operator fun invoke(param: SaveUserDataParam): Boolean
}