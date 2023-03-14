package com.example.progresstracker;


import java.util.ArrayList;

public class Start extends Change {
    private static final long serialVersionUID = 1L;
    private ArrayList<Subject> subj = new ArrayList<Subject>();


    public void addInArrayList(String name) {
        System.out.println("Enter a subject name: ");
        subj.add(new Subject(name));
        System.out.println("Subject added.");
    }

    @Override
    public boolean deleteInArrayList(String name) {
        System.out.println("Please enter the subject you would like to delete.");
        for (int i = 0; i < subj.size(); i++) {
            if (subj.get(i).getName().equals(name)) {
                subj.remove(i);
                System.out.println("Deleted.");
                return true;
            }
        }
        return false;
    }

    @Override
    public int goIn(String name) {
        System.out.println("Enter a subject name to search for: ");
        for (int i = 0; i < subj.size(); i++) {
            if (subj.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Subject getInstance(int i) {
        return subj.get(i);
    }

    public ArrayList<Subject> getArrayList() {
        return subj;
    }

    public String getArrayListNames() {
        String toBeReturned = "";
        for (Subject s : subj) {
            toBeReturned = toBeReturned + "  " + s.getName();
        }
        return toBeReturned;
    }
}