package uz.apexsoft.onepartcleanarchitecture.domain.usecase.impl

import uz.apexsoft.onepartcleanarchitecture.domain.models.UserData
import uz.apexsoft.onepartcleanarchitecture.domain.repository.UserRepository
import uz.apexsoft.onepartcleanarchitecture.domain.usecase.GetUserDataUseCase

class GetUserDataUseCaseImpl(
    private val userRepository: UserRepository
) : GetUserDataUseCase {
    override fun invoke(): UserData {
        return userRepository.getUserData()
    }
}