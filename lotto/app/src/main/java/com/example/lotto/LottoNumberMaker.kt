package com.example.lotto

import java.text.SimpleDateFormat
import java.util.*

object LottoNumberMaker {

    fun getRandomLottoNumbers(): MutableList<Int>{

        //무작위로 생성된 로번호를 저장할 가변 리스트 생성
        val lottoNumbers = mutableListOf<Int>()

        //6번 반복하는 for문
        for(i in 1..6){
            //랜덤한 번호를 임시로 저장할 변수를 생성
            var number = 0
            do{
                number = getRandomLottoNumber()
            }while(lottoNumbers.contains(number))

            lottoNumbers.add(number)
        }
        return lottoNumbers
    }

    //random 1~45 numbers
    fun getRandomLottoNumber(): Int{
        return Random().nextInt(45)+1
    }

    //shuffle
    fun getShuffleLottoNumbers(): MutableList<Int>{
        val list = mutableListOf<Int>()

        for(number in 1..45){
            list.add(number)
        }

        list.shuffle()

        return list.subList(0,6)
    }

    fun getLottoNumbersFromHash(name: String): MutableList<Int> {
        val list = mutableListOf<Int>()

        for(number in 1..45){
            list.add(number)
        }

        val targetString = SimpleDateFormat("yyyy-mm-dd", Locale.KOREA).format(Date()) + name
        list.shuffle(Random(name.hashCode().toLong()))

        return list.subList(0, 6)
    }
}