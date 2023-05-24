package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Work implements Serializable {
    //属性
    String id;//id
    String name;//书名
    String content;//作品详情
    int count;//点赞数
    int type;//类型（1.,2生活,3社会,4国际）
    String userName;//所属用户
    int money;

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    ArrayList<pinglun> pinglun = new ArrayList<>();

    public ArrayList<classes.pinglun> getPinglun() {return pinglun;}

    public void setPinglun(ArrayList<classes.pinglun> pinglun) {this.pinglun = pinglun;}

    public Work(String id, String name, String content, int count, int type, String userName, int money) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.count = count;
        this.type = type;
        this.userName = userName;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
