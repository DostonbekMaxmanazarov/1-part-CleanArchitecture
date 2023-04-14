package uz.apexsoft.onepartcleanarchitecture.data.storage

import uz.apexsoft.onepartcleanarchitecture.data.storage.models.AuthenticationRequest

interface AuthStorageSharedPref {
    fun saveAuthentication(user: AuthenticationRequest): Boolean
    fun getAuthentication(): AuthenticationRequest
}