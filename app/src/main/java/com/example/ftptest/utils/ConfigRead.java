package com.example.ftptest.utils;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.os.Environment;


public class ConfigRead {
    public static Logger configLogger = Logger.getLogger(ConfigRead.class);
    /*根目录的路径*/
//    public static  String rootDir = new File("").getAbsolutePath();//获取当前文件的绝对路径

    public static  String rootDir = System.getProperty("user.dir") + "/data";
    /*允许登录的用户*/
    public static Map<String,String> users = new HashMap<String,String>();

    /*已经登录的用户*/
    public static HashSet<String> loginUser = new HashSet<String>();

    /*拥有权限的用户*/
    public static HashSet<String> adminUsers = new HashSet<String>();

    public static void read(){
        users.put("admin","000000");
    }


    //初始化根目录，权限用户，能够登录的用户信息
//    public static void read(){
////        String path = Environment.getExternalStorageState() + "app/res/config/server.xml";
////        String path = System.getProperty("user.dir") + "app/res/config/server.xml";
//        String path =
//        Log.i("path:",path);
//        File file = new File(path);
//        SAXBuilder builder = new SAXBuilder();
//        try {
//            Document parse = builder.build(file);
//            Element root = parse.getRootElement();
//
//            //配置服务器的默认目录
//            rootDir = root.getChildText("rootDir");
////            System.out.print("rootDir is:");
////            System.out.println(rootDir);
////            configLogger.debug("rootDir is: "+rootDir);
//
//            //允许登录的用户
//            Element usersE = root.getChild("users");
//            List<Element> usersEC = usersE.getChildren();
//            String username = null;
//            String password = null;
//            System.out.println("\n所有用户的信息：");
//            for(Element user : usersEC) {
//                username = user.getChildText("username");
//                password = user.getChildText("password");
////                System.out.println("用户名："+username);
////                System.out.println("密码："+password);
////                configLogger.debug("用户名： "+username);
////                configLogger.debug("密码： "+password);
//                users.put(username,password);
//            }
//
//            /*
//            //拥有put权限和delete权限的用户
//            System.out.println("\n管理员用户：");
//            Element adminUsersE = root.getChild("adminUsers");
//            for(Element adminUserTemp: (List<Element>)adminUsersE.getChildren()) {
//                username = adminUserTemp.getText();
//                //System.out.println("用户名："+username);
//                adminUsers.add(username);
//            }   */
//        } catch (JDOMException | IOException e) {
//            e.printStackTrace();
//        }
//    }

}
