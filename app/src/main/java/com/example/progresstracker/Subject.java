package com.example.progresstracker;

import java.util.ArrayList;

public class Subject extends ActivitySimilarities {
        private ArrayList<Topic> topic = new ArrayList<>();

        //Topic creation
        public Subject (String name) {
            this.name = name;
        }
        public Subject() {
            this.name = "";
        }
        public ArrayList<Topic> getArrayList() {
            return topic;
        }


        //Editing arrayList of type Topic

        public void addInArrayList(String name) {
            System.out.println("Please enter the topic you wish to add: ");
            Topic newTopic = new Topic(name);
            topic.add(newTopic);
            System.out.println("Added.");
        }

}
