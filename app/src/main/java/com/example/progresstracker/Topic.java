package com.example.progresstracker;

import java.util.ArrayList;
import java.util.Scanner;

public class Topic extends Change{
    private ArrayList<Question> question = new ArrayList<>();
    private static String[] message = {"1. Go to a question", "2. Add a question", "3. Delete a question", "4. See questions and answers", "5. See questions only", "6. Look at progress", "7. Go back"};

    //topic creation
    public Topic (String topicName) {
        name = topicName; //name comes from the abstract class
    }

    //Goes into question
    @Override
    public int goIn(String name) {
        for (int i = 0; i < question.size(); i++) {
            if (question.get(i).getQuestion().equals(name)) {
                //TODO: Go in
                return i;
            }
        }
        System.out.println("Not found.");
        return -1;
    }
    public Question getQuestion (int i) {
        return question.get(i);
    }

    //Editing arrayList of type Question

    public void addInArrayList(String q, String answer) {
        Question newQuestion = new Question(q, answer);
        question.add(newQuestion);
        System.out.println("Added.");

    }

    public String getArrayListNames() {
        String toBeReturned = "";
        for (Question q : question ) {
            toBeReturned = toBeReturned + "  " + q.getQuestion();
        }
        return toBeReturned;
    }


    @Override
    public boolean deleteInArrayList(String name) {
        System.out.println("Please enter the question you would like to delete.");
        for (int i = question.size() - 1; i >= 0 ; i--) {
            if (question.get(i).getQuestion().equals(name)){
                question.remove(i);
                System.out.print("Deleted.");
                return true;
            }
        }
        return false;
    }

    public static String[] message() {
        return message;
    }

    //Extra methods exclusive to topic.
    //Returns all questions
    public void getQuestions () {
        if (question.size() == 0 ) {
            System.out.println("Nothing inside this topic.");
        }
        else {
            for (int i = 0; i < question.size(); i++){
                System.out.println(question.get(i).getQuestion());
            }
        }
    }

    //Checks progress
    public String getProgress (){
        int total = question.size();
        int correct = 0;
        for (int i = 0; i < question.size(); i++){
            if (question.get(i).getCorrect()){
                correct++;
            }
        }
        if (total == 0) {
            return ("No questions inside.");
        }
        else {
            return ("Progress: " + ((double)correct/total * 100) + "%");
        }
    }

}
