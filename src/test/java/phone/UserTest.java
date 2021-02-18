package phone;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testEquals() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        users.add(new User("Nikola", numberUserOne));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals(false, users.get(0).equals(users.get(1)));//зравнене ползователей разных
        assertEquals(true, users.get(0).equals(users.get(2)));//зравнне одинаковых пользователй
    }

    @Test
    void getUserName() {
        ArrayList<String> numberUserOne =new ArrayList<>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        User user = new User("Nikolai", numberUserOne);
        User user2 = new User("Blad", numberUserTwo);
        User user3 = new User(null, numberUserOne);
        assertEquals("Nikolai", user.getUserName());
        assertEquals("Blad", user2.getUserName());
        assertEquals("", user3.getUserName());
    }

    @Test
    void getUserNumber() {
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        ArrayList<String> numberUser = new ArrayList<String>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        User user = new User("Nikolai", numberUserOne);
        User user2 = new User("Blad", numberUserTwo);
        User user3 = new User(null, numberUser);
        assertEquals(numberUserOne, user.getUserNumber());
        assertEquals(numberUserTwo, user2.getUserNumber());
        assertEquals(numberUser, user3.getUserNumber());
    }
}