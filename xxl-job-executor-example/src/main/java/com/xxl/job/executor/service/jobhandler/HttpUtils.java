package com.xxl.job.executor.service.jobhandler;

import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author sunlaihui
 */
public class HttpUtils {
    public static String submitPostData(String strUrlPath,Map<String, String> params, String encode) {

        byte[] data = getRequestData(params, encode).toString().getBytes();
        try {

            //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
            URL url = new URL(strUrlPath);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);

            int response = httpURLConnection.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                return dealResponseResult(inputStream);
            }
        } catch (IOException e) {
            return "err: " + e.getMessage().toString();
        }
        return "-1";
    }
    public static String getData(String strUrlPath){
        HttpURLConnection httpURLConnection=null;
        try {
            URL url = new URL(strUrlPath);

            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("GET");

            int response = httpURLConnection.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                return dealResponseResult(inputStream);
            }
        } catch (IOException e) {
            return "err: " + e.getMessage().toString();
        }finally {
            if(httpURLConnection!=null) {
                httpURLConnection.disconnect();
            }
        }
        return "-1";
    }
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    public static void main(String[] args){
        List<LocationVO> locationVOList=new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        LocationVO locationVO=new LocationVO();
        locationVO.setLat("11");
        locationVO.setLng("34");
        locationVO.setBearingValue(12F);
        locationVO.setSpeedValue(13D);
        locationVO.setCreateTime(calendar.getTimeInMillis());
        locationVOList.add(locationVO);
        LocationVO locationVO1=new LocationVO();
        locationVO1.setLat("555");
        locationVO1.setLng("45");
        locationVO1.setBearingValue(12F);
        locationVO1.setSpeedValue(13D);
        locationVO1.setCreateTime(calendar.getTimeInMillis()+200);
        locationVOList.add(locationVO1);
        JSONArray jsonArray = new JSONArray(locationVOList);

        Map<String, String> params = new HashMap<String, String>();
        params.put("deviceId", "testNode");
        params.put("t", "gps");
        params.put("data", jsonArray.toString());
        String resultData=HttpUtils.submitPostData("http://localhost:8090/gps",params,"utf-8");
        System.out.println(resultData);
       String result= HttpUtils.getData("http://localhost:8090/lasted?deviceId=testNode");
       System.out.println(result);

    }

}
