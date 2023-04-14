package uz.apexsoft.onepartcleanarchitecture.data.mapper.impl

import uz.apexsoft.onepartcleanarchitecture.data.mapper.SingleMapper
import uz.apexsoft.onepartcleanarchitecture.data.storage.models.AuthenticationRequest
import uz.apexsoft.onepartcleanarchitecture.domain.models.RegistrationParam

class SaveAuthenticationParamMapToStorage : SingleMapper<RegistrationParam, AuthenticationRequest> {
    override fun invoke(value: RegistrationParam): AuthenticationRequest {
        return AuthenticationRequest(
            email = value.email,
            password = value.password,
            phoneNumber = value.phoneNumber,
        )
    }
}