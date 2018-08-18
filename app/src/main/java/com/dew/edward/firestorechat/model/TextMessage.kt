package com.dew.edward.firestorechat.model

import com.dew.edward.firestorechat.util.MessageType
import java.util.*


/**
 *   Created by Edward on 8/15/2018.
 */
data class TextMessage(val text: String,
                       override val time: Date,
                       override val senderId: String,
                       override val recipientId: String,
                       override val senderName: String,
                       override val type: String = MessageType.TEXT) : Message {

    constructor() : this("", Date(0), "", "", "")

}