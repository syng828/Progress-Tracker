package com.example.progresstracker;

import java.io.Serializable;

public class Question implements Serializable{ //Singular question
    private final String question;
    private final String answer;
    private boolean correct = false;
    private boolean expandable;

    public Question (String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.expandable = false;
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

    public String getCorrectString() {
        if(correct)
            return "Correct";
        else
            return "Incorrect";
    }

    //check if correct
    public void check(String guess) {
        if (guess.equals(answer)) {
            this.correct = true;
        }
        else {
            this.correct = false;
        }
    }



}

