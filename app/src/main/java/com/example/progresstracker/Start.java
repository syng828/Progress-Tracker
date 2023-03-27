package com.example.progresstracker;


import java.util.ArrayList;

public class Start extends ActivitySimilarities {
    private static final long serialVersionUID = 1L;
    private ArrayList<Subject> subj = new ArrayList<Subject>();


    public void addInArrayList(String name) {
        System.out.println("Enter a subject name: ");
        subj.add(new Subject(name));
        System.out.println("Subject added.");
    }

    public ArrayList<Subject> getArrayList() {
        return subj;
    }

}