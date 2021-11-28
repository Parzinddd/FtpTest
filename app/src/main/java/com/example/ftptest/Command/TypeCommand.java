package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

public class TypeCommand implements Command {
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        String response = "";
        data = data.toUpperCase(Locale.ROOT);
        if (data.equals("ASCII")){
            t.setType(ThreadController.TransferType.ASCII);
            response = "280 set type ASCII success";
        }else if (data.equals("BINARY")){
            t.setType(ThreadController.TransferType.BINARY);
            response = "280 set type BINARY success";
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
