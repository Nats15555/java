package phone;

import java.lang.reflect.Array;
import java.util.*;

public class PhoneBook {

    public static final String regexNum = "(\\d+|(\\+|-|\\*|#|-))*";

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

    public boolean addUser(User user){
        if (user == null || user.equals(new User(null,null)) || user.getUserNumber().size()!=0) {
            if (user.getUserNumber().size()>0){
                throw new  IllegalArgumentException("При cоздании пользователя нельзя задавать ему номер, параметр должен быть пуcтой ArreyList");
            }
            return false;
        }
        Object userS=findUser(user.getUserName());
        if (userS==Optional.empty()) {
            this.users.add(user);
            return true;
        }
        return false;
    }

    public boolean delUser(User user){
        if (user == null || user.equals(new User(null,null)) || user.getUserNumber().size()!=0) {
            if (user.getUserNumber().size()>0){
                throw new  IllegalArgumentException("При удалении пользователя нельзя задавать ему номер, параметр должен быть пуcтой ArreyList");
            }
            return false;
        }
        Object userS=findUser(user.getUserName());
        if (userS!=Optional.empty()) {
            List<String> buffNum = Arrays.asList(foundNumbers(user.getUserName()).split(","));
            User userF = new User(user.getUserName(),new ArrayList<>(buffNum));
            int index = this.users.indexOf(userF);
            this.users.remove(index);
            return true;
        }
        return false;
    }

    public boolean addNumder(String name, String phoneNum) throws Exception {
        if (name != null && phoneNum != null) {
            name.trim();
            phoneNum.trim();
        } else throw new IllegalAccessException("Не правильный формат номера");
        if (phoneNum.matches(regexNum) && !phoneNum.equals("")) {
            List<String> buffNum = Arrays.asList(foundNumbers(name).split(","));
            ArrayList<String> num = new ArrayList<>(buffNum);
            num.removeAll(Collections.singleton(""));
            if (!buffNum.contains(phoneNum)) {
                User user = new User(name,num);
                int index = this.users.indexOf(user);
                this.users.get(index).getUserNumber().add(phoneNum);
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
        if (number.matches(regexNum) && !number.equals("")) {
            List<String> buffNum = Arrays.asList(foundNumbers(name).split(","));
            if (buffNum.contains(number)) {
                User user = new User(name,new ArrayList<String>(buffNum));
                int index = this.users.indexOf(user);
                this.users.get(index).getUserNumber().remove(number);
                return true;
            }
        }
        return false;//Когда не нашел такой номер
        // удаляет номер по имени.*/
    }

    public String foundName(String phoneNum) {
        if (phoneNum != null && phoneNum.matches(regexNum) && !phoneNum.equals("")) {
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
        Object user = findUser(name);
        if (user != Optional.empty()) {
            return Arrays.asList(user.toString().split("=")).get(user.toString().split("=").length-1).replaceFirst("\\[", "")
                    .replaceFirst("]", "").replaceAll(" ","");
        }
        return "Пользователя нет в cиcтеме";
        //возвращает все номера пользователя
    }

    private Object findUser(String name) {
        int indexUser = this.users.size() - 1;
        while (indexUser > -1) {
            User user = this.users.get(indexUser);
            if (user.getUserName().equals(name)) {
                return user;
            }
            indexUser--;
        }
        Optional<User> userOptional = Optional.empty();
        return userOptional;//Данного пользователя нет в cиcтеме
    }
}
