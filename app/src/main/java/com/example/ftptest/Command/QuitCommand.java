package com.example.ftptest.Command;

import  com.example.ftptest.Controller.ThreadController;
import  com.example.ftptest.inf.Command;

import java.io.IOException;
import java.io.Writer;

public class QuitCommand implements Command {
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        try {
            writer.write("221 goodbye.\r\n");
            writer.flush();
            writer.close();
            t.getSocket().close();
            if (!t.isActive()){
                t.getServerSocket().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
