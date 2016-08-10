package com.yzyx.swq;

/**
 * Created by Administrator on 2016/8/10.
 */
public class Item {
    private int is;
    private String name;
    private String pass;
    private boolean gender;

    public Item() {
    }

    public Item(int is, String name, String pass, boolean gender) {

        this.is = is;
        this.name = name;
        this.pass = pass;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Item{" +
                "is=" + is +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", gender=" + gender +
                '}';
    }

    public int getIs() {
        return is;
    }

    public void setIs(int is) {
        this.is = is;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
