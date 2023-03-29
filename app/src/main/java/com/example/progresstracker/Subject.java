package com.example.progresstracker;

import java.util.ArrayList;

public class Subject extends ActivitySimilarities {
        private ArrayList<Topic> topic = new ArrayList<>();

        public Subject (String name) {
            this.name = name;
        }
        public ArrayList<Topic> getArrayList() {
            return topic;
        }


        //Editing arrayList of type Topic
        public void addInArrayList(String name) {
            Topic newTopic = new Topic(name);
            topic.add(newTopic);
        }

}
