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


class RegAct : AppCompatActivity() {

    @SuppressLint("UseSparseArrays")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        //the bellow code related to the governorates spinner
        val hashMapGovernorate = HashMap<Int, String>()
        //here i set the first value to appear in the spinner
        hashMapGovernorate[0] = "العاصمة"
        val url = "http://10.152.204.117/clothing/prl/governorates_select.php"
        val rq = Volley.newRequestQueue(this)
        val rt = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (i in 0 until response.length()) {
                        hashMapGovernorate[response.getJSONObject(i).getInt("governorate_id")] =
                                response.getJSONObject(i).getString("governorate_name")
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

        spinner_governorate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int,
                                        id: Long) {
                Toast.makeText(this@RegAct,
                        hashMapGovernorate.keys.toTypedArray().sortedArray()[position].toString(),
                        Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // sometimes you need nothing here
            }
        }
    }
}
