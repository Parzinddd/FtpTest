package com.example.ftptest.utils;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ConfigRead {

    /*根目录的路径*/
  //    public static  String rootDir = new File("").getAbsolutePath();//获取当前文件的绝对路径

    public static String rootDir;
    /*允许登录的用户*/
    public static Map<String, String> users = new HashMap<String, String>();

    /*已经登录的用户*/
    public static HashSet<String> loginUser = new HashSet<String>();

    /*拥有权限的用户*/
    public static HashSet<String> adminUsers = new HashSet<String>();

    //初始化根目录，权限用户，能够登录的用户信息
    public static void read() {
        users.put("admin","000000");
        users.put("ANONYMOUS","000000");
        users.put("test","test");
    }
}
