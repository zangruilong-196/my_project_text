package com.example.pojo;

public class book {

    private int id;
    private String name;
    private String zhouzhe;
    private int money;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getZhouzhe() {return zhouzhe;}

    public void setZhouzhe(String zhouzhe) {this.zhouzhe = zhouzhe;}

    public int getMoney() {return money;}

    public void setMoney(int money) {this.money = money;}

    public book (String name, String zhouzhe, int money) {
        this.name = name;
        this.zhouzhe = zhouzhe;
        this.money = money;
    }

    public book (int id, String name, String zhouzhe, int money) {
        this.id = id;
        this.name = name;
        this.zhouzhe = zhouzhe;
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zhuozhe='" + zhouzhe + '\'' +
                ", money='" + money + '\'' +
                '}';
    }


}
