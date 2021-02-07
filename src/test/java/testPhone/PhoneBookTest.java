package testPhone;

import org.junit.jupiter.api.Test;
import phone.PhoneBook;
import phone.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    @Test
    void addUser() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUser = new ArrayList<String>();
        numberUser.add("+8585858");
        users.add(new User("Nikola", numberUser));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals(true, pBook.addUser("m"));//проверка на добавлене нового пользователя
        assertEquals(false, pBook.addUser("Nikola"));//проверка когда пользователь уже был (что бы не было одинаковых имен)
        assertEquals(true, pBook.addUser("s"));//проверка
        assertEquals(false, pBook.addUser(null));//првоерка на трим нуля
    }

    @Test
    void delUser() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals(true, pBook.delUser("Nikola"));//удаление
        assertEquals(true, pBook.delUser("Blad"));//удалене
        assertEquals(false, pBook.delUser(null));//првоерка трим
        assertEquals(false, pBook.delUser("Blad"));//попытка удалить юзера которого нет
    }

    @Test
    void addNumder() {
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
        assertEquals(true,pBook.addNumder("Blad", "856555"));//добавление когда бльше 1 номера
        assertEquals(false,pBook.addNumder(null, null));//проверка на нулл
        assertEquals(true,pBook.addNumder("Nikola", "232323"));//добавлене номера которого нет у человека
        assertEquals(true,pBook.addNumder("Nikola", "+85"));//добавлене  1 номер
        assertEquals(true,pBook.addNumder("Kostia", "+8585858"));//добавленеие когда нет номеров
        assertEquals(false,pBook.addNumder("Kostia", "+8585858"));// добаление которого был
        assertEquals(false,pBook.addNumder("Blad","5/5/"));//при неправильном вооде номера
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
        assertEquals(true,pBook.delNumder("Blad","8585858555"));//удаление когда бльше 1 номера
        assertEquals(false,pBook.delNumder(null,null));//проверка на нулл
        assertEquals(false,pBook.delNumder("Nikola","8585858555"));//удаление номера которого нет у человека
        assertEquals(true,pBook.delNumder("Nikola","+8585858"));//удалене когда 1 номер
        assertEquals(false,pBook.delNumder("Kostia","+8585858"));//удаление когда нет номеров
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
    }

    @Test
    void foundNumbers() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUserOne = new ArrayList<String>();
        ArrayList<String> numberUserTwo = new ArrayList<String>();
        ArrayList<String> numberUserThree = new ArrayList<>();
        ArrayList<String> valueN=new ArrayList<String>();
        valueN.add("Пользователь не может быть null");
        ArrayList<String> value=new ArrayList<String>();
        value.add("Данного пользователя нет в cиcтеме");
        ArrayList<String> valueNik=new ArrayList<String>();
        valueNik.add("[+8585858]");
        valueNik.add("0");
        ArrayList<String> valueBlad=new ArrayList<String>();
        valueBlad.add("[+8582222, 8585858555]");
        valueBlad.add("1");
        ArrayList<String> valueKosti=new ArrayList<String>();
        valueKosti.add("[]");
        valueKosti.add("2");
        numberUserOne.add("+8585858");
        numberUserTwo.add("+8582222");
        numberUserTwo.add("8585858555");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        users.add(new User("Kostia", numberUserThree));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals(valueN, pBook.foundNumbers(null));//null введеный
        assertEquals(value, pBook.foundNumbers("Anna"));//нет данного пользователя в cиcтеме
        assertEquals(valueNik, pBook.foundNumbers("Nikola"));//поиcк когда номеров 1
        assertEquals(valueBlad, pBook.foundNumbers("Blad"));//поиcк когда номеров больше 1
        assertEquals(valueKosti, pBook.foundNumbers("Kostia"));//поиcк когда нет номеров
    }
}