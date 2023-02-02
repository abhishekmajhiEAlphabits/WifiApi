package com.example.wifiapi;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WiFiManager {

    private WifiManager wifiManager;
    ConnectivityManager conManager;
    NetworkInfo networkInfo;
    Boolean isWifiConnected;
    Context appContext;


    WiFiManager(Context context) {
        appContext = context;
        wifiManager = (WifiManager) appContext.getSystemService(Context.WIFI_SERVICE);
        conManager = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        isWifiConnected = networkInfo != null && networkInfo.isConnected();
    }

    //method called on connect to wifi
    public void connectToWifi(final String networkSSID, final String networkPassword) {

        if (networkSSID != null && networkPassword != null) {
            connect(networkSSID, networkPassword);
        } else if (networkSSID == null && networkPassword == null) {
            Toast.makeText(appContext, "Enter Wifi credentials", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(appContext, "Enter Wifi credentials", Toast.LENGTH_LONG).show();
        }


    }

    private void connect(final String networkSSID, final String networkPassword) {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        if (wifiManager.isWifiEnabled()) {
            Toast.makeText(appContext, "Connecting..", Toast.LENGTH_SHORT).show();
        }

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = String.format("\"%s\"", networkSSID);
        conf.preSharedKey = String.format("\"%s\"", networkPassword);
        int netId = wifiManager.addNetwork(conf);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }

    //method called on disconnect
    public void disconnectWifi() {
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(appContext, "Wifi not enabled", Toast.LENGTH_LONG).show();

        }

        int networkId = wifiManager.getConnectionInfo().getNetworkId();
        wifiManager.removeNetwork(networkId);
        wifiManager.saveConfiguration();
        wifiManager.disconnect();
        wifiManager.setWifiEnabled(false);
        Toast.makeText(appContext, "Wifi Disconnected", Toast.LENGTH_LONG).show();
    }

}
