package com.example.progresstracker;

import java.io.Serializable;

public class Question implements Serializable{ //Singular question
    private String question;
    private String answer;
    private boolean correct = false;

    public Question (String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        System.out.println("Please enter the new answer.");
        this.answer = answer;
        System.out.println("Answer set.");
    }

    public boolean getCorrect() {
        return correct;
    }

    //check if correct
    public void check(String guess) {
        System.out.println("Please enter your guess.");
        if (guess.equals(answer)) {
            correct = true;
            System.out.println("Correct!");
        }
        else {
            correct = false;
            System.out.println("Wrong!");
        }
    }

}

