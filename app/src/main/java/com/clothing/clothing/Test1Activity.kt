package com.clothing.clothing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Test1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        val hashmapTest = HashMap<Int, HashMap<Int, String>>()
        val hashmapInner = HashMap<Int, String>()

        hashmapInner[1] = "amman"
        hashmapTest[1] = hashmapInner
    }
}
