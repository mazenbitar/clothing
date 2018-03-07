package com.clothing.clothing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*
import android.graphics.Bitmap
import android.R.attr.data
import android.app.PendingIntent.getActivity


class TestActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "UseSparseArrays")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        floatingActionButton.setOnClickListener {
            val pickPictureIntent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPictureIntent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val selectedImage = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
            imageView7.setImageBitmap(bitmap)
        }
    }
}
