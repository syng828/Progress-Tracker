package com.example.progresstracker;

//the other classes share similarities to this.
import java.io.Serializable;

public abstract class Change implements Serializable {
    String name;

    public abstract int goIn(String name);
    public abstract boolean deleteInArrayList(String name);
    public abstract String getArrayListNames();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}