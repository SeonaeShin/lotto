package com.example.quicklocker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pref_ex.*

class PrefExActivity : AppCompatActivity() {

    val nameFieldKey= "nameField"

    val pushCheckBoxKey = "pushCheckBox"

    val preference by lazy{getSharedPreferences("PrefExActivity", Context.MODE_PRIVATE)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pref_ex)

        //저장버튼
        saveButton.setOnClickListener{
            preference.edit().putString(nameFieldKey, nameField.text.toString()).apply()

            preference.edit().putBoolean(pushCheckBoxKey, pushCheckBox.isChecked).apply()

        }

        //불러오기
        loadButton.setOnClickListener{
            nameField.setText(preference.getString(nameFieldKey,""))

            pushCheckBox.isChecked = preference.getBoolean(pushCheckBoxKey, false)
        }
    }
}