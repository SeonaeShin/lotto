package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    //로또 1번 공 이미지의 아이디를 사용
    val lottoImageStartId = R.drawable.ball_01

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        Toast.makeText(applicationContext,"ResultActivity입니다", Toast.LENGTH_LONG).show()

       //전달받은 결과 배열을 가져온다
       val result = intent.getIntegerArrayListExtra("result")

       result?.let {
           updateLottoBallImage(result.sortedBy { it})
       }
    }

    fun updateLottoBallImage(result: List<Int>){
        //결과 사이즈가 6개 미만인 경우 에러가 발생할 수 있으니 바로 레턴
        if(result.size < 6 ) return

        imageView01.setImageResource(lottoImageStartId+(result[0]-1))
        imageView02.setImageResource(lottoImageStartId+(result[1]-1))
        imageView03.setImageResource(lottoImageStartId+(result[2]-1))
        imageView04.setImageResource(lottoImageStartId+(result[3]-1))
        imageView05.setImageResource(lottoImageStartId+(result[4]-1))
        imageView06.setImageResource(lottoImageStartId+(result[5]-1))

    }
}
