package phone;

public interface PhoneBookInterface {

    String REGEX_NUMBER_PHONE = "(\\d+|(\\+|-|\\*|#|-))*";

    void addNewUser(User user);

    void deleteUser(User user);

    void addNumberPhoneToUser(User user, String phoneNumber);

    void deleteNumberPhoneToUser(User user, String phoneNumber);

    String findNumberPhoneFromUser(User user);

    String findNumbersPhoneFromName(String name);

    User findUserFromName(String name);
}
