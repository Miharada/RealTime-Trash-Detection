package com.example.realclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.LoaderCallbackInterface
import org.opencv.core.Mat

class CameraActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {
    private lateinit var mRgba : Mat
    private lateinit var mGray : Mat
    private lateinit var mOpenCvCameraView : CameraBridgeViewBase

    private val mLoaderCallback = object : BaseLoaderCallback(this){
        override fun onManagerConnected(status: Int) {
            when (status){
                LoaderCallbackInterface.SUCCESS -> {
                    Log.i("Camera Activity OYYYYYY", "OpenCV is loaded MANTAP !!")
                    mOpenCvCameraView.enableView()
                }
                else -> super.onManagerConnected(status)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }

    override fun onCameraViewStarted(width: Int, height: Int) {

    }

    override fun onCameraViewStopped() {

    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat {
        TODO("Not yet implemented")
    }
}