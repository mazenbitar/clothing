package com.clothing.clothing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val url = "http://10.152.204.117/clothing/prl/governorates_select.php"
        val rq = Volley.newRequestQueue(this)
        val rt = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    Toast.makeText(this, response.getJSONArray(0).toString(),
                            Toast.LENGTH_LONG).show()

                }, Response.ErrorListener { })
    }
}
