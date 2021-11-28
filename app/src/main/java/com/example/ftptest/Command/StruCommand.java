package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

public class StruCommand implements Command {
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        String response = "";
        data = data.toUpperCase(Locale.ROOT);
        if (data.equals("FILE")){
            response = "295 set structure FILE success";
        }else if (data.equals("RECORD")){
            response = "295 set structure RECORD success";
        }else if (data.equals("PAGE")){
            response = "295 set structure PAGE success";
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
