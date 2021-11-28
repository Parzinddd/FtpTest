package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

public class ModeCommand implements Command {
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        String response = "";
        data = data.toUpperCase(Locale.ROOT);
        if (data.equals("STREAM")){
            t.setMode(ThreadController.TransferMode.STREAM);
            response = "290 set mode STREAM success";
        }else if (data.equals("BLOCK")){
            t.setMode(ThreadController.TransferMode.BLOCK);
            response = "290 set mode BLOCK success";
        }else if (data.equals("ZIP")){
            t.setMode(ThreadController.TransferMode.ZIP);
            response = "290 set mode ZIP success";
        }

        try {
            writer.write(response);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
