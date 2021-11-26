package com.example.ftptest.inf;
import com.example.ftptest.Controller.ThreadController;


import java.io.Writer;

public interface Command {

    public void getResult(String data, Writer writer, ThreadController t);

}
