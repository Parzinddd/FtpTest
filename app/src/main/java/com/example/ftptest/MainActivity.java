package com.example.ftptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            FtpServer server = new FtpServer(21);
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}