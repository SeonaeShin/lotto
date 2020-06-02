package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_constellation.*

class ConstellationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)

        Toast.makeText(applicationContext, "ConstellationActivity입니다", Toast.LENGTH_LONG).show()


        goResultButton.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))

        }
    }
}
