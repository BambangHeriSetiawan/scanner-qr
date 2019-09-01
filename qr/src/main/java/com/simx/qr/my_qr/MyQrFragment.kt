package com.simx.qr.my_qr


import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

import com.simx.qr.R
import com.simx.qr.databinding.MyQrFragmentBinding

class MyQrFragment : Fragment() {
    private lateinit var binding:MyQrFragmentBinding
    private lateinit var vm:MyQrFragmentVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.my_qr_fragment, container,false)
        vm = MyQrFragmentVM()
        binding.lifecycleOwner = this
        binding.myQrVm = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    @Throws(WriterException::class)
    fun generateQr(value:String): Bitmap? {
        var bitMatrix: BitMatrix? = null
        try {
            bitMatrix = MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE,350,250, null)
        }catch (e: Exception){
            Log.e("MyQrFragment","generateQr -> ${e.message}")
        }
        val bitMatrixWidth = bitMatrix?.getWidth()

        val bitMatrixHeight = bitMatrix?.getHeight()

        val pixels = IntArray(bitMatrixWidth!! * bitMatrixHeight!!)

        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix?.get(x, y)!!) resources.getColor(android.R.color.black) else resources.getColor(android.R.color.white)
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888)

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }
    companion object {
        const val KEY_DATA = "key_data"
        fun instance() : MyQrFragment{
            var bundle =  Bundle()
            var fragment = MyQrFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
