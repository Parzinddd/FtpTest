package com.example.ftptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private TextView logTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        logTextView = findViewById(R.id.logText);
        confirmButton = findViewById(R.id.confirm_button);
        logSwitch = findViewById(R.id.log_switch);
        portNumTextview = findViewById(R.id.editTextPortNum);
        logScrollView = findViewById(R.id.logScrollView);
        confirmButton.setOnClickListener(this);

    }

    public void onClick(View view){
        if (view.getId()==R.id.confirm_button){
            Editable portnumEditable = portNumTextview.getText();
            String portnumString = portnumEditable.toString();
            int portnum=Integer.parseInt(portnumString);
            EventBus.getDefault().post("即将开启服务器");
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Log.i("njy","njy");
//                        EventBus.getDefault().post("登录成功");
                        FtpServer server = new FtpServer(portnum);
                        Log.i("njy2","njy2");
                        server.listen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(String s) {

        logTextView.setText(s);

    }
}