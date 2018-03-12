package com.clothing.clothing

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.webkit.MimeTypeMap
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    //declare variable to store image uri
    private var pathUri: Uri? = null

    @SuppressLint("SetTextI18n", "UseSparseArrays")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        button_register.setOnClickListener {
            var edit = editText.text

            val k = Intent(this, Test1Activity::class.java)
            startActivity(k)
        }


    }


}
