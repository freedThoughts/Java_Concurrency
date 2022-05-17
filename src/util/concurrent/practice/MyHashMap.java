package util.concurrent.practice;

import java.util.ArrayList;

public class MyHashMap {

    ArrayList<Node>[] array = new ArrayList[10];

    MyHashMap(){
        for(int i = 0 ; i < array.length; i++) {
            array[i] = new ArrayList<>();
            array[i].add(new Node());
        }
    }


    void put(Integer key, Integer value) {

        int mod = key %10;

        synchronized (array[mod].get(0).lockElement) {
            array[mod].add(new Node(key, value));
        }
    }


    Integer get(Integer key) {
        Integer mod = key %10;
        synchronized (array[mod].get(0).lockElement) {
            for(int i = 1; i < array[mod].size(); i++) {
                Node node = array[mod].get(i);
                if (node.key.equals(key))
                    return node.value;
            }
        }
        return null;
    }





    class Node {
        Integer key;
        Integer value;
        final Object lockElement;

        Node(){
            this.lockElement = new Object();
        }

        Node (Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.lockElement = null;
        }

    }



}
