package com.clothing.clothing

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test1.*

class Test1Activity : AppCompatActivity() {

    @SuppressLint("ShowToast", "UseSparseArrays", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        textView4.text = TestInfo.v + " " + TestInfo.v1
    }
}
