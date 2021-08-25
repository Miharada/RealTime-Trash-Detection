package com.example.realclean

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.Window
import android.view.WindowManager
import com.example.realclean.databinding.ActivityCameraBinding
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.core.CvType
import org.opencv.core.Mat

class CameraActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {
    private lateinit var mRgba : Mat
    private lateinit var mGray : Mat
    private lateinit var mOpenCvCameraView : CameraBridgeViewBase

    private val TAG = "CameraACTIVITY WOOYY"

    private val mLoaderCallback = object : BaseLoaderCallback(this){
        override fun onManagerConnected(status: Int) {
            when (status){
                LoaderCallbackInterface.SUCCESS -> {
                    Log.i(TAG, "OpenCV is loaded MANTAP !!")
                    mOpenCvCameraView.enableView()
                }
                else -> super.onManagerConnected(status)
            }
        }
    }

    fun CameraActivity() {
        Log.i(TAG, "Instantiated new " + this.javaClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCameraBinding.inflate(layoutInflater)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(binding.root)

        binding.cameraFrameSurface.apply {
            visibility = SurfaceView.VISIBLE
            setCvCameraViewListener(this@CameraActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        if (OpenCVLoader.initDebug()){
            Log.d(TAG,"OpenCV initialization is done !!!!")
        }else{
            Log.d(TAG,"OpenCV is NOT loaded. Try Again")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,this,mLoaderCallback)
        }
    }

    override fun onPause() {
        super.onPause()
        mOpenCvCameraView.disableView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mOpenCvCameraView.disableView()
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        mRgba = Mat(height,width,CvType.CV_8UC4)
        mGray = Mat(height,width,CvType.CV_8UC1)
    }

    override fun onCameraViewStopped() {
        mRgba.release()
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat {
        if (inputFrame != null) {
            mRgba = inputFrame.rgba()
            mGray = inputFrame.gray()
        }
        return mRgba
    }
}