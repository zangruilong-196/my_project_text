import manger.WorksManger;
import manger.UserManger;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
import classes.User;
import classes.Work;
import gongju.Dictionaries;
import gongju.PersistenceUtils;



public class Test {
    public static void readDataFromFile(){
        ArrayList<User> users =(ArrayList<User>) PersistenceUtils.dataDeserialization("D:\\javadatatext\\user.data");
        if(users==null){
            users = new ArrayList<User>();
            PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data",users);//写文件
        }
        //将从文件中读取得到的用户信息存储到字典中的集合
        for(User one:users){
            Dictionaries.regUser.add(one);
        }

    }
    public static void main(String[] args) {
        //读取数据
        readDataFromFile();
        //1.new一个UserManger类的对象
        UserManger manger = new UserManger();
        //2.new一个UserManger类的对象
        WorksManger workManger = new WorksManger();
        while(true){
            System.out.println("===================");
            System.out.println("1.注册用户");
            System.out.println("2.登录");
            System.out.println("3.查看所有用户信息");
            System.out.println("===================");
            Scanner input = new Scanner(System.in);
            int flag = input.nextInt();
            switch (flag){
                case 1://注册
                    System.out.print("请输入用户名：");
                    String user = input.next();
                    System.out.print("请输入密码：");
                    String psd = input.next();
                    System.out.print("请输入年龄：");
                    int  age = input.nextInt();
                    User newUser = new User(user,psd,age);
                    manger.registerUser(newUser);
                    break;


                case 2://登录
                    System.out.print("请输入用户名：");
                    String luser = input.next();
                    System.out.print("请输入密码：");
                    String lpsd = input.next();
                    User loginUser = manger.login(luser,lpsd);
                    if(loginUser!=null){
                        System.out.println("登录成功");
                        workManger.showMenu(loginUser);//登录成功后的菜单
                    }else{
                        System.out.println("登录失败");
                    }
                    break;
                case 3://查看所有
                    manger.showAll();
                    break;
            }
        }

        }
}
