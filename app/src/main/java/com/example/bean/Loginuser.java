package com.example.bean;

public class Loginuser {
    private int userid;
    private String username;
    private String password;
    private int gender;
    private int age;
    private int occupation;

    public Loginuser(int userid, String username, String password, int gender, int age, int occupation) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Loginuser{" +
                "userid=" + userid +
                ", username='" + username +
                ", password='" + password +
                ", gender=" + gender +
                ", age=" + age +
                ", occupation=" + occupation +
                '}';
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }
}
