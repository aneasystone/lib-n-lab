package com.stonie;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpClientApp 
{
    public static void main( String[] args )
    {
        try {
            doGet();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void doGet () throws Exception {
        String url = "https://www.crx4chrome.com/crx/152953/";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ...");
        CloseableHttpResponse response = httpclient.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);
    }
}
