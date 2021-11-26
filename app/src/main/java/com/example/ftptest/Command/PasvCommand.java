package com.example.ftptest.Command;


import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;
import com.example.ftptest.utils.ConfigRead;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class PasvCommand implements Command {
    Logger logger = Logger.getLogger(PasvCommand.class);
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        logger.debug("execute the PASV command...");
        String response = "";
        try{
            int tempPort = -1 ;
            ServerSocket serverSocket = null;
            while (serverSocket==null){
                tempPort = (int) (Math.random() * 100000) % 9999 + 1024;
                serverSocket = getServerSocket(tempPort);
            }
            if (tempPort != -1 && serverSocket != null){
                response = "271 Entering Passive Mode "+tempPort;
                logger.debug(response);
            }

//            Socket dataSocket;
//            dataSocket = serverSocket.accept();
//            t.setDataSocket(dataSocket);
            t.setServerSocket(serverSocket);
            t.setActive(false);

            writer.write(response);
            writer.write("\r\n");
            writer.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket(int port){
        ServerSocket socket;
        try{
           socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return socket;
    }
}
