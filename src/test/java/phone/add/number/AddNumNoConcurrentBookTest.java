package phone.add.number;

import org.junit.jupiter.api.Test;

import phone.PhoneBookNoConcurrent;
import phone.User;

public class AddNumNoConcurrentBookTest {

    User user = new User("SomeName");

    @Test
    void oneThreadNoConcurrent() throws InterruptedException {
        PhoneBookNoConcurrent phoneBookNoConcurrent = new PhoneBookNoConcurrent();
        phoneBookNoConcurrent.addNewUser(user);
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 1000000; i++) {
                phoneBookNoConcurrent.addNumberPhoneToUser(user, String.valueOf(i));
            }
        });

        thread1.start();
        thread1.join();

        System.out.println("1ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }
}
