package com.simonhelmle.mp_voucher_manager.application;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.simonhelmle.mp_voucher_manager.R;
import com.simonhelmle.mp_voucher_manager.databinding.ActivityBarcodeValidatorBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    {
        @Override
        public void onClick (View v){

        Boolean voucherExists = false;


        new LoadAllVouchers().execute();

        try {
            voucherExists = dbStatements.validateVoucherCode(voucherCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (voucherExists) {
            System.out.println("Gutscheincode wurde in Datenbank gefunden!");
        }


    }
    }

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

    })

    // Hashmap for ListView
    voucherList =new ArrayList<HashMap<String, String>>()

            // Loading products in Background Thread

            /**
             * Background Async Task to Load all product by making HTTP Request
             * */
            View.OnClickListener()

    class LoadAllVouchersToStorage extends AsyncTask<String, String, String> {

        /**
         * getting All products from url
         */

        protected String doInBackground(String... args) {

            // 1. build parameter map to be handed over to REST api

            Map<String, String> params = new HashMap<String, String>();

            params.put(TAG_VOUCHERCODE, voucherCode);

            // 2. create HTTP request string

            StringBuilder sbParams = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                try {
                    if (i != 0) {
                        sbParams.append("&");
                    }
                    sbParams.append(key).append("=")
                            .append(URLEncoder.encode(params.get(key), "UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }

            // Proceed with HTTP connection for IO

            try {

                // 3. open HTTP connection and send request string

                String url = url_get_voucher_details;
                URL urlObj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept-Charset", "UTF-8");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                // 4. Send request to REST api.

//                    String paramsString = sbParams.toString();
//
//                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//                    wr.writeBytes(paramsString);
//                    wr.flush();
//                    wr.close();

                // 5. Receive input stream from api as response to request.

                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String result = sb.toString();
                reader.close();

                // 6. Write response into JSON JAVA Object.

                json = new JSONObject(result);

                Log.d("JSON Input", "Result from Server: " + result);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return result;
        }
    }
}

