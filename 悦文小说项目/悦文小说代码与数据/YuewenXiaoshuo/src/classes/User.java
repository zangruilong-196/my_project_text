package classes;

import java.io.Serializable;
import java.util.ArrayList;
import classes.Work;
import classes.pinglun;

public class User implements Serializable {
    //属性
    String userName;
    String password;
    int age;

    int moneny;
    //存储点赞的作品
    ArrayList<Work> likeWorkList = new ArrayList<Work>();
    //购买过的书
    ArrayList<Work> gomai = new ArrayList<>();
    //读过的书
    ArrayList<Work> duguo = new ArrayList<>();
    //评论
    ArrayList<pinglun> pinglun = new ArrayList<>();

    public ArrayList<classes.pinglun> getPinglun() {return pinglun;}

    public void setPinglun(ArrayList<classes.pinglun> pinglun) {this.pinglun = pinglun;}

    public int getMoneny() {return moneny;}

    public void setMoneny(int moneny) {this.moneny = moneny;}

    public ArrayList<Work> getDuguo() {return duguo;}

    public void setDuguo(ArrayList<Work> duguo) {this.duguo = duguo;}

    public ArrayList<Work> getGomai() {return gomai;}

    public void setGomai(ArrayList<Work> gomai) {this.gomai = gomai;}

    //构造方法
    public User(String newUser,String newPsd,int newAge){
        this.userName =  newUser;
        this.password = newPsd;
        this.age = newAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Work> getLikeWorkList() {
        return likeWorkList;
    }

    public void setLikeWorkList(ArrayList<Work> likeWorkList) {
        this.likeWorkList = likeWorkList;
    }

    //行为
    public void showMsg(){
        System.out.println("用户名："+userName);
        System.out.println("密码：***********");
        System.out.println("年龄："+age);
    }
}