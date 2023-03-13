package com.example.progresstracker;

import java.util.ArrayList;
import java.util.Scanner;

    public class Subject extends Change  {
        private ArrayList<Topic> topic = new ArrayList<>();
        private static String[]message = {"1. Go to a topic", "2. Add a topic", "3. Delete a topic", "4. See all topics", "5. Go back"};

        //Topic creation
        public Subject (String name) {
            setName(name);
        }
        public Subject() {
            setName("");
        }

        public static String [] message() {
            return message;
        }
        //Goes into topic
        @Override
        public int goIn(String name) {
            System.out.println("Enter a topic name to search for: ");
            for (int i = 0; i < topic.size(); i++) {
                if (topic.get(i).getName().equals(name)) {
                    //TODO: Go in
                    return i;
                }
            }
            System.out.println("Not found.");
            return -1;
        }
        public Topic getArrayList(int i) {
            return topic.get(i);
        }


        //Editing arrayList of type Topic

        public void addInArrayList(String name) {
            System.out.println("Please enter the topic you wish to add: ");
            Topic newTopic = new Topic(name);
            topic.add(newTopic);
            System.out.println("Added.");
        }

        @Override
        public boolean deleteInArrayList(String name) {
            System.out.println("Please enter the topic you wish to delete: ");
            for (int i = topic.size() - 1; i >= 0 ; i--) {
                if (topic.get(i).getName().equals(name)){
                    topic.remove(i);
                    System.out.println("Deleted.");
                    return true;
                }
            }
            System.out.println("Could not find.");
            return false;
        }

        public String getArrayListNames() {
            String toBeReturned = "";
            for (Topic t : topic ) {
                toBeReturned = toBeReturned + "  " + t.getName();
            }
            return toBeReturned;
        }

}
