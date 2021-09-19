package com.example.phonecontactapplication

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize


/**This class models how our DataBase structure will look like*/
@Parcelize
data class Contacts (
    var id : String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var number: String? = null
): Parcelable
