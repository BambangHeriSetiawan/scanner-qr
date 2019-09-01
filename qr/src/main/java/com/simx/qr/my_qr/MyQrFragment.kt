package com.simx.qr.my_qr


import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import coil.Coil
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

import com.simx.qr.R
import com.simx.qr.databinding.MyQrFragmentBinding

class MyQrFragment : Fragment() {
    private lateinit var binding:MyQrFragmentBinding
    private lateinit var vm:MyQrFragmentVM
    private var code:String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.my_qr_fragment, container,false)
        vm = MyQrFragmentVM()
        binding.lifecycleOwner = this
        binding.myQrVm = vm
        if (!arguments?.isEmpty!!){
            code  = arguments?.getString(KEY_DATA)
            vm.code.postValue(code)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.code.observe(this, Observer { c -> run {
            if (!c.isNullOrEmpty()){
                var imageQr = generateQr(c)
                binding.imgQr.load(imageQr){
                    transformations(RoundedCornersTransformation(0.5f))
                }
            }
        } })


    }


    @Throws(WriterException::class)
    fun generateQr(value:String?): Bitmap? {
        var bitMatrix: BitMatrix? = null
        var multiFormatWriter = MultiFormatWriter()
        var barcodeEncoder = BarcodeEncoder()
        try {
            bitMatrix = multiFormatWriter.encode(value, BarcodeFormat.QR_CODE,500,500)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return barcodeEncoder.createBitmap(bitMatrix)
    }
    companion object {
        const val KEY_DATA = "key_data"
        fun instance(value:String) : MyQrFragment{
            var bundle =  Bundle()
            bundle.putString(KEY_DATA,value)
            var fragment = MyQrFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
