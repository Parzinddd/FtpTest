package com.example.ftptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //显示日志的开关
    private Switch logSwitch;
    //确认端口号的按钮
    private Button confirmButton;
    //输入端口号的文本框
    private EditText portNumTextview;
    //显示log的卷轴（
    private ScrollView logScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirmButton = findViewById(R.id.confirm_button);
        logSwitch = findViewById(R.id.log_switch);
        portNumTextview = findViewById(R.id.editTextPortNum);
        logScrollView = findViewById(R.id.logScrollView);
        confirmButton.setOnClickListener(this);

//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Editable portnumEditable = portNumTextview.getText();
//                String portnumString = portnumEditable.toString();
//                int portnum=Integer.parseInt(portnumString);
//                try {
//                    FtpServer server = new FtpServer(portnum);
//                    server.listen();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });



//        try {
//            FtpServer server = new FtpServer(21);
//            server.listen();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void onClick(View view){
        if (view.getId()==R.id.confirm_button){
            Editable portnumEditable = portNumTextview.getText();
            String portnumString = portnumEditable.toString();
            int portnum=Integer.parseInt(portnumString);
            try {
                Log.i("njy","njy");
                FtpServer server = new FtpServer(portnum);
                Log.i("njy2","njy2");
                server.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}