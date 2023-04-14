package uz.apexsoft.onepartcleanarchitecture.data.repository

import uz.apexsoft.onepartcleanarchitecture.data.mapper.SingleMapper
import uz.apexsoft.onepartcleanarchitecture.data.storage.AuthStorageSharedPref
import uz.apexsoft.onepartcleanarchitecture.data.storage.models.AuthenticationRequest
import uz.apexsoft.onepartcleanarchitecture.domain.models.Authentication
import uz.apexsoft.onepartcleanarchitecture.domain.models.RegistrationParam
import uz.apexsoft.onepartcleanarchitecture.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authStorage: AuthStorageSharedPref,
    private val saveAuthParamMapToStorage: SingleMapper<RegistrationParam, AuthenticationRequest>,
    private val authRequestMapToDomain: SingleMapper<AuthenticationRequest, Authentication>
) : AuthRepository {

    override fun saveAuthentication(saveParam: RegistrationParam): Boolean {
        val authentication = saveAuthParamMapToStorage(value = saveParam)
        return authStorage.saveAuthentication(authentication)
    }

    override fun getAuthentication(): Authentication {
        val authentication = authStorage.getAuthentication()
        return authRequestMapToDomain(value = authentication)
    }
}