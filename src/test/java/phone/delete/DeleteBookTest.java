package phone.delete;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import phone.PhoneBookConcurrent;
import phone.User;

class DeleteBookTest {

    static List<User> users = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i <= 1000000; i++) {
            users.add(new User("Vlad" + i));
        }
    }

    @Test
    void oneThreadConcurrent() throws InterruptedException {
        PhoneBookConcurrent phoneBookConcurrent = new PhoneBookConcurrent();
        for (User user : users) {
            phoneBookConcurrent.addNewUser(user);
        }

        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (User user : users) {
                phoneBookConcurrent.deleteUser(user);
            }
        });

        thread1.start();
        thread1.join();

        System.out.println("1ThreadConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void twoThreadConcurrent() throws InterruptedException {
        PhoneBookConcurrent phoneBookConcurrent = new PhoneBookConcurrent();
        for (User user : users) {
            phoneBookConcurrent.addNewUser(user);
        }

        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 499999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 500000; i <= 1000000; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("2ThreadConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void tThreadConcurrent() throws InterruptedException {
        PhoneBookConcurrent phoneBookConcurrent = new PhoneBookConcurrent();
        for (User user : users) {
            phoneBookConcurrent.addNewUser(user);
        }

        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 333333; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 333334; i <= 666666; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 666667; i <= 1000000; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("3ThreadConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void fThreadConcurrent() throws InterruptedException {
        PhoneBookConcurrent phoneBookConcurrent = new PhoneBookConcurrent();
        for (User user : users) {
            phoneBookConcurrent.addNewUser(user);
        }

        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 249999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 250000; i <= 449999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 500000; i <= 749999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 750000; i <= 1000000; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
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

        System.out.println("4ThreadConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    void fiThreadConcurrent() throws InterruptedException {
        PhoneBookConcurrent phoneBookConcurrent = new PhoneBookConcurrent();
        for (User user : users) {
            phoneBookConcurrent.addNewUser(user);
        }

        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 199999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 200000; i <= 399999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 400000; i <= 599999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 600000; i <= 799999; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
            }
        });
        Thread thread5 = new Thread(() -> {
            for (int i = 800000; i <= 1000000; i++) {
                phoneBookConcurrent.deleteUser(users.get(i));
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

        System.out.println("5ThreadConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }


}