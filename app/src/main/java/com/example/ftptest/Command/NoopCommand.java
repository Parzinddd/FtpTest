package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;

import java.io.IOException;
import java.io.Writer;

public class NoopCommand implements Command {
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        try {
            writer.write("220 continuous connection ");
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
