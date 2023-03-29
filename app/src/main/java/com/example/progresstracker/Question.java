package com.example.progresstracker;

import java.io.Serializable;

public class Question implements Serializable{
    private final String question;
    private final String answer;
    private boolean correct;
    private boolean expandable;  //expandable is used for expanding the layout
    private String attempt;

    public Question (String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.expandable = false;
        this.correct = false;
        this.attempt = "";
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }
    public String getAttempt() {
        return attempt;
    }

    public boolean isExpandable() {
        return expandable;
    }
    public void setExpanded(boolean expandable) {
        this.expandable = expandable;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect(){
        return correct;
    }

    //check if correct
    public void check() {
        if (attempt.equals(answer)) {
            this.correct = true;
        }
        else {
            this.correct = false;
        }
    }



}

