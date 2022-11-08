package phone.delete;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import phone.PhoneBookNoConcurrent;
import phone.User;

public class DeleteNoConcurrentBookTest {
    static List<User> users = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i <= 1000000; i++) {
            users.add(new User("Vlad" + i));
        }
    }

    @Test
    void oneThreadNoConcurrent() throws InterruptedException {
        PhoneBookNoConcurrent phoneBookNoConcurrent = new PhoneBookNoConcurrent();
        for (User user : users) {
            phoneBookNoConcurrent.addNewUser(user);
        }

        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (User user : users) {
                phoneBookNoConcurrent.deleteUser(user);
            }
        });

        thread1.start();
        thread1.join();

        System.out.println("1ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }
}
