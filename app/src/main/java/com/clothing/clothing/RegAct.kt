package com.clothing.clothing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class RegAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        //bellow i'll file the governorate spinner from the database
        val url = "http://192.168.0.29/clothing/prl/governorates_select.php"
    }
}
