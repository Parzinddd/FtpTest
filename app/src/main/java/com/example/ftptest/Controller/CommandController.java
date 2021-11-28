package com.example.ftptest.Controller;

import  com.example.ftptest.inf.Command;
import  com.example.ftptest.Command.*;

public class CommandController {
    public static Command createCommand(String order) {
        order = order.toUpperCase();
        switch(order) {
            case "USER":return new UserCommand();

            case "PASS":return new PassCommand();

            case "MYPORT":return new PortCommand();

            case "QUIT":return new QuitCommand();

            case "RETR":return new RetrCommand();

            case "TYPE":return new TypeCommand();

            case "STOR":return new StorCommand();

            case "PASV":return new PasvCommand();

            case "MODE":return new ModeCommand();

            case "STRU":return new StruCommand();

            default :return new NoopCommand();
        }

    }
}
