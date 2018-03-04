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
        var url = "http://192.168.0.29/clothing/prl/governorates_select.php"
        var rq = Volley.newRequestQueue(this)
        //this hashmap to store the id and the name of the governorate from the DB
        val hashMapGovernorates = HashMap<Int, String>()
        var rt = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (index in 0 until response.length()) {
                        hashMapGovernorates[response.getJSONObject(index)
                                .getInt("governorate_id")] = response.getJSONObject(index)
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
        rq.add(rt)

        //bellow i'll file the area spinner from the database
        url = "http://192.168.0.29/clothing/prl/areas_select.php"
        //this hashmap to store the id and the name of the governorate from the DB
        val hashMapAreas = HashMap<Int, String>()
        rt = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (index in 0 until response.length()) {
                        hashMapAreas[response.getJSONObject(index)
                                .getInt("area_id")] = response.getJSONObject(index)
                                .getString("area_name")
                    }
                    val array = hashMapAreas.values.toTypedArray()
                    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                            array)
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                    spinner_area.adapter = adapter
                }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(rt)
    }
}
