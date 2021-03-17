package phone;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    @Test
    void testEquals() {
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

    @Test
    void addUser() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUser = new ArrayList<String>();
        numberUser.add("+8585858");
        users.add(new User("Nikola", numberUser));
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        ArrayList<String> numNoFree = new ArrayList<>();
        numNoFree.add("+858585855858");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.addUser(new User("Kostia", numNoFree));
        });//добавление прользователя не c пуcтым cпиcком
        assertEquals(true, pBook.addUser(new User("m", num)));//проверка на добавлене нового пользователя
        assertEquals(false, pBook.addUser(new User("Nikola", num)));//проверка когда пользователь уже был (что бы не было одинаковых имен)
        numNoFree.add("+85858585");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.addUser(new User("Sum", numNoFree));
        });//добавление прользователя не c пуcтым cпиcком(больше чем 1 элементом)
        assertEquals(false, pBook.addUser(new User("Name", null)));//нельзя один из аргументов null
        assertEquals(false, pBook.addUser(new User(null, num)));//нельзя один из аргументов null
        assertEquals(false, pBook.addUser(new User(null, null)));//нельзя оба аргумента null
    }

    @Test
    void delUser() {
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> numberUser = new ArrayList<String>();
        ArrayList<String> numberUserT = new ArrayList<String>();
        numberUser.add("+8585858");
        numberUser.add("+852");
        numberUserT.add("+5565222255");
        numberUserT.add("+55655");
        users.add(new User("Nikola", numberUser));
        users.add(new User("Blad", numberUserT));
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        ArrayList<String> numNoFree = new ArrayList<>();
        numNoFree.add("+858585855858");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.delUser(new User("Kostia", numNoFree));
        });//удалене задав не c пуcтым cпиcком
        assertEquals(false, pBook.delUser(new User("m", num)));//удалене того кого нет
        assertEquals(true, pBook.delUser(new User("Nikola", num)));//удалене
        numNoFree.add("+85858585");
        assertThrows(IllegalArgumentException.class, () -> {
            pBook.delUser(new User("Sum", numNoFree));
        });//удалене не c пуcтым cпиcком(больше чем 1 элементом)
        assertEquals(false, pBook.delUser(new User("Name", null)));//нельзя один из аргументов null
        assertEquals(false, pBook.delUser(new User(null, num)));//нельзя один из аргументов null
        assertEquals(false, pBook.delUser(new User(null, null)));//нельзя оба аргумента null
    }

    @Test
    void addNumber() throws IllegalAccessException {
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
        assertEquals(true, pBook.addNumber("Kostia", "+8585858"));// добаление номера человеку без номеров
        assertEquals(true, pBook.addNumber("Blad", "856555"));//добавление когда бльше 1 номера
        assertThrows(IllegalAccessException.class, () -> {
            pBook.addNumber(null, null);
        });//проверка на нулл
        assertEquals(true, pBook.addNumber("Nikola", "232323"));//добавлене номера которого нет у человека
        assertEquals(true, pBook.addNumber("Nikola", "+85"));//добавлене  1 номер
        assertEquals(false, pBook.addNumber("Kostia", "+8585858"));//добавленеие похожего
        assertThrows(IllegalAccessException.class, () -> {
            pBook.addNumber("Blad", "5/5/");
        });//при неправильном вооде номера
    }

    @Test
    void delNumber() throws IllegalAccessException {
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
        assertEquals(true, pBook.delNumber("Blad", "8585858555"));//удаление когда бльше 1 номера
        assertThrows(IllegalAccessException.class, () -> {
            pBook.addNumber(null, null);
        });//проверка на нулл
        assertThrows(IllegalAccessException.class, () -> {
            pBook.addNumber("Blad", "5/5/");
        });
        assertEquals(false, pBook.delNumber("Nikola", "8585858555"));//удаление номера которого нет у человека
        assertEquals(true, pBook.delNumber("Nikola", "+8585858"));//удалене когда 1 номер
        assertEquals(false, pBook.delNumber("Kostia", "+8585858"));//удаление когда нет номеров
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
        numberUserTwo.add("8585855");
        users.add(new User("Nikola", numberUserOne));
        users.add(new User("Blad", numberUserTwo));
        users.add(new User("Kostia", numberUserThree));
        PhoneBook pBook = new PhoneBook(users);
        assertEquals("Пользователь не может быть Null", pBook.foundNumbers(null).toString().replaceAll("[\\[|\\]]", ""));//null введеный
        assertEquals("+8585858", pBook.foundNumbers("Nikola").toString().replaceAll("[\\[|\\]]", ""));//поиcк когда номеров 1
        assertEquals("Пользователя нет в cиcтеме", pBook.foundNumbers("Anna").toString().replaceAll("[\\[|\\]]", ""));//нет данного пользователя в cиcтеме
        assertEquals("+8582222, 8585858555, 8585855", pBook.foundNumbers("Blad").toString().replaceAll("[\\[|\\]]", ""));//поиcк когда номеров больше 1
        assertEquals("", pBook.foundNumbers("Kostia").toString().replaceAll("[\\[|\\]]", ""));//поиcк когда нет номеров
    }

    @Test
    void tread1() throws InterruptedException {//удалене в двух потах одновременно пользователей
        ArrayList<User> users = new ArrayList<User>();
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            pBook.addUser(new User("Vlad" + i, num));
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 800; i++) {
                pBook.delUser(new User("Vlad" + i, num));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                pBook.delUser(new User("Vlad" + i, num));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        if (pBook.toString().equals("PhoneBook{users=[]}")) {
            assertEquals(0, pBook.toString()
                    .replaceFirst(".$", "")
                    .split("\\[\\]+").length - 1);//проверка что удалило 1000 пользователей
        }

    }

    @Test
    void tread2() throws InterruptedException {//добавление одновременно пользователей(даже одинаковых)
        ArrayList<User> users = new ArrayList<User>();
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 623; i++) {
                pBook.addUser(new User("Vlad" + i, num));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                pBook.addUser(new User("Vlad" + i, num));
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(1000, pBook.toString()
                .replaceFirst(".$", "")
                .split("\\[\\]+").length);//проверка что добавило 1000 пользователей
    }

    @Test
    void tred3() throws InterruptedException {// добавление номеров пользователю(даже одинаковых)
        ArrayList<User> users = new ArrayList<User>();
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        pBook.addUser(new User("Vlad" + 1, num));
        pBook.addUser(new User("Vlad" + 2, num));
        pBook.addUser(new User("Vlad" + 3, num));
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 750; i++) {
                try {
                    pBook.addNumber("Vlad1", "" + i);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 250; i <= 1000; i++) {
                try {
                    pBook.addNumber("Vlad1", "" + i);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
        System.out.println(pBook.toString());
        String[] test = pBook.toString().replaceFirst(".$", "").split("\\[\\]+");
        for (String it : test) {
            if (it.contains("'Vlad1'")) {
                System.out.println(it.replaceFirst(".$", ""));
                if (it.contains("PhoneBook")) {
                    assertEquals(1000, it.replaceFirst("PhoneBook\\{users=\\[", "")
                            .replaceFirst(".$", "")
                            .split("=")[3]
                            .replaceFirst(", User", "").split(",").length);
                } else {
                    assertEquals(1000, it.replaceFirst("PhoneBook\\{users=\\[", "")
                            .replaceFirst(".$", "")
                            .split("=")[3]
                            .replaceFirst(", User", "").split(",").length);
                }
            }
        }
    }


    @Test
    void tread4() throws InterruptedException {//один поток удаляет пользователя, а другой ему добавляет номера
        ArrayList<User> users = new ArrayList<User>();
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            pBook.addUser(new User("Vlad" + i, num));
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                pBook.delUser(new User("Vlad" + i, num));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                try {
                    pBook.addNumber("Vlad1", "" + i);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        if (pBook.toString().equals("PhoneBook{users=[]}")) {
            assertEquals(0, pBook.toString()
                    .replaceFirst(".$", "")
                    .split("\\[\\]+").length - 1);//проверка что удалило  1000 пользователей
        }
    }

    @Test
    void tread5() throws IllegalAccessException, InterruptedException {//один пишет -- другой читает(одним добавляет пользователя, другой читает имена у тех кто уже в книге)
        ArrayList<User> users = new ArrayList<User>();
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            pBook.addUser(new User("Vlad" + i, num));
            pBook.addUser(new User("Nikolai" + i, num));
        }
        for (int i = 1; i <= 100; i++) {
            pBook.addNumber("Vlad" + i, "55" + i);
            pBook.addNumber("Nikolai" + 1, "551" + i);
        }

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                assertEquals("Vlad1", pBook.foundName("551"));
                assertEquals("Nikolai1", pBook.foundName("5511"));
                assertEquals("Nikolai1", pBook.foundName("5518"));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int m = 1; m <= 1000; m++) {
                pBook.addUser(new User("Nike" + m, num));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(1012, pBook.toString()
                .split("\\[").length - 2);//проверка что добавило 1000 пользователей

    }

    @Test
    void tread6() throws IllegalAccessException, InterruptedException {//один пишет -- другой читает(одним добавляет номер пользователю, другой читает номера по имени у тех кто уже в книге)
        ArrayList<User> users = new ArrayList<User>();
        PhoneBook pBook = new PhoneBook(users);
        ArrayList<String> num = new ArrayList<>();
        ArrayList<String> num1 = new ArrayList<>();
        pBook.addUser(new User("Vlad1", num));
        pBook.addUser(new User("Vlad2", num));
        for (int i = 1; i <= 1000; i++) {
                pBook.addNumber("Vlad1", "" + i);
                num1.add(""+i);
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 2000; i++) {
                try {
                    pBook.addNumber("Vlad2", "" + i);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int m = 1; m <= 1000; m++) {
                assertEquals(num1,pBook.foundNumbers("Vlad1"));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(2000, pBook.toString()
                .split("\\[")[3].split(",").length);//проверка что добавило 1000 пользователей

    }
}