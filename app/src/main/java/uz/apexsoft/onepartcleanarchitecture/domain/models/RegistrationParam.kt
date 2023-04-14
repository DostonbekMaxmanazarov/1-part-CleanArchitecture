package uz.apexsoft.onepartcleanarchitecture.domain.models

data class RegistrationParam(
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val confirmPassword: String = ""
)
