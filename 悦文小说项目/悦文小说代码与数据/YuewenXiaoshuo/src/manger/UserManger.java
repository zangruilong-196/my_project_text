package manger;

import java.util.Scanner;
import classes.User;
import classes.Work;
import gongju.Dictionaries;
import gongju.PersistenceUtils;


public class UserManger {
    //属性
    //ArrayList<User> regUser = new ArrayList<User>();

    //注册用户
    public User registerUser(User newUser){
        //注册的用户名是否重复
        for(User one:Dictionaries.regUser){
            if(one.getUserName().equals(newUser.getUserName())) {
                System.out.println("该账号已被注册，请重新选择。");
                return null;
            }
        }
        Dictionaries.regUser.add(newUser);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data",Dictionaries.regUser);
        System.out.println("恭喜，注册成功！");
        return newUser;
    }
    //登录
    public User login(String user,String psd){
        for(User one:Dictionaries.regUser){
            if(one.getUserName().equals(user)&&one.getPassword().equals(psd)){
               return one;
            }
        }
        return null;
    }


    //查看所有
    public void showAll(){
        for(User one:Dictionaries.regUser){
            System.out.println("用户名："+one.getUserName()+",密码："+one.getPassword());
        }
    }

}
