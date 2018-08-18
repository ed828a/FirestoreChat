package com.dew.edward.firestorechat

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dew.edward.firestorechat.service.MyFirebaseInstanceIDService
import com.dew.edward.firestorechat.util.FirestoreUtil
import com.dew.edward.firestorechat.util.RC_SIGN_IN
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar


class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        buttonSignIn.setOnClickListener {
            val intent = AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(signInProviders)
                    .setLogo(R.drawable.ic_fire)
                    .build()
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }

    private val signInProviders = listOf(
            AuthUI.IdpConfig.EmailBuilder()
                    .setAllowNewAccounts(true)
                    .setRequireName(true)
                    .build(),
            AuthUI.IdpConfig.GoogleBuilder()
                    .build()
    )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val progressDialog = indeterminateProgressDialog("Setting up your account")
                FirestoreUtil.initCurrentUserIfFirstTime {
                    startActivity(intentFor<MainActivity>()
                            .newTask().clearTask())  // this line make sure that we don't go back to the sing-in activity again.

                    val registrationToken = FirebaseInstanceId.getInstance().token
                    if (registrationToken != null)
                        MyFirebaseInstanceIDService.addTokenToFirestore(registrationToken)

                    progressDialog.dismiss()
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {

                if (response == null) return

                when (response.error?.errorCode) {
                    ErrorCodes.NO_NETWORK -> longSnackbar(constraint_layout, "No network") // longStnakbar is from Anko
                    ErrorCodes.UNKNOWN_ERROR -> longSnackbar(constraint_layout, "Unknown error")
                }
            }
        }
    }
}
