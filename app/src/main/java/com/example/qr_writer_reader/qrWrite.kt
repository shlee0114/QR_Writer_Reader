package com.example.qr_writer_reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.qr_write_test.*

class qrWrite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_write_test)
        val i = intent
        val text =  i.extras!!["text"].toString()
        val multiFormatWriter = MultiFormatWriter()
        val ditMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500)
        val barcodeEncoder = BarcodeEncoder()

        qrResult.setImageBitmap(barcodeEncoder.createBitmap(ditMatrix))
    }
}