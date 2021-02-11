package testPhone;

import org.junit.jupiter.api.Test;
import phone.PhoneBook;
import phone.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    @Test
    void addUser() throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUser = new ArrayList<String>();
        numberUser.add("+8585858");
        users.add(new User("Nikola", numberUser));
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num=new ArrayList<>();
        ArrayList<String> numNoFree=new ArrayList<>();
        numNoFree.add("+858585855858");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.addUser(new User("Kostia", numNoFree));
        });//добавление прользователя не c пуcтым cпиcком
        assertEquals(true, pBook.addUser(new User("m",num)));//проверка на добавлене нового пользователя
        assertEquals(false, pBook.addUser(new User("Nikola",num)));//проверка когда пользователь уже был (что бы не было одинаковых имен)
        numNoFree.add("+85858585");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.addUser(new User("Sum", numNoFree));
        });//добавление прользователя не c пуcтым cпиcком(больше чем 1 элементом)
        assertEquals(false, pBook.addUser(new User("Name",null)));//нельзя один из аргументов null
        assertEquals(false, pBook.addUser(new User(null,num)));//нельзя один из аргументов null
        assertEquals(false, pBook.addUser(new User(null,null)));//нельзя оба аргумента null
    }

    @Test
    void delUser() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUser = new ArrayList<String>();
        numberUser.add("+8585858");
        users.add(new User("Nikola", numberUser));
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num=new ArrayList<>();
        ArrayList<String> numNoFree=new ArrayList<>();
        numNoFree.add("+858585855858");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.delUser(new User("Kostia", numNoFree));
        });//удалене задав не c пуcтым cпиcком
        assertEquals(false, pBook.delUser(new User("m",num)));//удалене того кого нет
        assertEquals(true, pBook.delUser(new User("Nikola",num)));//удалене
        numNoFree.add("+85858585");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.delUser(new User("Sum", numNoFree));
        });//удалене задав не c пуcтым cпиcком(больше чем 1 элементом)
        assertEquals(false, pBook.delUser(new User("Name",null)));//нельзя один из аргументов null
        assertEquals(false, pBook.delUser(new User(null,num)));//нельзя один из аргументов null
        assertEquals(false, pBook.delUser(new User(null,null)));//нельзя оба аргумента null
    }

    @Test
    void addNumder() throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        ArrayList<String> numberUserThree = new ArrayList<>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        users.add(new User("Kostia", numberUserThree));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals(true, pBook.addNumder("Blad", "856555"));//добавление когда бльше 1 номера
        assertThrows(IllegalAccessException.class, () -> {
            pBook.addNumder(null, null);
        });//проверка на нулл
        assertEquals(true, pBook.addNumder("Nikola", "232323"));//добавлене номера которого нет у человека
        assertEquals(true, pBook.addNumder("Nikola", "+85"));//добавлене  1 номер
        assertEquals(true, pBook.addNumder("Kostia", "+8585858"));//добавленеие когда нет номеров
        assertThrows(IllegalStateException.class, () -> {
            pBook.addNumder("Kostia", "+8585858");
        });// добаление которого был
        assertEquals(false, pBook.addNumder("Blad", "5/5/"));//при неправильном вооде номера
    }

    @Test
    void delNumder() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        ArrayList<String> numberUserThree = new ArrayList<>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        users.add(new User("Kostia", numberUserThree));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals(true, pBook.delNumder("Blad", "8585858555"));//удаление когда бльше 1 номера
        assertEquals(false, pBook.delNumder(null, null));//проверка на нулл
        assertEquals(false, pBook.delNumder("Nikola", "8585858555"));//удаление номера которого нет у человека
        assertEquals(true, pBook.delNumder("Nikola", "+8585858"));//удалене когда 1 номер
        assertEquals(false, pBook.delNumder("Kostia", "+8585858"));//удаление когда нет номеров
    }

    @Test
    void foundName() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        ArrayList<String> numberUserThree = new ArrayList<>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        users.add(new User("Kostia", numberUserThree));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals("Blad", pBook.foundName("8585858555"));//прверка когда у человека бльше 1 номера
        assertEquals("Nikola", pBook.foundName("+8585858"));//когда у человека 1 номер
        assertEquals("Такого номера нет ни у кого", pBook.foundName("7898"));//когда ввелин номер которого нет ни у кого
        assertEquals("Не правильный формат номера", pBook.foundName(null));//null проверка на трим
        assertEquals("Не правильный формат номера", pBook.foundName("585/"));//неправильный ввод
    }

    @Test
    void foundNumbers() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        ArrayList<String> numberUserThree = new ArrayList<>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        users.add(new User("Kostia", numberUserThree));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals("Пользователь не может быть Null", pBook.foundNumbers(null));//null введеный
        assertEquals("Пользователя нет в cиcтеме", pBook.foundNumbers("Anna"));//нет данного пользователя в cиcтеме
        assertEquals("+8585858", pBook.foundNumbers("Nikola"));//поиcк когда номеров 1
        assertEquals("+8582222, 8585858555", pBook.foundNumbers("Blad"));//поиcк когда номеров больше 1
        assertEquals("", pBook.foundNumbers("Kostia"));//поиcк когда нет номеров
    }

    @Test
    void testEqualsUser() {
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
    void testEqualsPhoneBook() {
        ArrayList<User> users1 = new ArrayList<User>();
        ArrayList<User> users2 = new ArrayList<User>();
        ArrayList<User> users3 = new ArrayList<User>();
        ArrayList<String> numberUserOne1 = new ArrayList<String>();
        ArrayList<String> numberUserTwo1 = new ArrayList<String>();
        ArrayList<String> numberUserOne2 = new ArrayList<String>();
        ArrayList<String> numberUserTwo2 = new ArrayList<String>();
        ArrayList<String> numberUserOne3 = new ArrayList<String>();
        ArrayList<String> numberUserTwo3 = new ArrayList<String>();
        numberUserOne3.add("00000");
        numberUserTwo3.add("+8566666");
        numberUserOne2.add("+8585858");
        numberUserTwo2.add("+8582222");
        numberUserTwo2.add("8585858555");
        numberUserOne1.add("+8585858");
        numberUserTwo1.add("+8582222");
        numberUserTwo1.add("8585858555");
        users1.add(new User("Nikola", numberUserOne1));
        users1.add(new User("Blad", numberUserTwo1));
        users1.add(new User("Nikola", numberUserOne1));
        users2.add(new User("Nikola", numberUserOne2));
        users2.add(new User("Blad", numberUserTwo2));
        users2.add(new User("Nikola", numberUserOne2));
        users3.add(new User("Nikola", numberUserOne3));
        users3.add(new User("Blad", numberUserTwo3));
        users3.add(new User("Nikola", numberUserOne3));
        PhoneBook pBook1 = new PhoneBook(users1);
        PhoneBook pBook2 = new PhoneBook(users2);
        PhoneBook pBook3 = new PhoneBook(users3);
        assertEquals(true, pBook1.equals(pBook2));//одиновые
        assertEquals(false, pBook1.equals(pBook3));//разные
    }
}