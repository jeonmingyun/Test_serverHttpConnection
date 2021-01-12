package com.mamp.serverhttpconnection;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mamp.serverhttpconnection.Thread.HttpThread;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jObj = new JSONObject();
                try {
                    jObj.put("name", "짱구");
                    jObj.put("comment", "android request");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                new HttpThread("/api/sendData", jObj).start();
            }
        });

    }

}
