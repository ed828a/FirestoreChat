package com.dew.edward.firestorechat.recyclerview.item

import android.content.Context
import com.dew.edward.firestorechat.R
import com.dew.edward.firestorechat.model.TextMessage
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_text_message.*


/**
 *   Created by Edward on 8/15/2018.
 */
class TextMessageItem(val message: TextMessage,
                      val context: Context): MessageItem(message) {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView_message_text.text = message.text
        super.bind(viewHolder, position)
    }

    override fun getLayout(): Int = R.layout.item_text_message

    // those two function eliminate message's blinking.
    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        return when {
            other !is TextMessageItem -> false
            this.message != other.message -> false
            else -> true
        }
//        if (other !is TextMessageItem)
//            return false
//        if (this.message != other.message)
//            return false
//        return true
    }

    override fun equals(other: Any?): Boolean = isSameAs(other as? TextMessageItem)
    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + context.hashCode()
        return result
    }
}