package uz.apexsoft.onepartcleanarchitecture.domain.repository

import uz.apexsoft.onepartcleanarchitecture.domain.models.RegistrationParam
import uz.apexsoft.onepartcleanarchitecture.domain.models.Authentication

interface AuthRepository {
    fun saveAuthentication(saveParam: RegistrationParam): Boolean
    fun getAuthentication(): Authentication
}