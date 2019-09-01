package com.simx.qr.scanner


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.zxing.BarcodeFormat

import com.simx.qr.R
import com.simx.qr.common.CustomViewFinderView
import com.simx.qr.databinding.ScannerFragmentBinding
import me.dm7.barcodescanner.core.IViewFinder
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerFragment constructor(private val listener: OnScannerListener) : Fragment() {


    private lateinit var binding:ScannerFragmentBinding
    private lateinit var vm:ScannerFragmentVM
    private lateinit var zXingScannerView: ZXingScannerView
    private val zinxListener = ZXingScannerView.ResultHandler {result -> run {
        listener.onResult(result)
    }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.scanner_fragment, container,false)
        vm = ScannerFragmentVM()
        binding.lifecycleOwner = this
        binding.scannerVm = vm
        zXingScannerView = object : ZXingScannerView(this.context){
            override fun createViewFinderView(context: Context?): IViewFinder {
                return CustomViewFinderView(context!!)
            }
        }
        if (hasPermissionCamera()) initView()
        else requestPermissionCamera()
        return binding.root
    }


    private fun initView() {
        zXingScannerView.setAutoFocus(true)
        zXingScannerView.setLaserEnabled(true)
        zXingScannerView.setFormats(listOf(BarcodeFormat.QR_CODE,BarcodeFormat.CODE_128))
        zXingScannerView.setResultHandler(zinxListener)
        binding.containerQr.addView(zXingScannerView)
        zXingScannerView.startCamera()
    }

    private fun hasPermissionCamera():Boolean{
        return ActivityCompat.checkSelfPermission(this.context!!, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionCamera(){
        ActivityCompat.requestPermissions(activity!!, listOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray(), RC_CAMERA)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var granted : Boolean? = null
        when (requestCode) {
            RC_CAMERA -> {
                for (i in permissions.indices){
                    granted = grantResults[i] == PackageManager.PERMISSION_GRANTED
                }
                if (granted!!) initView()
                else requestPermissionCamera()
            }
            else -> {
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    interface OnScannerListener {
        fun onResult(result: Result )
    }
    companion object {
        private const val RC_CAMERA = 11101
        fun instance(listener: OnScannerListener) : ScannerFragment{
            var bundle =  Bundle()
            var fragment = ScannerFragment(listener)
            fragment.arguments = bundle
            return fragment
        }
    }

}
