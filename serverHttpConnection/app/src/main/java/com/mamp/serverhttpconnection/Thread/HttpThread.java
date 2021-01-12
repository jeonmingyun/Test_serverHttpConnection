package com.mamp.serverhttpconnection.Thread;

import android.os.Handler;
import android.util.Log;

import com.mamp.serverhttpconnection.Constants;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpThread extends Thread {
    private String url = Constants.path;
    private String jsonMessage;
    private Handler handler;

    public HttpThread(String requestUrl, JSONObject jObj) {
        url += requestUrl;
        jsonMessage = jObj.toString();
    }

    @Override
    public void run() {
        try {
            requestPost();
//            HttpHandler handler = new HttpHandler;
//            handler.sendEmptyMessage(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*POST 동기*/
    public void requestPost() {
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("password", "post")
                    .url(url)
                    .post(RequestBody.create(MediaType.parse("application/json"), jsonMessage)) //POST로 전달할 내용 설정
                    .build();

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                ResponseBody body = response.body();
                if(body != null) {
                    Log.e("response success", body.string());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
