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

        floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }

        button4.setOnClickListener {
            val databaseReference: DatabaseReference? = null
            val contentResolver = contentResolver

            val fileExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver
                    .getType(pathUri))
            val storageReference = FirebaseStorage.getInstance().reference.child("Profileimgs/" +
                    System.currentTimeMillis().toString() + "." + fileExtension)

            storageReference.putFile(this.pathUri!!).addOnSuccessListener { taskSnapshot ->
                databaseReference?.child(databaseReference.push().key)?.setValue(
                        "image", taskSnapshot.downloadUrl.toString()
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            //here i store the image uri
            pathUri = data?.data

            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)
            imageView7.setImageBitmap(bitmap)
        }
    }
}
