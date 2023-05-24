package com.example.pojo;

public class book1 {
    private int id;
    private String name;
    private String zhouzhe;
    private String xiangqing;
    private String path;



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

    public String getXiangqing(){return xiangqing;}

    public void setXiangqing(String xiangqing){this.xiangqing = xiangqing;}

    public String getPath(){return path;}

    public void setPath(String path){this.path = path;}

    public book1 (String name, String zhouzhe, String xiangqing, String path) {
        this.name = name;
        this.zhouzhe = zhouzhe;
        this.xiangqing = xiangqing;
        this.path = path;
    }

    public book1 (int id, String name, String zhouzhe, String xiangqing, String path) {
        this.id = id;
        this.name = name;
        this.zhouzhe = zhouzhe;
        this.xiangqing = xiangqing;
        this.path = path;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zhuozhe='" + zhouzhe + '\'' +
                ", xiangqing='" + xiangqing + '\'' +
                ", path='" + path + '\'' +
                '}';
    }


}
