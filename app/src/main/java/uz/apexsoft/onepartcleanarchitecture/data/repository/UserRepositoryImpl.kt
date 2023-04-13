package uz.apexsoft.onepartcleanarchitecture.data.repository

import uz.apexsoft.onepartcleanarchitecture.data.storage.UserStorage
import uz.apexsoft.onepartcleanarchitecture.data.storage.models.User
import uz.apexsoft.onepartcleanarchitecture.domain.models.SaveUserDataParam
import uz.apexsoft.onepartcleanarchitecture.domain.models.UserData
import uz.apexsoft.onepartcleanarchitecture.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {
    override fun saveUserData(saveParam: SaveUserDataParam): Boolean {
        val user = mapToStorage(saveParam = saveParam)
        return userStorage.saveUser(user)
    }

    override fun getUserData(): UserData {
        val user = userStorage.getUser()
        return mapToDomain(user = user)
    }

    private fun mapToStorage(saveParam: SaveUserDataParam): User {
        return User(firstName = saveParam.firstName, lastName = saveParam.lastName)
    }

    private fun mapToDomain(user: User): UserData {
        return UserData(firstName = user.firstName, lastName = user.lastName)
    }
}