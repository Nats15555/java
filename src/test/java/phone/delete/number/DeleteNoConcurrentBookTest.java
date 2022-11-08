package phone.delete.number;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import phone.PhoneBookNoConcurrent;
import phone.User;

public class DeleteNoConcurrentBookTest {

    static User user = new User("SomeName");
    static PhoneBookNoConcurrent phoneBookNoConcurrent = new PhoneBookNoConcurrent();

    @BeforeAll
    public static void setUp() {
        phoneBookNoConcurrent.addNewUser(user);
        for (int i = 0; i <= 1000000; i++) {
            phoneBookNoConcurrent.addNumberPhoneToUser(user, String.valueOf(i));
        }
    }

    @Test
    void oneThreadNoConcurrent() throws InterruptedException {
        long time = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 1000000; i++) {
                phoneBookNoConcurrent.deleteNumberPhoneToUser(user, String.valueOf(i));
            }
        });

        thread1.start();
        thread1.join();

        System.out.println("1ThreadNoConcurrent: " + (System.currentTimeMillis() - time) + " ms");
    }
}
