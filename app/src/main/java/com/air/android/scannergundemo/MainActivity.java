package com.air.android.scannergundemo;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ScannerGunManager.OnScanListener {

    TextView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcodeView = findViewById(R.id.barcode);

        ScannerGunManager.getInstance().setScanListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }

        boolean ret = super.onKeyDown(keyCode, event);
        ScannerGunManager.getInstance().dispatchKeyEvent(keyCode, event);
        return ret;
    }

    @Override
    public void onResult(final String code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                barcodeView.setText(code);
            }
        });
    }
}