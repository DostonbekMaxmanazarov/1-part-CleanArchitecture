package uz.apexsoft.onepartcleanarchitecture.domain.utils

import uz.apexsoft.onepartcleanarchitecture.domain.enum.CountryCode

private const val emailRegex = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}" // xx@yy.zz
private const val uzPhoneNumberRegex = "^\\+\\d{3}\\s\\d{2}\\s\\d{3}\\s\\d{2}\\s\\d{2}$" // Uz: +998 00 123 45 67
private const val usPhoneNumberRegex = "^\\d{3}-\\d{3}-\\d{4}$" // Us: 123-456-7890

/**
 * We use this function to check whether the email is entered correctly or not
 * */
fun String.isValidationEmail(): Boolean {
    val pattern = Regex(pattern = emailRegex)
    return pattern.matches(this)
}

/**
 * We use this function to check whether the phone numbers
 * of the countries are entered correctly or not
 * @param - as a parameter, the CountryCode enum class is used to define country codes
 * */
fun String.isValidationPhoneNumber(code: CountryCode = CountryCode.UZ): Boolean {
    val pattern = when (code) {
        CountryCode.UZ -> Regex(pattern = uzPhoneNumberRegex)
        CountryCode.US -> Regex(pattern = usPhoneNumberRegex)
    }
    return pattern.matches(this)
}

/**
 * We use this function to check whether the password is entered correctly or not
 * */
fun String.isValidationPassword(): Boolean {
    if (this.length > 5) {
        if (this.any { it.isUpperCase() }) {
            if (this.any { it.isDigit() }) {
                return true
            }
        }
    }
    return false
}