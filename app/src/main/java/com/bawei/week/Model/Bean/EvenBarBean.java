package com.bawei.week.Model.Bean;

public class EvenBarBean {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "EvenBarBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public EvenBarBean(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
