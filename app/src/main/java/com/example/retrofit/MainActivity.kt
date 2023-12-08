package com.example.retrofit

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.RETROFIT.ProductApi
import com.example.retrofit.spinnerInterface.spinnerProductPhone
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var spinnerHelper: spinnerProductPhone
    private var selectedItem = ""
    private var selectedItemId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.texxt)
        val b = findViewById<Button>(R.id.bt)

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinnerHelper = spinnerProductPhone(this, spinner)
        spinner.onItemSelectedListener = this

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productApi = retrofit.create(ProductApi::class.java)

        b.setOnClickListener {
            if (selectedItemId == -1) {
                // обрабатывать регистр, если ни один элемент не выбран
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val product = productApi.getProductById(selectedItemId)
                runOnUiThread {
                    tv.text = product.title.toString()
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedItem = spinnerHelper.getSelectedItem()
        selectedItemId = when (selectedItem) {
            "Iphone 9" -> 1
            "Iphone X" -> 2
            "SAMSUNG Universe 9" -> 3
            "OPPO F19" -> 4
            "Huawei P30" -> 5
            else -> -1
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}