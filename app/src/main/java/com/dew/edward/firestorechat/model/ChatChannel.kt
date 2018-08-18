package com.dew.edward.firestorechat.model


/**
 *   Created by Edward on 8/15/2018.
 */
data class ChatChannel(val userIds: MutableList<String>) {
    constructor(): this(mutableListOf())
}