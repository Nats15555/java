package phone.add;

import org.junit.jupiter.api.Test;

import phone.PhoneBookNoConcurrent;
import phone.User;

public class AddNoConcurrentBookTest {
    @Test
    void oneThreadNoConcurrent() throws InterruptedException {
        PhoneBookNoConcurrent phoneBookNoConcurrent = new PhoneBookNoConcurrent();
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 1000000; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });

        thread1.start();
        thread1.join();

        System.out.println("1ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void twoThreadNoConcurrent() throws InterruptedException {
        PhoneBookNoConcurrent phoneBookNoConcurrent = new PhoneBookNoConcurrent();
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 499999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 500000; i <= 1000000; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("2ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void tThreadNoConcurrent() throws InterruptedException {
        PhoneBookNoConcurrent phoneBookNoConcurrent= new PhoneBookNoConcurrent();
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 333333; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 333334; i <= 666666; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 666667; i <= 1000000; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("3ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void fThreadNoConcurrent() throws InterruptedException {
        PhoneBookNoConcurrent phoneBookNoConcurrent= new PhoneBookNoConcurrent();
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 249999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 250000; i <= 449999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 500000; i <= 749999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 750000; i <= 1000000; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("4ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void fiThreadNoConcurrent() throws InterruptedException {
        PhoneBookNoConcurrent phoneBookNoConcurrent= new PhoneBookNoConcurrent();
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 199999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 200000; i <= 399999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 400000; i <= 599999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 600000; i <= 799999; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });
        Thread thread5 = new Thread(() -> {
            for (int i = 800000; i <= 1000000; i++) {
                phoneBookNoConcurrent.addNewUser(new User("Vlad" + i));
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println("5ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }
}
