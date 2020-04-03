package com.example.qr_writer_reader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.qr_read_test.*

class qrRead : CaptureActivity(), DecoratedBarcodeView.TorchListener{

    var manager : CaptureManager? = null
    var switchFlashLightButtonCheck = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_read_test)

        barcodeView.setTorchListener(this)

        manager = CaptureManager(this, barcodeView)
        manager!!.initializeFromIntent(intent, savedInstanceState)
        manager!!.decode()

    }

    override fun onPause() {
        super.onPause()
        manager!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        manager!!.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        manager!!.onDestroy()
    }

    override fun onTorchOff() {
        switchFlashLightButtonCheck = false
    }

    override fun onTorchOn() {
        switchFlashLightButtonCheck = true
    }
}