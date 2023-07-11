package com.example.wifiapi


import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.example.wifiapi.WiFiManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.wifiapi.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var networkSSID: String? = null
    var networkPassword: String? = null
    private var edtSSID: EditText? = null
    private var edtPassword: EditText? = null
    private var btnConnect: Button? = null
    private var btnDisconnect: Button? = null
    var wiFiManager: WiFiManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (wiFiManager == null) {
            wiFiManager = WiFiManager(applicationContext)
        }
        initialisation()
        onClickEvents()
    }

    private fun initialisation() {
        edtSSID = findViewById<View>(R.id.editTextSSID) as EditText
        edtPassword = findViewById<View>(R.id.editTextPassword) as EditText
        btnConnect = findViewById<View>(R.id.connectWifiButton) as Button
        btnDisconnect = findViewById<View>(R.id.disoconnectButton) as Button
    }

    private fun onClickEvents() {
        btnConnect!!.setOnClickListener(this)
        btnDisconnect!!.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.connectWifiButton) {
            networkSSID = edtSSID!!.text.toString()
            networkPassword = edtPassword!!.text.toString()
            Log.d("TAG", networkSSID + networkPassword)
            wiFiManager!!.connectToWifi(networkSSID, networkPassword)
        }
        if (view.id == R.id.disoconnectButton) {
            wiFiManager!!.disconnectWifi()
        }
    }
}