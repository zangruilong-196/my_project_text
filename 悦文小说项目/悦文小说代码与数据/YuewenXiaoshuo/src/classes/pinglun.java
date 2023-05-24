package classes;

import java.io.Serializable;

public class pinglun implements Serializable {

    //属性
    String id; //所属作品Id
    String data;//日期
    String work;//所属作品
    String userName;//所属用户
    String stroy;//评论内容


    public pinglun(String id, String data, String work, String userName, String stroy)
    {
        this.id=id;
        this.data=data;
        this.work=work;
        this.userName=userName;
        this.stroy=stroy;
    }

    public String getData() {return data;}
    public String getWork() {return work;}
    public String getUserName() {return userName;}
    public String getStroy() {return stroy;}
    public String getId() {return id;}

    public void setId(String id) {this.id = id;}
    public void setData(String data) {this.data = data;}
    public void setWork(String work) {this.work = work;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setStroy(String stroy) {this.stroy = stroy;}


}
