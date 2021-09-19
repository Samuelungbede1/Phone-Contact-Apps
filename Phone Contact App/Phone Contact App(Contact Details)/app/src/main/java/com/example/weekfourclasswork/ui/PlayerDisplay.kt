package com.example.weekfourclasswork.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weekfourclasswork.R
import java.util.jar.Manifest

class PlayerDisplay : AppCompatActivity() {
    lateinit var mobile: String
    var MY_PERMISSIONS_REQUEST_CALL_PHONE= 1010

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_display)
        var callBtn = findViewById<ImageView>(R.id.iv_callImageButton)

        var intent = intent
        val name = intent.getStringExtra("name")
        mobile = intent.getStringExtra("mobile").toString()
        val mail = intent.getStringExtra("mail")
        val image = intent.getIntExtra("image", 0)

        findViewById<TextView>(R.id.textView_PlayerName).text = name
        findViewById<TextView>(R.id.textView_PlayerPhone).text = mobile
        findViewById<TextView>(R.id.textView_PlayerEmail).text = mail
        findViewById<ImageView>(R.id.player_image).setImageResource(image)


        callBtn.setOnClickListener {
            //fun onClick(view: View) {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel: $mobile")
                if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(
//                            this,android.Manifest.permission.CALL_PHONE)) {
//
//
//                    } else {
                        ActivityCompat.requestPermissions(
                            this, arrayOf(android.Manifest.permission.CALL_PHONE), MY_PERMISSIONS_REQUEST_CALL_PHONE)
                  //  }

                }else

                startActivity(callIntent)


        }
    }

}


