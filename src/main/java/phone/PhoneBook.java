package phone;

import java.util.*;

public class PhoneBook {

    public static final String regexNum = "(\\d+|(\\+|-|\\*|#|-))*";

    private final List<User> users = new ArrayList<>();//пользователи

    public PhoneBook(List<User> user) {
        if (user != null) {
            users.addAll(user);
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

    public boolean addUser(User user) {
        if (checkEx(user)) {
            Optional<User> userS = findUser(user.getUserName());
            if (userS.isEmpty()) {
                this.users.add(user);
                return true;
            }
        }
        return false;
    }

    public boolean delUser(User user) {
        if (checkEx(user)) {
            Optional<User> userS = findUser(user.getUserName());
            if (userS.isPresent()) {
                this.users.remove(userS.get());
                return true;
            }
        }
        return false;
    }

    public boolean addNumder(String name, String phoneNum) throws IllegalStateException, IllegalAccessException {
        if (name != null && phoneNum != null && phoneNum.matches(regexNum) && !phoneNum.equals("")) {
            name.trim();
            phoneNum.trim();
        } else throw new IllegalAccessException("Не правильный формат номера");
        User user = findUser(name).get();
        if (!user.getUserNumber().contains(phoneNum)) {
            this.users.get(this.users.indexOf(user)).getUserNumber().add(phoneNum);
            return true;
        } else {
            throw new IllegalStateException("Пользователь которому вы хотите добавить уже имеет этот номер");
        }
        /*добавляет номер по имени,
         возвращает состояние выполнения функции, True если выполнилось, False если нет*/
    }

    public boolean delNumder(String name, String number) {
        if (name != null && number != null) {
            name.trim();
            number.trim();
        } else return false;
        if (number.matches(regexNum) && !number.equals("")) {
            User user = findUser(name).get();
            if (user.getUserNumber().contains(number)) {
                this.users.get(this.users.indexOf(user)).getUserNumber().remove(number);
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
                List<String> buff = this.users.get(indexUser).getUserNumber();
                if (buff.contains(phoneNum)) {
                    return this.users.get(indexUser).getUserName();
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
        Optional<User> user = findUser(name);
        return user.map(value -> value.getUserNumber().toString().replaceAll("[ |\\[|\\]]", ""))
                .orElse("Пользователя нет в cиcтеме");
        //возвращает все номера пользователя
    }

    private Optional<User> findUser(String name) {
        int indexUser = this.users.size() - 1;
        while (indexUser > -1) {
            User user = this.users.get(indexUser);
            if (user.getUserName().equals(name)) {
                return Optional.of(user);
            }
            indexUser--;
        }
        return Optional.<User>empty();//Данного пользователя нет в cиcтеме
    }

    private boolean checkEx(User user) throws IllegalArgumentException {
        if (user == null || user.equals(new User(null, null)) || user.getUserNumber().size() != 0) {
            if (user.getUserNumber().size() > 0) {
                throw new IllegalArgumentException("При удалении пользователя нельзя задавать ему номер, параметр должен быть пуcтой List");
            }
            return false;
        }
        return true;
    }
}
