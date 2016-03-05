package com.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-24 下午4:07:53
 */
public class HttpRequestTools {

    private static final String LOCAL_CHARSET = "UTF-8"; // 默认编码

    /**
     * 参数为键值对的post请求
     * 
     * @author zhaozhineng
     * @date 2016-2-25
     */
    public static String post(String postUrl, List<NameValuePair> paramList, String charset) throws Exception {
        String responseContent = "";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(postUrl);
        // 创建参数队列
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(paramList, charset);
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("http请求响应状态非200：" + response.getStatusLine().getReasonPhrase());
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseContent = EntityUtils.toString(entity, LOCAL_CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭httpclient失败！", e);
            }
        }
        return responseContent;
    }

    /**
     * 参数为字符串的post请求
     * 
     * @author zhaozhineng
     * @date 2016-2-25
     */
    public static String post(String postUrl, String param, String charset) throws Exception {
        String responseContent = "";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(postUrl);
        try {
            StringEntity strEntity = new StringEntity(param, charset);
            httppost.setEntity(strEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("http请求响应状态非200：" + response.getStatusLine().getReasonPhrase());
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseContent = EntityUtils.toString(entity, LOCAL_CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭httpclient失败！", e);
            }
        }
        return responseContent;
    }

    /**
     * @author zhaozhineng
     * @date 2016-2-24
     */
    public static void main(String[] args) {
        String url = "http://192.168.8.245:8080/mesService_syncOutStock.action";
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("type", "house"));

        String param = "测试";
        url = "http://127.0.0.1:8080/ztles/mesService_syncOutStock.action";
        try {
            HttpRequestTools.post(url, param, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
