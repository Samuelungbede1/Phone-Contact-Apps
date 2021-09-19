package com.example.phonecontactreader

import android.graphics.Bitmap

data class ContactDetails (
    var name: String="",
    var mobileNumber: String="",
    var image: Bitmap?= null
)