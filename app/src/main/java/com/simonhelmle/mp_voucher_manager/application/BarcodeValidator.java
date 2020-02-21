package com.simonhelmle.mp_voucher_manager.application;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.simonhelmle.mp_voucher_manager.R;
import com.simonhelmle.mp_voucher_manager.database.DBConnection;
import com.simonhelmle.mp_voucher_manager.database.DBInteraction;
import com.simonhelmle.mp_voucher_manager.databinding.ActivityBarcodeValidatorBinding;

import java.io.IOException;
import java.net.HttpURLConnection;

public class BarcodeValidator extends AppCompatActivity {

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "id";
    private static final String TAG_VOUCHERCODE = "voucherCode";
    private static final String TAG_VOUCHERCREATIONDATE = "voucherCreationDate";
    private static final String TAG_VOUCHERVOID = "voucherVoid";
    private static final String TAG_VOUCHERVOIDDATE = "voucherVoidDate";

    // url to get all products list
    private static String url_get_voucher_details = "http://api.simon-helmle.de/mysql_php_api/get_voucher_details.php";

    private ActivityBarcodeValidatorBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_validator);
        binding = ActivityBarcodeValidatorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initViews();

    }

    private void initViews() {

        Bundle extras = getIntent().getExtras();
        final String voucherCode = extras.getString("voucherCode");
        binding.barcodeResult.setText("Gutschein ID: " + voucherCode);

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

