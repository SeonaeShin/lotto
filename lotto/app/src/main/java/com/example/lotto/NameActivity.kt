package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*
import java.util.*
import kotlin.collections.ArrayList


class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        Toast.makeText(applicationContext, "NameActivity입니다", Toast.LENGTH_LONG).show()

        goButton.setOnClickListener {

            if(TextUtils.isEmpty(editText.text.toString())){
                Toast.makeText(applicationContext, "이름을 입력하세요", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            var intent = Intent(this, ResultActivity::class.java)

            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getLottoNumbersFromHash(editText.text.toString())))
            intent.putExtra("name", editText.text.toString())

            startActivity(intent)

        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
