package phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {// передалать это книга а не юзер у юза поля зтринговые
    private String userName = "";         // буфер имен пользователей
    private List<String> userNumber = new ArrayList<String>();  // бефер номеров пользователей

    public User(String userName, List<String> userNumber) {
        if (userName != null && userNumber != null) {
            this.userNumber = (ArrayList<String>) userNumber;
            this.userName = userName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserName().equals(user.getUserName()) &&
                getUserNumber().equals(user.getUserNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getUserNumber());
    }

    @Override
    public String toString() {
        return "User=" +
                "userName='" + userName + '\'' +
                ", userNumber=" + userNumber;
    }

    public String getUserName() { return this.userName; }

    public List<String> getUserNumber() {
        return this.userNumber;
    }

}
