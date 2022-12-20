package com.halome.demopushnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtToken: EditText = findViewById(R.id.txtToken)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                println("Fetching FCM registration token failed $task.exception")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Toast.makeText(baseContext, "Your registration token::$token", Toast.LENGTH_SHORT).show()

            txtToken.setText(token)
        })
    }
}