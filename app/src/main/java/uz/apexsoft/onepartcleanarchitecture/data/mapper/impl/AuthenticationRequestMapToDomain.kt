package uz.apexsoft.onepartcleanarchitecture.data.mapper.impl

import uz.apexsoft.onepartcleanarchitecture.data.mapper.SingleMapper
import uz.apexsoft.onepartcleanarchitecture.data.storage.models.AuthenticationRequest
import uz.apexsoft.onepartcleanarchitecture.domain.models.Authentication

class AuthenticationRequestMapToDomain :
    SingleMapper<AuthenticationRequest, Authentication> {
    override fun invoke(value: AuthenticationRequest): Authentication {
        return Authentication(
            password = value.password, phoneNumber = value.phoneNumber
        )
    }
}