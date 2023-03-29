package com.example.progresstracker;


import java.util.ArrayList;

public class Start extends ActivitySimilarities {
    private static final long serialVersionUID = 1L;
    private ArrayList<Subject> subj = new ArrayList<Subject>();


    public void addInArrayList(String name) {
        subj.add(new Subject(name));
    }

    public ArrayList<Subject> getArrayList() {
        return subj;
    }

}