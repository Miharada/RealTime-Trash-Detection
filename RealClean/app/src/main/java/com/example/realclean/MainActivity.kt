package com.example.realclean

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.realclean.databinding.ActivityMainBinding
import org.opencv.android.OpenCVLoader

class MainActivity : AppCompatActivity() {

    companion object {
        init {
            if (OpenCVLoader.initDebug()){
                Log.d("WOOOOOY", "SUKSESSSSSSSSSS")
            }else{
                Log.d("WOOOOOY", "GAGAAALLLLLL")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenCam.setOnClickListener {
            val toCamera = Intent(this,CameraActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(toCamera)
        }
    }
}