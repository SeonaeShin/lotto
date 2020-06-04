package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext,"MainActivity입니다", Toast.LENGTH_LONG).show()

        randomCard.setOnClickListener{
            val intent = Intent(this, ResultActivity::class.java)

            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getShuffleLottoNumbers()))

            startActivity(intent)
        }

        constellationCard.setOnClickListener{
            startActivity(Intent(this, ConstellationActivity::class.java))
        }

        nameCard.setOnClickListener{
            startActivity(Intent(this, NameActivity::class.java))
        }
    }
}
