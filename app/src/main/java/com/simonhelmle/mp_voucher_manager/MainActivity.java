package com.simonhelmle.mp_voucher_manager;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.simonhelmle.mp_voucher_manager.database.DBConnection;
import com.simonhelmle.mp_voucher_manager.database.DBInteraction;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadAllVouchersToStorage().execute();
    }

    class LoadAllVouchersToStorage extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                HttpURLConnection conn = DBConnection.openGEThttpConnection();
                DBInteraction.createVoucherListInStorage(conn);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
