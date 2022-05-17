package util.concurrent.practice2;

public class Test3 {
    public static void main(String[] arg) {
        Employee employee = new Employee();

        MyThread t1 = new MyThread(new Employee(), "Prakash");
        MyThread t2 = new MyThread(new Employee(), "Manish");

        t1.start();
        t2.start();


        //Runnable runnable = ()
    }


    static class MyThread extends Thread {

        Employee employee;
        String name;

        MyThread(Employee employee, String name){
            this.employee = employee;
            this.name = name;
        }

        @Override
        public void run(){
            employee.print(this.name);
        }

    }
}
