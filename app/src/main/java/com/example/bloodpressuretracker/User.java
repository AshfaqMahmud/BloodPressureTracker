package com.example.bloodpressuretracker;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String sys,dia,bp,cmnt,date,verdict,key;

    public User(String sys, String dia, String bp, String cmnt, String date, String verdict, String key) {
        this.sys = sys;
        this.dia = dia;
        this.bp = bp;
        this.cmnt = cmnt;
        this.date = date;
        this.verdict = verdict;
        this.key = key;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getCmnt() {
        return cmnt;
    }

    public void setCmnt(String cmnt) {
        this.cmnt = cmnt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
//custom constructor for inserting the value under a certain key


    //custom constructor for updating the value under a certain key
    public User(String sys, String dia, String bp, String cmnt, String date) {
        this.sys = sys;
        this.dia = dia;
        this.bp = bp;
        this.cmnt = cmnt;
        this.date = date;
    }
    //default constructor
    public User() {

    }

}
