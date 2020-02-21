package com.simonhelmle.mp_voucher_manager.database;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBConnection {

    private static String url_get_all_vouchers = "https://api.simon-helmle.de/mysql_php_api/test_api.php";

    public static HttpURLConnection openGEThttpConnection() throws IOException {
        String url = url_get_all_vouchers;
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

    public static HttpURLConnection openPOSThttpConnection() throws IOException {
        String url = url_get_all_vouchers;
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
