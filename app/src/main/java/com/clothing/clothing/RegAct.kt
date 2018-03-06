package com.clothing.clothing

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_reg.*
import java.util.*


@Suppress("NAME_SHADOWING")
class RegAct : AppCompatActivity() {

    @SuppressLint("UseSparseArrays")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        //the bellow code related to fill governorates spinner from the database
        val hashMapGovernorate = HashMap<Int, String>()
        //here i set the first value to appear in the spinner
        hashMapGovernorate[0] = "المحافظة"
        //the 4 lines bellow to fill the spinner withe the word "العاصمة" when there is no internet
        // connection
        val array = hashMapGovernorate.values.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                array)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner_governorate.adapter = adapter

        val url = "http://192.168.0.29/clothing/prl/governorates_select.php"
        val rq = Volley.newRequestQueue(this)
        val rt = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (i in 0 until response.length()) {
                        hashMapGovernorate[response.getJSONObject(i)
                                .getInt("governorate_id")] = response.getJSONObject(i)
                                .getString("governorate_name")
                    }
                    //here i sort the hashmap by keys ascending
                    val map = TreeMap(hashMapGovernorate)
                    val array = map.values.toTypedArray()
                    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                            array)
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                    spinner_governorate.adapter = adapter
                }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message,
                    Toast.LENGTH_LONG).show()
        })
        rq.add(rt)

        //here i declare the areas hashmap
        val hashMapAreas = HashMap<Int, String>()
        //here i set the first value to appear in the spinner
        hashMapAreas[0] = "المنطقة"

        spinner_governorate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int,
                                        id: Long) {

                if (position != 0) {
                    //the bellow code related to fill the areas spinner from the database based on the
                    // governorate selected form the governorate spinner
                    val url1 = "http://192.168.0.29/clothing/prl/areas_select.php?" +
                            "governorate_id=${hashMapGovernorate.keys.toTypedArray()
                                    .sortedArray()[position]}"
                    val rq1 = Volley.newRequestQueue(this@RegAct)
                    val rt1 = JsonArrayRequest(Request.Method.GET, url1, null,
                            Response.Listener { response ->
                                for (i in 0 until response.length()) {
                                    hashMapAreas[response.getJSONObject(i)
                                            .getInt("area_id")] = response.getJSONObject(i)
                                            .getString("area_name")
                                }
                                //here i sort the hashmap by keys ascending
                                val map = TreeMap(hashMapAreas)
                                val array = map.values.toTypedArray()
                                val adapter = ArrayAdapter(this@RegAct,
                                        android.R.layout.simple_spinner_item, array)
                                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                                spinner_area.adapter = adapter
                            }, Response.ErrorListener { error ->
                        Toast.makeText(this@RegAct, error.message, Toast.LENGTH_LONG).show()
                    })
                    rq1.add(rt1)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // sometimes you need nothing here
            }
        }
    }
}
