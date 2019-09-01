package com.simx.scanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.Result
import com.simx.qr.scanner.ScannerFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vp_qr.adapter = QrPageAdater(supportFragmentManager, object : ScannerFragment.OnScannerListener {
            override fun onClose() {
                finish()
            }

            override fun onResult(result: Result) {
                Toast.makeText(this@MainActivity, result.text, Toast.LENGTH_SHORT).show()
            }
        }, "Testing")
        tab_qr.setupWithViewPager(vp_qr,true)
    }

}
