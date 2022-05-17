package util.concurrent.practice;

public class MyLinkedList {

    Node head = null;
    final Object lockObject = new Object();

    public static void main(String[] arg) {



    }

    void add(Integer val) {

        boolean isAdded = false;

        if (head == null) {
            synchronized (lockObject) {
                if (head == null) {
                    head = new Node(val);
                    isAdded = true;
                }
            }
        }

        if (isAdded) return;

        Node node = head;
        while(node.next != null) {
            node = node.next;
        }

        synchronized (lockObject) {
            while(node.next != null) {
                node = node.next;
            }

            Node newNode = new Node(val);
            node.next = newNode;
        }
    }


    class Node {
        Integer val;
        Node next;
        //final Object lockElement;



        Node(Integer val){
            //this.lockElement = new Object();
            this.val = val;
        }
    }
}
