package com.dew.edward.firestorechat.model

import java.util.*


/**
 *   Created by Edward on 8/15/2018.
 */
interface Message {
    val time: Date
    val senderId: String
    val recipientId: String
    val senderName: String
    val type: String
}