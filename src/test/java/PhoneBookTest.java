import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    @Test
    void addUser() {
        ArrayList<String> userName= new ArrayList<String>();
        ArrayList<String> userNumber= new ArrayList<String>();
        userName.add("Nikola");
        userNumber.add("+756565");
        userName.add("Blad");
        userNumber.add("+856545,789988");
        PhoneBook pBook= new PhoneBook(userName,userNumber);
        ArrayList<String> testName=new ArrayList<String>();
        testName.add("Nikola");
        testName.add("Blad");
        testName.add("m");
        assertEquals(testName,pBook.addUser("m"));//проверка на добавлене нового пользователя
        System.out.println("_______________________");
        assertEquals(testName,pBook.addUser("Nikola"));//проверка когда пользователь уже был (что бы не было одинаковых имен)
    }

    @Test
    void delUser() {
        ArrayList<String> userName= new ArrayList<String>();
        ArrayList<String> userNumber= new ArrayList<String>();
        userName.add("Nikola");
        userNumber.add("+756565");
        userName.add("Blad");
        userNumber.add("+856545,789988");
        userName.add("Nika");
        userNumber.add("-");
        PhoneBook pBook= new PhoneBook(userName,userNumber);
        ArrayList<String> testName=new ArrayList<String>();
        testName.add("Nikola");
        testName.add("Nika");
        assertEquals(testName,pBook.delUser("Blad"));//удалене пользователя
    }

    @Test
    void addNumder() {
        ArrayList<String> userName= new ArrayList<String>();
        ArrayList<String> userNumber= new ArrayList<String>();
        userName.add("Nikola");
        userNumber.add("+756565");
        userName.add("Blad");
        userNumber.add("+856545,789988");
        userName.add("Nika");
        userNumber.add("-");
        PhoneBook pBook= new PhoneBook(userName,userNumber);

        ArrayList<String> testNoName=new ArrayList<String>();
        testNoName.add("+756565");
        testNoName.add("+856545,789988");
        testNoName.add("-");
        assertEquals(testNoName,pBook.addNumder("m","+555555"));//добавление по имени которого нет
        System.out.println("_______________________");
        ArrayList<String> testTrueName=new ArrayList<String>();
        testTrueName.add("+756565,+7565655");
        testTrueName.add("+856545,789988");
        testTrueName.add("-");
        assertEquals(testTrueName,pBook.addNumder("Nikola","+7565655"));//добавление нового номера человеку но у него уже были номера
        System.out.println("_______________________");
        ArrayList<String> testAddName=new ArrayList<String>();
        testAddName.add("+756565,+7565655");
        testAddName.add("+856545,789988");
        testAddName.add("+7565655");
        assertEquals(testAddName,pBook.addNumder("Nika","+7565655"));//добавлене нового номера когда номеров не было
        System.out.println("_______________________");
        ArrayList<String> testAddMoreName=new ArrayList<String>();
        testAddMoreName.add("+756565,+7565655");
        testAddMoreName.add("+856545,789988");
        testAddMoreName.add("+7565655");
        assertEquals(testAddMoreName,pBook.addNumder("Blad","+856545"));//добавление номера, который уже был у человека у которого больше 1 номера*/
    }

    @Test
    void delNumder() {
        ArrayList<String> userName= new ArrayList<String>();
        ArrayList<String> userNumber= new ArrayList<String>();
        userName.add("Nikola");
        userNumber.add("+756565");
        userName.add("Blad");
        userNumber.add("+856545,789988");
        userName.add("Nika");
        userNumber.add("-");
        PhoneBook pBook= new PhoneBook(userName,userNumber);
        ArrayList<String> test=new ArrayList<String>();
        test.add("-");
        test.add("+856545,789988");
        test.add("-");
        assertEquals(test,pBook.delNumder("Nikola","+756565"));
        ArrayList<String> testM=new ArrayList<String>();
        testM.add("-");
        testM.add("789988");
        testM.add("-");
        assertEquals(testM,pBook.delNumder("Blad","+856545"));
        ArrayList<String> testO=new ArrayList<String>();
        testO.add("-");
        testO.add("-");
        testO.add("-");
        assertEquals(testO,pBook.delNumder("Blad","789988"));
    }

    @Test
    void foundName() {
        ArrayList<String> userName= new ArrayList<String>();
        ArrayList<String> userNumber= new ArrayList<String>();
        userName.add("Nikola");
        userNumber.add("+756565");
        userName.add("Blad");
        userNumber.add("+856545,8565656,789988");
        userName.add("Nika");
        userNumber.add("-");
        PhoneBook pBook= new PhoneBook(userName,userNumber);
        assertEquals("Blad",pBook.foundName("+856545"));//прверка когда у человека бльше 1 номера
        assertEquals("Nikola",pBook.foundName("+756565"));//когда у человека 1 номер
        assertEquals("Такого номера нет ни у кого",pBook.foundName("7898"));//когда ввелин номер которого нет ни у кого
        assertEquals("Blad",pBook.foundName("8565656"));
    }

    @Test
    void foundNumbers() {
        ArrayList<String> userName= new ArrayList<String>();
        ArrayList<String> userNumber= new ArrayList<String>();
        userName.add("Nikola");
        userNumber.add("+756565");
        userName.add("Blad");
        userNumber.add("+856545,789988");
        userName.add("Nika");
        userNumber.add("-");
        PhoneBook pBook= new PhoneBook(userName,userNumber);
        assertEquals("+856545,789988",pBook.foundNumbers("Blad"));//вывод когда больше 1 номера
        assertEquals("-",pBook.foundNumbers("Nika"));//нет номера
        assertEquals("+756565",pBook.foundNumbers("Nikola"));//вывод когда 1 номер
        assertEquals("Данного пользователя нет в cиcтеме",pBook.foundNumbers("Anna"));//вывод когда нет пользователя
    }
}