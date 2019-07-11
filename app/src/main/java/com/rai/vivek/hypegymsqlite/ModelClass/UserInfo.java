package com.rai.vivek.hypegymsqlite.ModelClass;


public class UserInfo {
    int id;
    public String intime, outtime, desc;


    public UserInfo(int id, String intime, String outtime, String desc) {
        this.id = id;
        this.intime = intime;
        this.outtime = outtime;
        this.desc = desc;
    }


    public int getId() {
        return id;
    }

    public String getIntime() {
        return intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public String getDesc() {
        return desc;
    }
}
