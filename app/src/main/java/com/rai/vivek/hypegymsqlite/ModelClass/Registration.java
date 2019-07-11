package com.rai.vivek.hypegymsqlite.ModelClass;

public class Registration {
    int id;
    String name;
    public String email;
    String phone;
    public String password;
    String address;
    //String dob, doj;

    public Registration(int id, String name, String email, String phone, String password, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

  /* public String getDob() {
        return dob;
    }



    public String getDoj() {
        return doj;
    }

*/
}