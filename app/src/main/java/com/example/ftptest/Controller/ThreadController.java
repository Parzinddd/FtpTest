package com.example.ftptest.Controller;

import  com.example.ftptest.inf.Command;

import  com.example.ftptest.utils.ConfigRead;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import  com.example.ftptest.Command.*;

import org.greenrobot.eventbus.EventBus;

public class ThreadController extends Thread{



    private boolean isActive = true;

    private int count=0;
    //tcp连接
    private Socket socket;
    //用户
    public static final ThreadLocal<String> USER = new ThreadLocal<String>();

    //数据连接ip
    private String dataIp;
    //数据连接的port
    private  String dataPort;
    //用于标记用户是否已经登录
    private boolean isLogin = false;

    //当前目录
    private String dir = ConfigRead.rootDir+File.separator+"data";
    //被动模式数据连接开放端口

    private ServerSocket serverSocket;

    public enum TransferType {
        ASCII, BINARY
    };

    private TransferType type = TransferType.BINARY;

    public enum TransferMode {
        BLOCK,STREAM,ZIP
    };

    private TransferMode mode = TransferMode.STREAM;

    public ThreadController(Socket socket) {
        this.socket = socket;
    }



    public void run() {
        EventBus.getDefault().post("a new client is connected ============= ");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            while(true) {
                //第一次访问，输入流里面是没有东西的，所以会阻塞住
                if(count == 0) {
                    writer.write("220 welcome my ftp server, Server ready.\r\n");
                    writer.flush();
                    count++;
                }
                else {
                    //两种情况会关闭连接：(1)quit命令 (2)密码错误
                    if(!socket.isClosed()) {
                        //进行命令的选择，然后进行处理，当客户端没有发送数据的时候，将会阻塞
                        String command = reader.readLine();
//                        System.out.println(command);
//                        logger.debug("order: "+ command);
                        if(command !=null) {
                            String[] datas = command.split(" ");
                            System.out.println(datas[0]);
                            Command commandSolver = CommandController.createCommand(datas[0]);

                            //登录验证,在没有登录的情况下，无法使用除了user,pass之外的命令
                            if(loginValiate(commandSolver)) {
                                if(commandSolver == null)
                                {
                                    writer.write("502  该命令不存在，请重新输入");
                                }
                                else
                                {
                                    String data = "";
                                    if(datas.length >=2) {
                                        data = datas[1];
                                    }
                                    commandSolver.getResult(data, writer,this);

                                }
                            }
                            else
                            {
                                writer.write("532 执行该命令需要登录，请登录后再执行相应的操作\r\n");
                                writer.flush();
                            }
                        }
                    }
                    else {
                        //连接已经关闭，这个线程不再有存在的必要
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
//            System.out.println("结束tcp连接");
            EventBus.getDefault().post("结束tcp连接");
        }
    }
    public  boolean loginValiate(Command command) {
        if(command instanceof UserCommand || command instanceof PassCommand) {
            return true;
        }
        else
        {
            return isLogin;
        }
    }

    public void setLogin(boolean b) {
        this.isLogin = b;
    }

    public void setDataIp(String ip) {
        this.dataIp = ip;
    }

    public void setDataPort(String port) {
        this.dataPort = port;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public String getDir() {
        return this.dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDataIp() {
        return dataIp;
    }

    public String getDataPort() {
        return dataPort;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public TransferType getType() {
        return type;
    }

    public void setType(TransferType type) {
        this.type = type;
    }

    public void setMode(TransferMode mode) {
        this.mode = mode;
    }


}
