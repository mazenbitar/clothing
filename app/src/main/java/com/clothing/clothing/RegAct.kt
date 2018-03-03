package com.clothing.clothing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_reg.*
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_items.*


class RegAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        //bellow i'll file the governorate spinner from the database
        /*val url = "http://192.168.0.29/clothing/prl/governorates_select.php"
        val rq = Volley.newRequestQueue(this)
        //this hashmap to store the id and the name of the governorate from the DB
        val hashMapGovernorates = HashMap<Int, String>()
        //here i set the first value of the hashmap to make appears as the first value in the
        // dropdown list
        hashMapGovernorates[0] = "اختر العاصمة"
        val rt = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (index in 0 until response.length()) {
                        hashMapGovernorates[response.getJSONObject(0)
                                .getInt("governorate_id")] = response.getJSONObject(0)
                                .getString("governorate_name")
                    }
                    val array = hashMapGovernorates.values.toTypedArray()
                    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                            array)
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                    spinner_governorate.adapter = adapter
                }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(rt)*/

        val spinnerArray = ArrayList<String>()
        spinnerArray.add("item1")
        spinnerArray.add("item2")
        val adapter = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, spinnerArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_governorate.adapter = adapter
    }
}
