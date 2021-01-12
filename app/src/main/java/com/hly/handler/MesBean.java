package com.hly.handler;

public class MesBean {
    private String Name;
    private String Sex;
    private int Age;

    public MesBean(String Name, String Sex, int Age) {
        this.Name = Name;
        this.Sex = Sex;
        this.Age = Age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
