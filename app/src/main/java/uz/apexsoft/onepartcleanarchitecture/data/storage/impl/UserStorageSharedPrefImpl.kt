package uz.apexsoft.onepartcleanarchitecture.data.storage.impl

import android.content.Context
import uz.apexsoft.onepartcleanarchitecture.data.storage.UserStorage
import uz.apexsoft.onepartcleanarchitecture.data.storage.models.User

private const val LAST_NAME = "last_name"
private const val FIRST_NAME = "first_name"
private const val DEFAULT_LAST_NAME = "empty last name"
private const val DEFAULT_FIRST_NAME = "empty first name"
private const val SHARED_PREFS_NAME = "shared_prefs_name"

class UserStorageSharedPrefImpl(context: Context) : UserStorage {
    private val sharedPreference = context.getSharedPreferences(
        SHARED_PREFS_NAME, Context.MODE_PRIVATE
    )

    override fun saveUser(user: User): Boolean {
        sharedPreference.edit().putString(FIRST_NAME, user.firstName).apply()
        sharedPreference.edit().putString(LAST_NAME, user.lastName).apply()
        return true
    }

    override fun getUser(): User {
        val firstName = sharedPreference.getString(
            FIRST_NAME, DEFAULT_FIRST_NAME
        ) ?: DEFAULT_FIRST_NAME

        val lastName = sharedPreference.getString(
            LAST_NAME, DEFAULT_LAST_NAME
        ) ?: DEFAULT_LAST_NAME

        return User(firstName = firstName, lastName = lastName)

    }
}