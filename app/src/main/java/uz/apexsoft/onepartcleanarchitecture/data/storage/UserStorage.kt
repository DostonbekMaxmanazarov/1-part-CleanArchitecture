package uz.apexsoft.onepartcleanarchitecture.data.storage

import uz.apexsoft.onepartcleanarchitecture.data.storage.models.User

interface UserStorage {
    fun saveUser(user: User): Boolean
    fun getUser(): User
}