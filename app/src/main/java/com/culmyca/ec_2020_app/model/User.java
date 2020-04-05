package com.culmyca.ec_2020_app.model;

public class User {
    public String full_name,mobile_no,email,college;

    public User(String full_name, String mobile_no, String email, String college) {
        this.full_name = full_name;
        this.mobile_no = mobile_no;
        this.email = email;
        this.college = college;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "User{" +
                "full_name='" + full_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", email='" + email + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
}
