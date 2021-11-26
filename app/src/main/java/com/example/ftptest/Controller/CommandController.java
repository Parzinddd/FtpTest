package com.example.ftptest.Controller;

import com.example.ftptest.Command.*;
import com.example.ftptest.inf.Command;


public class CommandController {
    public static Command createCommand(String order) {

        order = order.toUpperCase();
        switch(order)
        {
            case "USER":return new UserCommand();

            case "PASS":return new PassCommand();

//            case "LIST":return new DirCommand();

            case "MYPORT":return new PortCommand();

            case "QUIT":return new QuitCommand();

            case "RETR":return new RetrCommand();

//            case "CWD":return new CwdCommand();

            case "STOR":return new StorCommand();

            case "PASV":return new PasvCommand();

            default :return null;
        }

    }
}
