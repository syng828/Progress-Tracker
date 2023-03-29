package com.example.progresstracker;

import java.util.ArrayList;

public class Topic extends ActivitySimilarities {
    private ArrayList<Question> questions = new ArrayList<>();
    private int progress;

    //topic creation
    public Topic (String topicName) {
        name = topicName;
        progress = 0;
    }
    public ArrayList<Question> getArrayList() { return questions;}


    //Editing arrayList of type Question
    public void addInArrayList(String q, String answer) {
        Question newQuestion = new Question(q, answer);
        questions.add(newQuestion);
    }

    //Checks progress based on #of correct answers from all questions
    public int getProgress (){
        double correct = 0.0;
        if (questions == null || questions.size() == 0) {
            progress = 0;
        }
        else {
            int total = questions.size();
            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).isCorrect()) {
                    correct++;
                }
            }
            progress = (int) ((correct / total) * 100);
        }
        return progress;

    }

}
