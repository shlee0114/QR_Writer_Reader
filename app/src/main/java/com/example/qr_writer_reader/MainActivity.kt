package com.example.qr_writer_reader

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("getKeyHash", ""+getKeyHash(this));

        QRRead.setOnClickListener {
            val test = IntentIntegrator(this)
                .setBeepEnabled(false)
                .setCaptureActivity(qrRead::class.java)
                .initiateScan()
        }

        QRWrite.setOnClickListener {
            val i = Intent(this, qrWrite::class.java)
            i.putExtra("text", qrText.text)
            startActivity(i)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    fun getKeyHash(context: Context): String? {
        val pm: PackageManager = context.getPackageManager()
        try {
            val packageInfo: PackageInfo =
                pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES)
                    ?: return null
            for (signature in packageInfo.signatures) {
                try {
                    val md: MessageDigest = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    return Base64.encodeToString(
                        md.digest(),
                        Base64.NO_WRAP
                    )
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

}
