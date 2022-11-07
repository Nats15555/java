package phone;

import java.util.*;

public class PhoneBook {

    private static final String regexNum = "(\\d+|(\\+|-|\\*|#|-))*";

    /**
     * Поле телефонная книга(хранятся User)
     */
    private final List<User> users = new ArrayList<>();

    /**
     * Конструктор
     */
    public PhoneBook(List<User> user) {
        if (user != null) {
            users.addAll(user);
        }
    }

    /**
     * Переопределение equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBook phoneBook = (PhoneBook) o;
        return users.equals(phoneBook.users);
    }

    /**
     * Переопределение hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    /**
     * Переопределение toString
     */
    @Override
    public String toString() {
        return "PhoneBook{" +
                "users=" + users +
                '}';
    }

    /**
     * Метод добавляет пользователя в телефонную книгу
     *
     * @param user передаем пользователя которого нужно добавить(имя и пустой список номеров) (User)
     * @return boolean если пользователь был добавлен возвращает true иначе false
     * @throws IllegalArgumentException При удалении пользователя нельзя задавать ему номер, параметр должен быть пустой List
     */
    public boolean addUser(User user) {
        if (checkExUser(user)) {
            synchronized (users) {
                Optional<User> userS = findUser(user.getUserName());
                if (userS.isEmpty()) {
                    this.users.add(user);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод удаляет пользователя из телефонной книги
     *
     * @param user (User) передаем пользователя которого нужно удалить(имя и пустой список номеров)
     * @return boolean если пользователь был удален возвращает true иначе false
     * @throws IllegalArgumentException При удалении пользователя нельзя задавать ему номер, параметр должен быть пустой List
     */
    public boolean delUser(User user) {
        if (checkExUser(user)) {
            synchronized (users) {
                Optional<User> userS = findUser(user.getUserName());
                if (userS.isPresent()) {
                    this.users.remove(userS.get());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод добавляет номер по имени
     *
     * @param name   String Имя пользователя
     * @param number String Номер телефона
     * @return boolean если пользователь был добавлен возвращает true иначе false
     * @throws IllegalAccessException Не правильный формат номера
     */
    public boolean addNumber(String name, String number) throws IllegalAccessException {
        if (checkExNumber(name, number)) {
            Optional<User> user = findUser(name.trim());
            if (user.isPresent()) {
                if (!user.get().getUserNumber().contains(number.trim())) {
                    synchronized (user.get()) {
                        user.get().getUserNumber().add(number.trim());
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Метод удаляет номер по имени
     *
     * @param name   String Имя пользователя
     * @param number String Номер телефона
     * @return boolean возвращает true если удаление выполнено корректно иначе false
     * @throws IllegalAccessException Не правильный формат номера
     */
    public boolean delNumber(String name, String number) throws IllegalAccessException {
        if (checkExNumber(name, number)) {
            Optional<User> user = findUser(name.trim());
            if (user.isPresent()) {
                if (user.get().getUserNumber().contains(number.trim())) {
                    synchronized (user.get()) {
                        user.get().getUserNumber().remove(number.trim());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Метод ищет имя пользователя по номеру телефона
     *
     * @param phoneNum String Номер телефона
     * @return String c найденным именем или c уточнениями в зависимости от ситуации
     */
    public String foundName(String phoneNum) {
        if (phoneNum != null && phoneNum.matches(regexNum) && !phoneNum.equals("")) {
            int indexUser = this.users.size() - 1;
            while (indexUser > -1) {
                List<String> buff = this.users.get(indexUser).getUserNumber();
                if (buff.contains(phoneNum.trim())) {
                    return this.users.get(indexUser).getUserName();
                }
                indexUser--;
            }
            return "Такого номера нет ни у кого";
        } else {
            return "Не правильный формат номера";
        }
    }


    /**
     * Метод ищет все номера телефона пользователя по имени
     *
     * @param name String Имя пользователя
     * @return String c найденными номерами или c уточнения в зависимости от ситуации
     */
    public List<String> foundNumbers(String name) {
        List<String> message = new ArrayList<>();
        message.add("Пользователь не может быть Null");
        if (name == null) {
            return message;
        }
        message.clear();
        message.add("Пользователя нет в системе");
        Optional<User> user = findUser(name.trim());
        return user.map(User::getUserNumber)
                .orElse(message);
    }

    /**
     * Метод ищет пользователя по имени
     *
     * @param name String Имя пользователя
     * @return Optional User если пользователь был найден, если такого пользователя нет то пустой Optional User
     */
    private Optional<User> findUser(String name) {
        synchronized (this.users) {
            int indexUser = this.users.size() - 1;
            while (indexUser > -1) {
                User user = this.users.get(indexUser);

                if (user.getUserName().equals(name)) {
                    return Optional.of(user);
                }
                indexUser--;
            }
            return Optional.<User>empty();
        }
    }

    /**
     * Метод проверяет корректность введения пользователя
     *
     * @param user User Пользователь
     * @return boolean true, если пользователь введен корректно, иначе возвращает false.
     * @throws IllegalArgumentException При удалении пользователя нельзя задавать ему номер, параметр должен быть пустой List
     */
    private boolean checkExUser(User user) throws IllegalArgumentException {
        if (user != null && user.getUserNumber().size() > 0) {
            throw new IllegalArgumentException("При удалении пользователя нельзя задавать ему номер, параметр должен быть пустой List");
        }
        if (user == null || user.equals(new User(null, null)) || user.getUserNumber().size() != 0) {
            return false;
        }
        return true;
    }

    /**
     * Метод проверяет корректность введения номера и имени
     *
     * @param name   String Имя пользователя
     * @param number String Номер пользователя
     * @return boolean true если имя и номер введены корректно, иначе возвращает false.
     * @throws IllegalAccessException Не правильный формат номера
     */
    private boolean checkExNumber(String name, String number) throws IllegalAccessException {
        if (name != null && number != null && number.matches(regexNum) && !number.equals("")) {
            return true;
        } else throw new IllegalAccessException("Не правильный формат номера");
    }
}
