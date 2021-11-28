package com.example.ftptest.Command;

import  com.example.ftptest.Controller.ThreadController;
import  com.example.ftptest.inf.Command;


import org.greenrobot.eventbus.EventBus;

import java.io.*;
import java.net.Socket;
import java.util.Objects;


public class RetrCommand implements Command {

    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        Socket s = null;
        String desDir="";
        if (Objects.equals(data, "")) {
            desDir = t.getDir() ;
        }else {
            desDir = t.getDir() + File.separator + data;
        }
        EventBus.getDefault().post(desDir);
        File file = new File(desDir);
        if(file.exists()) {
            try {
                if (file.isDirectory()) {
                    writer.write("150 open "+t.getType().toString()+"ascii mode...\r\n");
                    File files[] = file.listFiles();
                    if (files!= null) {
                        for (File file1 : files) {
                            if (file1.isDirectory()){
                                writer.write("160 "+file1.getName()+" dis\r\n");
                            }else {
                                writer.write("160 " + file1.getName() + " file\r\n");
                            }
                        }
                    }
                    writer.write("220 transfer complete...\r\n");
                    writer.flush();
                } else {
                    writer.write("150 open "+t.getType().toString()+" mode...\r\n");
                    writer.flush();
                    BufferedOutputStream dataOut;
                    if (t.isActive()) {
                        s = new Socket(t.getDataIp(), Integer.parseInt(t.getDataPort()));
                        dataOut = new BufferedOutputStream(s.getOutputStream());
                    } else {
                        s = t.getServerSocket().accept();
                        dataOut = new BufferedOutputStream(s.getOutputStream());
                    }
                    byte[] buf = new byte[1024];
                    FileInputStream is = new FileInputStream(file);
                    int bytes;
                    while ((bytes = is.read(buf)) != -1) {
                        dataOut.write(buf, 0, bytes);
                    }
                    dataOut.flush();
                    s.close();
                    writer.write("220 transfer complete...\r\n");
                    writer.flush();
                }
            }catch (Exception e){
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
