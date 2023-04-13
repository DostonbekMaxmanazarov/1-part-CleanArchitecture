package uz.apexsoft.onepartcleanarchitecture.domain.repository

import uz.apexsoft.onepartcleanarchitecture.domain.models.SaveUserDataParam
import uz.apexsoft.onepartcleanarchitecture.domain.models.UserData

interface UserRepository {
    fun saveUserData(saveParam: SaveUserDataParam): Boolean
    fun getUserData(): UserData
}