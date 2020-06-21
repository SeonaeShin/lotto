package com.example.quicklocker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class PrefFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pref_fragment)

//        FragmentManager.beginTransaction().replace(android.R.id.content, MyPrefFragment()).commit()
    }

//    class MyPrefFragment : PreferenceFragment(){
//        override fun onCreate(savedInstanceState: Bundle?){
//            super.onCreate(savedInstanceState)
//
//            addPreferencesFromResource(R.xml.ex_pref)
//        }
//    }
}