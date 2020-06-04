package com.example.lotto


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_constellation.*
import java.time.DayOfWeek
import java.util.*

class ConstellationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)

        Toast.makeText(applicationContext, "ConstellationActivity입니다", Toast.LENGTH_LONG).show()

        goResultButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getLottoNumbersFromHash(makeConstellationString(datePicker.month, datePicker.dayOfMonth))))

            intent.putExtra("constellation", makeConstellationString(datePicker.month, datePicker.dayOfMonth))

            startActivity(intent)
        }

        textView.text = makeConstellationString(datePicker.month, datePicker.dayOfMonth)

        //DatePicker의 날짜가 변화하면 별자리를 보여주는 텍스트뷰도 변경
        val calendar = Calendar.getInstance()

    }

    fun makeConstellationString(month: Int, day:Int): String{
        //전달받은 월정보와 일 정보를 기반으로 정수형태의 값을 만든다
        //Ex)1월 5일 --> 105, 11월 1일 --> 1101
        val target = "${month +1}${String.format("%02d", day)}".toInt()
        when(target){
            in 101..119 -> return "염소자리"
            in 120..218 -> return "물병자"
            in 219..320 -> return "물고기자"
            in 321..419 -> return "양자"
            in 420..520 -> return "황소자"
            in 521..621 -> return "쌍둥이자리"
            in 622..722 -> return "게자리"
            in 723..822 -> return "사자자리"
            in 823..923 -> return "리처녀자리"
            in 924..1022 -> return "리천칭자리"
            in 1023..1122 -> return "리전갈자리"
            in 1123..1124 -> return "리사수자리"
            in 1125..1231 -> return "리염소자리"
            else -> return "기타별자리"
        }
    }
}
