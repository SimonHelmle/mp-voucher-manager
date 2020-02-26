package com.simonhelmle.mp_voucher_manager.application;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.simonhelmle.mp_voucher_manager.R;
import com.simonhelmle.mp_voucher_manager.data.DBStorage;
import com.simonhelmle.mp_voucher_manager.database.DBConnection;
import com.simonhelmle.mp_voucher_manager.database.DBInteraction;

import java.io.IOException;
import java.net.HttpURLConnection;

public class BarcodeVoid extends AppCompatActivity {

    private com.simonhelmle.mp_voucher_manager.databinding.ActivityBarcodeVoidBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_void);
        binding = com.simonhelmle.mp_voucher_manager.databinding.ActivityBarcodeVoidBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        final String voucherCode = extras.getString("voucherCode");
        Log.d("voucherCode", voucherCode);

        new PostVoucherVoid().execute(voucherCode);

        new LoadAllVouchersToStorage().execute();
        Toast.makeText(getApplicationContext(), "Data reloaded", Toast.LENGTH_SHORT).show();

        binding.btnNewScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BarcodeVoid.this, BarcodeScanner.class));
            }
        });
    }

    class PostVoucherVoid extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {

            String voucherCode = strings[0];
            String requestString = DBConnection.URL_POST_VOUCHER_VOID + "?voucherCode=" + DBInteraction.encodeString(voucherCode);
            Boolean operationSuccess = false;

            try {
                HttpURLConnection conn = DBConnection.openPOSThttpConnection(requestString);
                operationSuccess = DBInteraction.postVoucherVoid(conn);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return operationSuccess;
        }

        @Override
        protected void onPostExecute(Boolean operationSuccess) {
            super.onPostExecute(operationSuccess);

            if (operationSuccess) {
                binding.statusGraphic.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_smiley_success, null));
                binding.statusGraphic.setVisibility(View.VISIBLE);
                binding.voidResult.setText("Gutschein erfolgreich entwertet.");
                binding.voucherVoidMessage.setText("Viel Spaß bei der Show!");
            } else {
                binding.statusGraphic.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_smiley_error, null));
                binding.statusGraphic.setVisibility(View.VISIBLE);
                binding.voidResult.setText("Gutschein konnte nicht entwertet werden.");
                binding.voucherVoidMessage.setText("Fehler im Vorgang. Bitte neu scannen.");
            }
        }
    }

    class LoadAllVouchersToStorage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            if (DBStorage.voucherList != null) {
                DBStorage.voucherList.clear();
            }

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
