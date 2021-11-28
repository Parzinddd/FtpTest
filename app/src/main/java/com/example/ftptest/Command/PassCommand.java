package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;
import com.example.ftptest.utils.ConfigRead;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import org.apache.log4j.Logger;


import java.io.IOException;
import java.io.Writer;

public class PassCommand implements Command {
    Logger logger = Logger.getLogger(PassCommand.class);
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        logger.debug("execute the pass command");
        logger.debug("the data is "+data);
        //获得用户名
        String key = ThreadController.USER.get();
        String pass = ConfigRead.users.get(key);

        String response = null;
        if(pass.equals(data)) {
            logger.debug("登录成功");
            EventBus.getDefault().post("登录成功");
            ConfigRead.loginUser.add(key);
            t.setLogin(true);
            response = "230 User "+key+" logged in";
        }
        else {
            logger.debug("登录失败，密码错误");
            response = "530   密码错误";
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
