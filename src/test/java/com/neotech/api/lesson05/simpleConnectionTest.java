package com.neotech.api.lesson05;

import java.net.HttpURLConnection;
import java.net.URL;

public class simpleConnectionTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://neo-api.azurewebsites.net");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        System.out.println("Status code: " + status);
    }
}
