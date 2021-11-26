package com.example.ftptest;
;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.utils.ConfigRead;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer {
    private int port;

    ServerSocket serverSocket;

    public FtpServer(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        ConfigRead.read();
    }

    public void listen() throws IOException {
        Socket socket = null;
        while (true) {
            socket = serverSocket.accept();
            ThreadController thread = new ThreadController(socket);
            thread.start();
        }
    }
//    public static void main(String args[]) throws IOException {
//        FtpServer ftpServer = new FtpServer(21);
//        ftpServer.listen();
//    }
}
