package phone;

import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;

public class User implements Comparable<User> {

    private String userName;
    private PhoneNumbers phoneNumbers;

    public User(String userName) {
        if (userName != null) {
            this.phoneNumbers = new PhoneNumbers(new ConcurrentSkipListSet<>());
            this.userName = userName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, phoneNumbers);
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public int compareTo(User o) {
        return phoneNumbers.compareTo(o.phoneNumbers);
    }
}
