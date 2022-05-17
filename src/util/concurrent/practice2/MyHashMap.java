package util.concurrent.practice2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyHashMap {


    MyHashMap(){
        for(int i = 0; i < 10; i++)
            locks.add(new ReentrantReadWriteLock(true));
    }

    List<ReadWriteLock> locks = new ArrayList<>();


    List<LinkedList<Node>> arrayList = new ArrayList<>();

    void put(int key, int val){
        int mod = key%10;
        LinkedList<Node> linkedList = arrayList.get(mod);
        ReadWriteLock readWriteLock = locks.get(mod);

        readWriteLock.writeLock().lock();
        try{
            linkedList.addFirst(new Node(key, val));
        } finally{
            readWriteLock.writeLock().unlock();
        }
    }

    Integer get(int key) {
        int mod = key%10;
        LinkedList<Node> linkedList = arrayList.get(mod);
        ReadWriteLock readWriteLock = locks.get(mod);

        readWriteLock.readLock().lock();
        try{
            Iterator<Node> iterator = linkedList.iterator();

            while (iterator.hasNext()) {
                Node node = iterator.next();
                if (node.key == key) return node.val;
            }
            return null;
        } finally{
            readWriteLock.readLock().unlock();
        }
    }


    static class Node{

        int key;
        int val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}
