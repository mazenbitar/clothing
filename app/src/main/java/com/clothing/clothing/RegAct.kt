package com.clothing.clothing

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
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
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*


@Suppress("NAME_SHADOWING")
class RegAct : AppCompatActivity() {

    @SuppressLint("UseSparseArrays")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        //bellow is the code to capture image using the camera after pressing on the floating button
        floatingActionButton.setOnClickListener {
            val pickPictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(pickPictureIntent, 1)
        }

        //the bellow code related to fill governorates spinner from the database
        val hashMapGovernorate = HashMap<Int, String>()
        //here i set the first value to appear in the spinner
        hashMapGovernorate[0] = "المحافظة"
        //the 4 lines bellow to fill the spinner with the word "العاصمة" when there is no internet
        // connection
        var array = hashMapGovernorate.values.toTypedArray()
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                array)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner_governorate.adapter = adapter
        //bellow i'll fill the spinner with data when there is internet connection
        var url = "http://192.168.0.29/clothing/prl/governorates_select.php"
        var rq = Volley.newRequestQueue(this)
        var rt = JsonArrayRequest(Request.Method.GET, url, null,
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
        //the 4 lines bellow to fill the spinner with the word "المنطقة" when there is no internet
        // connection
        array = hashMapAreas.values.toTypedArray()
        adapter = ArrayAdapter(this@RegAct,
                android.R.layout.simple_spinner_item, array)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner_area.adapter = adapter

        //bellow i filter the areas in the areas spinner depend on the selected governorate form
        // the governorates spinner
        spinner_governorate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int,
                                        id: Long) {
                //bellow i'll show the areas on the areas spinner when there is governorate
                // selected, but if no governorate selected nothing will be shown
                if (position != 0) {
                    //bellow i'll clear the areas hashmap to fill it again with the new areas
                    // deepened on the governorate selected
                    hashMapAreas.clear()
                    //the bellow code related to fill the areas spinner from the database based on the
                    // governorate selected form the governorate spinner
                    url = "http://192.168.0.29/clothing/prl/areas_select.php?" +
                            "governorate_id=${hashMapGovernorate.keys.toTypedArray()
                                    .sortedArray()[position]}"
                    rq = Volley.newRequestQueue(this@RegAct)
                    rt = JsonArrayRequest(Request.Method.GET, url, null,
                            Response.Listener { response ->
                                for (i in 0 until response.length()) {
                                    hashMapAreas[response.getJSONObject(i)
                                            .getInt("area_id")] = response.getJSONObject(i)
                                            .getString("area_name")
                                }
                                //here i sort the hashmap by keys ascending
                                val map = TreeMap(hashMapAreas)
                                array = map.values.toTypedArray()
                                adapter = ArrayAdapter(this@RegAct,
                                        android.R.layout.simple_spinner_item, array)
                                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                                spinner_area.adapter = adapter
                            }, Response.ErrorListener { error ->
                        Toast.makeText(this@RegAct, error.message, Toast.LENGTH_LONG).show()
                    })
                    rq.add(rt)
                } else {
                    //bellow i'll show nothing on the areas spinner, because nothing been chosen
                    hashMapAreas.clear()
                    //here i set the first value to appear in the spinner
                    hashMapAreas[0] = "المنطقة"
                    array = hashMapAreas.values.toTypedArray()
                    adapter = ArrayAdapter(this@RegAct, android.R.layout.simple_spinner_item,
                            array)
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                    spinner_area.adapter = adapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // sometimes you need nothing here
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            //here i set the imageView image from the camera capture image
            imageViewStore.setImageBitmap(data?.extras.get("data") as Bitmap)
        }
    }
}
