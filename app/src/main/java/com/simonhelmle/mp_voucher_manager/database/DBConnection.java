package com.simonhelmle.mp_voucher_manager.database;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBConnection {

    /*
    Subsequently all constants are defined whereas each constant/URL represents the respective API capability.

    URL_GET_ALL_VOUCHERS: Retrieves the entire voucher database with all rows and all columns.
    URL_POST_VOUCHER_VOID: Needs a voucher code as input parameter. Sets this voucher to void.
    URL_POST_NEW_VOUCHER: Creates a new voucher in data base which is not void.
     */
    public static final String URL_GET_ALL_VOUCHERS = "https://api.simon-helmle.de/mp-voucher-manager/get_all_vouchers.php";
    public static final String URL_POST_VOUCHER_VOID = "https://api.simon-helmle.de/mp-voucher-manager/post_voucher_void.php";
    public static final String URL_POST_NEW_VOUCHER = "https://api.simon-helmle.de/mp-voucher-manager/post_new_voucher.php";

    public static HttpURLConnection openGEThttpConnection(String url) throws IOException {

        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept-Charset", "UTF-8");

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);

        conn.connect();

        return conn;
    }

    public static HttpURLConnection openPOSThttpConnection(String url) throws IOException {

        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept-Charset", "UTF-8");

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);

        conn.connect();

        return conn;
    }
}
