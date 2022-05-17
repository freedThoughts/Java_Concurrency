package util.concurrent.practice2;

public class Employee {

    synchronized void print(String name){
        for (int i = 0; i < 5; i++)
            System.out.println("Hello " + name);
    }
}
