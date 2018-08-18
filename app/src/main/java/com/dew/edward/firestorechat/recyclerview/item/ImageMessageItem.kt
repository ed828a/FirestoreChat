package com.dew.edward.firestorechat.recyclerview.item

import android.content.Context
import com.dew.edward.firestorechat.R
import com.dew.edward.firestorechat.glide.GlideApp
import com.dew.edward.firestorechat.model.ImageMessage
import com.dew.edward.firestorechat.util.StorageUtil
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_image_message.*


/**
 *   Created by Edward on 8/17/2018.
 */
class ImageMessageItem(val message: ImageMessage,
                       val context: Context): MessageItem(message) {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        super.bind(viewHolder, position)
        GlideApp.with(context)
                .load(StorageUtil.pathToReference(message.imagePath))
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(viewHolder.imageView_message_image)
    }

    override fun getLayout(): Int = R.layout.item_image_message

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        return when {
            other !is ImageMessageItem -> false
            this.message != other.message -> false
            else -> true
        }
    }

    override fun equals(other: Any?): Boolean = isSameAs(other as? ImageMessageItem)
    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + context.hashCode()
        return result
    }
}