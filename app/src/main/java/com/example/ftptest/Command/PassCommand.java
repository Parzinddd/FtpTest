package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;
import org.greenrobot.eventbus.EventBus;

import  com.example.ftptest.utils.ConfigRead;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class PassCommand implements Command {

    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        EventBus.getDefault().post("execute the pass command");
        EventBus.getDefault().post("the data is "+data);
        //获得用户名
        String key = ThreadController.USER.get();
        String pass = ConfigRead.users.get(key);
        String response = null;

        if (key.equals("ANONYMOUS")){
            EventBus.getDefault().post("登录成功");
            ConfigRead.loginUser.add(key);
            t.setLogin(true);
            response = "230 User "+key+" logged in";
            String dir = t.getDir()+ File.separator+ThreadController.USER.get();
            EventBus.getDefault().post(dir);
            t.setDir(dir);
            File file = new File(dir);
            file.mkdir();
        }else {
            if(pass.equals(data)) {
                EventBus.getDefault().post("登录成功");
                ConfigRead.loginUser.add(key);
                t.setLogin(true);
                response = "230 User "+key+" logged in";
                String dir = t.getDir()+ File.separator+ThreadController.USER.get();
                t.setDir(dir);
                File file = new File(dir);
                file.mkdir();
            }
            else {
                EventBus.getDefault().post("登录失败，密码错误");
                response = "530   密码错误";
            }
        }
        try {
            writer.write(response);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
