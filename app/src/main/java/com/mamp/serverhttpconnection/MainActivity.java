package com.mamp.serverhttpconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mamp.serverhttpconnection.Task.NetworkTask;
import com.mamp.serverhttpconnection.Task.SendDataSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkTask networkTask = new NetworkTask("/api/sendData") {

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        Log.e("task", s);
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                };
                SendDataSet sds1 = new SendDataSet("name", "짱구");
                SendDataSet sds2 = new SendDataSet("comment", "android request");
                networkTask.execute(sds1, sds2);
            }
        });
//        NetworkTask networkTask = new NetworkTask("/api/sendData") {
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//
//                Log.e("task", s);
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
//            }
//        };
//        SendDataSet sds1 = new SendDataSet("name", "짱구");
//        SendDataSet sds2 = new SendDataSet("comment", "android request");
//        networkTask.execute(sds1, sds2);


    }
}
