package com.arq.lab;

import javax.net.ssl.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InsecureHttpClient {

    // Weak: trust-all SSL context + hostname verifier
    public static String fetch(String httpsUrl) throws Exception {
        TrustManager[] trustAll = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                }
        };

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAll, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        URL url = new URL(httpsUrl);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) out.append(line).append("\n");
            return out.toString();
        }
    }
}
