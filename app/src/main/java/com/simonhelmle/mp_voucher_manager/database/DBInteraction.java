package com.simonhelmle.mp_voucher_manager.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.simonhelmle.mp_voucher_manager.data.DBStorage;
import com.simonhelmle.mp_voucher_manager.data.Voucher;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

public class DBInteraction {

    /*
    This class handles all interactions/communications between the app and the API. All methods within this class must be called within a new thread, separate to the main thread as per Android guidelines.
     */

    public static String createAPIrequestString(Map<String, String> params) {

        /* This method creates a request string appendix which is attached to the URI to pass parameters to the webservice/API.
         *
         *  As method input a map is expected with Key and Value as Map parameters.
         *  The key shall be equal to the requested API parameter.
         *  The value equals the respective value handed over to the API (e.g. for SQL SELECT).
         *
         *  The method returns the URI appendix as string.
         *
         * */

        StringBuilder sbParams = new StringBuilder();
        int i = 0;

        for (String key : params.keySet()) {
            try {
                if (i != 0) {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        String requestString = sbParams.toString();
        return requestString;
    }

    public static void sendRequestStringToAPI(String requestString, HttpURLConnection conn) throws IOException {

        /*
        This method transmits the actual requestString to the API. Therefore the API can process the parameters e.g. within data base query.
        This method doesnt return anything, it just sends the parameters to the webservice.
         */

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(requestString);
        wr.flush();
        wr.close();
    }

    public static void createVoucherListInStorage(HttpURLConnection conn) throws IOException {

        /*
        This method retrieves the input stream from the HTTP connection,
        creates Voucher Objects based on the retrieved JSON Arrays
        and writes the Voucher Objects into the DBStorage Class.
        From here it can be assessed centrally during runtime by other application components.
         */

        // First, the input stream is read from the open HTTP connection and written into a String.

        InputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String result = sb.toString();
        reader.close();

        // Second, the result String (JSON) is transformed into Voucher Class Objects and written into central storage location.

        Type voucherListType = new TypeToken<ArrayList<Voucher>>() {
        }.getType();

        new DBStorage().voucherList = new Gson().fromJson(result, voucherListType);

    }
}
