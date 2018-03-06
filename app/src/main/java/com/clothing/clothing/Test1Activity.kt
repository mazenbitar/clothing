package com.clothing.clothing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_test1.*
import java.util.*

class Test1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        val hashMapTest = HashMap<Int, String>()
        //here i set the first value to appear in the spinner
        hashMapTest[0] = "العاصمة"
        val url = "http://10.152.204.117/clothing/prl/governorates_select.php"
        val rq = Volley.newRequestQueue(this)
        val rt = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    for (i in 0 until response.length()) {
                        hashMapTest[response.getJSONObject(i).getInt("governorate_id")] =
                                response.getJSONObject(i).getString("governorate_name")
                    }
                    val map = TreeMap(hashMapTest)
                    //here i sort the hashmap by keys ascending
                    val array = map.values.toTypedArray()
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                            array)
                    listView.adapter = adapter


                }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message,
                    Toast.LENGTH_LONG).show()
        })
        rq.add(rt)

        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, hashMapTest.keys.toTypedArray().sortedArray()[i].toString(),
                    Toast.LENGTH_SHORT).show()


        }
    }
}
