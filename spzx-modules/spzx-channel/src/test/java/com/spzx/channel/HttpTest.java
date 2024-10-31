package com.spzx.channel;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

//@SpringBootTest
public class HttpTest {

    public static void main(String[] args) throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();

//      http://192.168.25.75:9205/product/list

        HttpGet httpGet = new HttpGet("http://192.168.25.75:9205/product/list");

        CloseableHttpResponse httpResponse = httpClient.execute( httpGet );

        System.out.println( "状态码：" + httpResponse.getStatusLine().getStatusCode() );

        String resultJson = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        System.out.println( resultJson );

        httpClient.close();

    }


}

