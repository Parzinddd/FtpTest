package com.example.ftptest.Command;


import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.Writer;

public class PortCommand implements Command {

    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        String response = "200 the port an ip have been transfered";
        try {

            String[] iAp =  data.split(",");
            String ip = iAp[0];
            String port = Integer.toString(Integer.parseInt(iAp[1]));
            System.out.println("ip is "+ip);
            EventBus.getDefault().post("ip is "+ip);
            System.out.println("port is "+port);
            EventBus.getDefault().post("port is "+port);
            t.setDataIp(ip);
            t.setDataPort(port);
            writer.write(response);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
