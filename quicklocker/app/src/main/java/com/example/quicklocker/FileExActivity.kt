package com.example.quicklocker

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_file_ex.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
//import java.util.jar.Manifest
import android.Manifest

class FileExActivity : AppCompatActivity() {

    // 데이터 저장에 사용할 파일 이름
    val filename = "data.txt"
    var granted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_ex)
        //권한체크
        checkPermission()

        //저장 버튼 클릭시
        saveButton.setOnClickListener {
            //textFiled 현재 텍스트 가져옴
            val text = textField.text.toString()
            when{
                //텍스트 비어 있는 경우 오류 메시지
                TextUtils.isEmpty(text) -> {
                    Toast.makeText(applicationContext,"텍스트가 비어있습니다",Toast.LENGTH_LONG).show()
                }
                !isExternalStorageWritable() -> {
                    Toast.makeText(applicationContext,"외부장치가 없니다",Toast.LENGTH_LONG).show()
                }
                else -> {
                    //내부 저장소 파일에 저장하는 함수 호출
//                    saveToInnerStorage(text, filename)
                    //외부 저장소에 파일 저장
//                    saveToExternalStorage(text, filename)
                    //외부저장소 /sdcard/data.txt에 데이타를 저장
                    saveToExternalCustomDirectory(text)
                }
            }
        }

        loadButton.setOnClickListener{
            try{
                //textField의 텍스트를 불러온 텍스트로 설정한다다
//                textField.setText(loadFromInnerStorage(filename))

//                textField.setText(loadFromExternalStorage(filename))

                textField.setText(loadFromExternalCustomDirectory())
            } catch (e: FileNotFoundException){
                Toast.makeText(applicationContext,"저장된 텍스트가 없습니",Toast.LENGTH_LONG).show()
            }
        }
    }

    // 내부저장소 파일의 텍스트를 저장한다.
    fun saveToInnerStorage(text: String, filename:String){
        //내부 저장소의 전달된 파일이름의 파일 출력 스트림을 가져온다
        val fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
        //파일 출력 스트림에 text 를 바이트로 변환하여 write한다
        fileOutputStream.write(text.toByteArray())
        Toast.makeText(applicationContext,"저장되었습니다",Toast.LENGTH_LONG).show()
        //파일 출력 스트림을 닫는다
        fileOutputStream.close()
    }

    // 내부 저장소 파일의 텍스트를 불러온다
    fun loadFromInnerStorage(filename: String): String{
        //내부저장소의 전달된 파일이름의 파일 입력 스트림을 가져온다
        val fileInputStream = openFileInput(filename)
        //파일의 저장된 내용을 읽어 String 형태로 불러온다
        return fileInputStream.reader().readText()
    }

    //외부 저장장치 사용할 수 있고 쓸 수 있는지 체크하는 함수
    fun isExternalStorageWritable():Boolean{
        when{
            //외부저장장치 상태가 media_monted 면 사용 가능
            Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED -> return true
            else -> return false
        }
    }

    //권한요청시 사용할 요청코
    val MY_PERMISSION_REQUEST = 999

    //드권한 체크 및 요청함수
    fun checkPermission(){
        val permissionCheck = ContextCompat.checkSelfPermission(this@FileExActivity,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
        when{
            permissionCheck != PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(applicationContext,"not supposed to be approached",Toast.LENGTH_LONG).show()
                //권한을 요청
                ActivityCompat.requestPermissions(
                    this@FileExActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSION_REQUEST
                )
                Toast.makeText(applicationContext,"ask to be approached, ->${granted}",Toast.LENGTH_LONG).show()
            } else ->{
            Toast.makeText(applicationContext,"xxx->${granted}",Toast.LENGTH_LONG).show() }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            MY_PERMISSION_REQUEST -> {
                when {
                    grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                        //권한 요청 성
                        granted = true
                    }
                    else -> {
                        //사용자가 권한 허용 안함
                        granted = false
                    }
                }
            }
        }
    }

    fun saveToExternalCustomDirectory(text: String, filepath: String = "/sdcard/data.txt"){
        when{
            //권한이 있는 경우
            granted -> {
                //파라미터로 전달받은 경로의 파일의 출력 스트림 객체를 생성
                val fileOutputStream = FileOutputStream(File(filepath))
                fileOutputStream.write(text.toByteArray())
                fileOutputStream.close()
                //권한 없는 경우
            } else -> {


            Toast.makeText(applicationContext, "권한이 없습니다 ", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun loadFromExternalCustomDirectory(filepath: String = "/sdcard/data.txt") : String {
        when {
            granted -> {
                return FileInputStream(File(filepath)).reader().readText()
            }
            else -> {
                Toast.makeText(applicationContext, "권한이 없습니다 ", Toast.LENGTH_LONG).show()
                return ""
            }
        }
    }

//    //외부 저장장치에서 앱 전용데이터로 사용할 파일 객체를 반환하는 함수
//    @RequiresApi(Build.VERSION_CODES.KITKAT)
//    fun getAppDataFileFromExternalStroage(filename:String): File {
//        //kitkat버전 부터는 앱전용 디렉토리의 디엑토리 타입 상수인 environment.Directory_document를 지원
//        var dir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//                getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
//            } else {
//            //하위 버전에서는 직접 디렉토리 이름을 입력
//            File(Environment.getExternalStorageState().absolutePath+"/Documents")
//        }
//        //디렉토리의 경로중 없는 디렉토리가 있다면 생성한다
//        dir?.mkdirs()
//        return File("${dir.absolutePath}${File.separator}${filename}")
//    }
//
//    //외부저장소 앱 전용 디렉토리에 파일로 저장하는 함수
//    @RequiresApi(Build.VERSION_CODES.KITKAT)
//    fun saveToExternalStorage(text:String, filename:String){
//        val fileOutputStream = FileOutputStream(getAppDataFileFromExternalStroage(filename))
//        fileOutputStream.write(text.toByteArray())
//        fileOutputStream.close()
//    }
//
//    //외부저장소 앱 전용 디렉토리에tj 파일데이터를 불러오 함수
//    @RequiresApi(Build.VERSION_CODES.KITKAT)
//    fun loadFromExternalStorage(filename:String): String{
//        return FileInputStream(getAppDataFileFromExternalStroage(filename)).reader().readText()
//    }
}