package com.huat.qi.appfirst.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Qi on 2018/6/9.
 */

public class GradeUtils {
    public GradeUtils(){
    }
    public static String connect(String ip, String grade) {
        String str = "http://" + ip
                + ":8084/game/GradeServlet?grade="+grade;
        URL url=null;
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        StringBuffer sb = null;// 线程安全
        try {
            url = new URL(str);//获得URL对象
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");//GET方式提交参数
                connection.setDoOutput(true);//设置可以向服务器读写
                connection.setDoInput(true);
                //请求成功
                if (connection.getResponseCode() == 200) {
                    inputStream = connection.getInputStream();
                    Reader reader = new InputStreamReader(inputStream, "UTF-8");
                    //打包成字符流
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String str1 = null;
                    sb = new StringBuffer();
                    while ((str1 = bufferedReader.readLine()) != null) {
                        sb.append(str1);
                    }

                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {

            e.printStackTrace();
            //关闭流很重要
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                connection.disconnect();
                connection = null;
            }

        }
        if (sb != null) {
            return new String(sb);
        }
        return "服务器异常！";

    }
}
