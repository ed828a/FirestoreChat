package com.dew.edward.firestorechat.model

import com.dew.edward.firestorechat.util.MessageType
import java.util.*


/**
 *   Created by Edward on 8/17/2018.
 */
data class ImageMessage(val imagePath: String,
                        override val time: Date,
                        override val senderId: String,
                        override val recipientId: String,
                        override val senderName: String,
                        override val type: String = MessageType.IMAGE) : Message {

    constructor() : this("", Date(0), "", "", "")

}