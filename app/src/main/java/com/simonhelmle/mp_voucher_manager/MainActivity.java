package com.simonhelmle.mp_voucher_manager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.simonhelmle.mp_voucher_manager.application.BarcodeScanner;
import com.simonhelmle.mp_voucher_manager.database.DBConnection;
import com.simonhelmle.mp_voucher_manager.database.DBInteraction;
import com.simonhelmle.mp_voucher_manager.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        new LoadAllVouchersToStorage().execute();
        Toast.makeText(getApplicationContext(), "Data Base successfully connected. Data loaded.", Toast.LENGTH_SHORT).show();

        binding.btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BarcodeScanner.class));
            }
        });
    }

    class LoadAllVouchersToStorage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                HttpURLConnection conn = DBConnection.openGEThttpConnection(DBConnection.URL_GET_ALL_VOUCHERS);
                DBInteraction.createVoucherListInStorage(conn);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}