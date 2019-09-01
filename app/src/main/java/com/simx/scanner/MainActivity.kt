package com.simx.scanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.simx.qr.scanner.ScannerFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, ScannerFragment.instance(object : ScannerFragment.OnScannerListener {
            override fun onResult(result: com.google.zxing.Result) {
                Toast.makeText(this@MainActivity,result.text,Toast.LENGTH_SHORT).show()
            }
        })).commit()
    }
}
