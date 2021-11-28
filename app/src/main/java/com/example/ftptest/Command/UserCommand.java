package com.example.ftptest.Command;
import  com.example.ftptest.Controller.ThreadController;
import  com.example.ftptest.inf.Command;
import  com.example.ftptest.utils.ConfigRead;

import java.io.IOException;
import java.io.Writer;

public class UserCommand implements Command {
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        String response = "";
        if(ConfigRead.users.containsKey(data)) {
            ThreadController.USER.set(data);
            response = "331 please input your password";
        }
        else {
            response = "501 user is not validate";
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
