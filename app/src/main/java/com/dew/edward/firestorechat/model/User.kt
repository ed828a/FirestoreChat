package com.dew.edward.firestorechat.model


/**
 *   Created by Edward on 8/15/2018.
 */
// variable name must match map function for serialization.
data class User(val name: String,
                val bio: String,
                val profilePicturePath: String?,
                val registrationTokens: MutableList<String>) {
    constructor(): this("", "", null, mutableListOf())
}