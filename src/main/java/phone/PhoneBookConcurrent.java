package phone;

import java.util.concurrent.ConcurrentSkipListSet;

public class PhoneBookConcurrent implements PhoneBookInterface {

    private final ConcurrentSkipListSet<User> phoneBook;

    public PhoneBookConcurrent() {
        this.phoneBook = new ConcurrentSkipListSet<>();
    }

    @Override
    public void addNewUser(User user) { //+
        phoneBook.add(user);
    }

    @Override
    public void deleteUser(User user) { //+
        phoneBook.remove(user);
    }

    @Override
    public void addNumberPhoneToUser(User user, String phoneNumber) {
        User findUser = findUserFromName(user.getUserName());
        findUser.getPhoneNumbers().getUserNumbers().add(phoneNumber);
    }

    @Override
    public void deleteNumberPhoneToUser(User user, String phoneNumber) {
        User findUser = findUserFromName(user.getUserName());
        findUser.getPhoneNumbers().getUserNumbers().remove(phoneNumber);
    }

    @Override
    public String findNumberPhoneFromUser(User user) {
        return findNumbersPhoneFromName(user.getUserName());
    }

    @Override
    public String findNumbersPhoneFromName(String name) {
        User user = findUserFromName(name);
        return user != null ? user.getPhoneNumbers().toString() : "Номер не нашелся";
    }

    @Override
    public User findUserFromName(String name) {
        for (User temp : phoneBook) {
            if (temp.getUserName().equals(name)) {
                return temp;
            }
        }
        return null;
    }
}
