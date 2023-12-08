package com.example.retrofit.spinnerInterface

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class spinnerProductPhone(private val context: Context, private val spinner: Spinner) {
    val listitem = listOf("Iphone 9","Iphone X","SAMSUNG Universe 9","OPPO F19","Huawei P30")

    init {
        val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, listitem)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemIdAtPosition(position).toString()
                // Вызывайте метод или передавайте данные в другие классы, если требуется
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Если нет выбора, выполните действия, например, показать "воздух"
            }
        }

    }
    fun getSelectedItem(): String {
        return spinner.selectedItem.toString()
    }
}