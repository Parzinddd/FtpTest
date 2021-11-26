package com.example.ftptest.Command;

import com.example.ftptest.Controller.ThreadController;
import com.example.ftptest.inf.Command;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.Socket;

public class StorCommand implements Command {
    Logger logger = Logger.getLogger(StorCommand.class);
    @Override
    public void getResult(String data, Writer writer, ThreadController t) {
        try{
            writer.write("150 Binary data connection\r\n");
            writer.flush();
            RandomAccessFile inFile = new RandomAccessFile(t.getDir()+"/data/"+data,"rw");
            InputStream inSocket;
            Socket tempSocket = null;
            if (t.isActive()){
                //数据连接
                tempSocket = new Socket(t.getDataIp(),Integer.parseInt(t.getDataPort()));
                inSocket = tempSocket.getInputStream();
            }else {
                tempSocket = t.getServerSocket().accept();
                inSocket = tempSocket.getInputStream();
            }
            byte[] byteBuffer = new byte[1024];
            int bytes;
            //这里又会阻塞掉，无法从客户端输出流里面获取数据？是因为客户端没有发送数据么
            while((bytes = inSocket.read(byteBuffer) )!= -1){
                inFile.write(byteBuffer, 0, bytes);
            }
            logger.debug("传输完成，关闭连接");
            inFile.close();
            inSocket.close();
            tempSocket.close();
            //断开数据连接

            writer.write("226 transfer complete\r\n");
            writer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
