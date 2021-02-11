package phone;

import java.util.ArrayList;
import java.util.Objects;

public class PhoneBook {

    final String regexNum = "(\\d+|(\\+|-|\\*|#|-))*";

    private ArrayList<User> users = new ArrayList<>();//пользователи

    public PhoneBook(ArrayList<User> user) {
        if (user != null) {
            this.users = user;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBook phoneBook = (PhoneBook) o;
        return users.equals(phoneBook.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "users=" + users +
                '}';
    }

    public boolean addUser(User user)
    throws Exception{
        if (user == null || user.equals(new User(null,null)) || user.getUserNumber().size()!=0) {
            if (user.getUserNumber().size()>0){
                throw new  IllegalArgumentException("При cоздании пользователя нельзя задавать ему номер, параметр должен быть пуcтой ArreyList");
            }
            return false;
        }
        ArrayList<String> empty = new ArrayList<>();
        if (findUser(user.getUserName()).equals(new User("", empty))) {
            this.users.add(user);
            return true;
        }
        return false;
    }
/*
    public boolean addUser(String name) {
        if (name != null) name.trim();
        else return false;
        int indexUsers = this.users.size() - 1;
        while (indexUsers > -1) {
            User user = this.users.get(indexUsers);
            if (user.getUserName().equals(name)) {
                return false;
            }
            indexUsers--;
        }

        return true;
        // добавление пользовтеля, в номер телефона пуcтоту.
    }*/

    public boolean delUser(User user){
        if (user == null || user.equals(new User(null,null)) || user.getUserNumber().size()!=0) {
            if (user.getUserNumber().size()>0){
                throw new  IllegalArgumentException("При удалении пользователя нельзя задавать ему номер, параметр должен быть пуcтой ArreyList");
            }
            return false;
        }
        ArrayList<String> empty = new ArrayList<>();
        if (!findUser(user.getUserName()).equals(new User("", empty))) {
            this.users.remove(findUser(user.getUserName()));
            return true;
        }
        return false;
    }
/*
    public boolean delUser(String name) {
        if (name != null) name.trim();
        else return false;
        int indexUser = this.users.size() - 1;
        while (indexUser > -1) {
            User user = this.users.get(indexUser);
            if (user.getUserName().equals(name)) {
                this.users.remove(indexUser);
                return true;
            }
            indexUser--;
        }
        return false;
        /* удаляет пользователя по имени, если прочер то и прочек удалить(и номер удалить),
         **

         возвращает состояние выполнения функции, True если выполнилось, False если нет
    }*/

    public boolean addNumder(String name, String phoneNum)
            throws Exception {
        if (name != null && phoneNum != null) {
            name.trim();
            phoneNum.trim();
        } else throw new IllegalAccessException("Не правильный формат номера");
        if (phoneNum.matches(this.regexNum) && !phoneNum.equals("")) {
            ArrayList<String> buffNum = findUser(name).getUserNumber();
            if (!buffNum.contains(phoneNum)) {
                buffNum.add(phoneNum);
                findUser(name).setUserNumber(buffNum);
                return true;
            } else {
                throw new IllegalStateException("Пользователь которому вы хотите добавить уже имеет этот номер");
            }
        }
        return false;
        /*добавляет номер по имени,
         возвращает состояние выполнения функции, True если выполнилось, False если нет*/
    }

    public boolean delNumder(String name, String number) {
        if (name != null && number != null) {
            name.trim();
            number.trim();
        } else return false;
        if (number.matches(this.regexNum) && !number.equals("")) {
            ArrayList<String> buffNum = findUser(name).getUserNumber();
            if (buffNum.contains(number)) {
                buffNum.remove(number);
                findUser(name).setUserNumber(buffNum);
                return true;
            }
        }
        return false;//Когда не нашел такой номер
        // удаляет номер по имени.
    }

    public String foundName(String phoneNum) {
        if (phoneNum != null && phoneNum.matches(this.regexNum) && !phoneNum.equals("")) {
            phoneNum.trim();
            int indexUser = this.users.size() - 1;
            while (indexUser > -1) {
                ArrayList<String> buff = this.users.get(indexUser).getUserNumber();
                if (buff.size() >= 1) {
                    int len = buff.size() - 1;
                    while (len > -1) {
                        if (buff.get(len).equals(phoneNum)) {
                            return this.users.get(indexUser).getUserName();
                        }
                        len--;
                    }
                }
                indexUser--;
            }
            return "Такого номера нет ни у кого";
        } else {
            return "Не правильный формат номера";
        }

        // ищет имя пользователя по номеру телефона, возвращает Имя
    }

    public String foundNumbers(String name) {
        if (name != null) name.trim();
        else {
            return "Пользователь не может быть Null";//Пользователь не может быть null
        }
        User user = findUser(name);
        ArrayList<String> empty = new ArrayList<>();
        if (!user.equals(new User("", empty))) {
            return user.getUserNumber().toString().replaceFirst("\\[", "")
                    .replaceFirst("]", "");
        }
        return "Пользователя нет в cиcтеме";
        //возвращает все номера пользователя
    }

    private User findUser(String name) {
        int indexUser = this.users.size() - 1;
        while (indexUser > -1) {
            User user = this.users.get(indexUser);
            if (user.getUserName().equals(name)) {
                return user;
            }
            indexUser--;
        }
        ArrayList<String> empty = new ArrayList<>();
        return new User("", empty);//Данного пользователя нет в cиcтеме
    }
}
