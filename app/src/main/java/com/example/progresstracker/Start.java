package com.example.progresstracker;


import java.util.ArrayList;

public class Start extends Change {
    private static final long serialVersionUID = 1L;
    private ArrayList<Subject> subj = new ArrayList<Subject>();
    private static String[] message = {"1.Go to a subject", "2. Add a Subject", "3. Delete a subject", "4. See all subjects", "5. Quit"};

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

    public static String[] message() {
        return message;
    }


    public String getArrayListNames() {
        String toBeReturned = "";
        for (Subject s : subj) {
            toBeReturned = toBeReturned + "  " + s.getName();
        }
        return toBeReturned;
    }
}