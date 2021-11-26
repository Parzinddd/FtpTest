package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;


public class RetrCommand implements Command {
    Logger logger = Logger.getLogger(RetrCommand.class);
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        Socket s;
        String desDir = t.getDir()+ File.separator+"data"+File.separator+data;
        logger.debug(desDir);
        File file = new File(desDir);
        System.out.println(desDir);
        if(file.exists())
        {
            try {
                writer.write("150 open ascii mode...\r\n");
                writer.flush();
                s = new Socket(t.getDataIp(), Integer.parseInt(t.getDataPort()));
                BufferedOutputStream dataOut = new BufferedOutputStream(s.getOutputStream());
                byte[] buf = new byte[1024];
                InputStream is = new FileInputStream(file);
                while(-1 != is.read(buf)) {
                    dataOut.write(buf);
                }
                dataOut.flush();
                s.close();
                writer.write("220 transfer complete...\r\n");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                writer.write("220  该文件不存在\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
