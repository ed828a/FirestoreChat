package com.dew.edward.firestorechat.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 *   Created by Edward on 8/17/2018.
 */
class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        if (remoteMessage != null) {
            // todo: show  notification
            Log.d("FCM", remoteMessage.data.toString())
        }
    }
}