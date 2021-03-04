package phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {// передалать это книга а не юзер у юза поля зтринговые

    /**
     * Поле имя пользователя
     */
    private String userName = "";         // буфер имен пользователей

    /**
     * cпиcок номеров пользователя
     */
    private List<String> userNumber = new ArrayList<String>();  // бефер номеров пользователей

    /**
     * Конcтруктор
     */
    public User(String userName, List<String> userNumber) {
        if (userName != null && userNumber != null) {
            this.userNumber = (ArrayList<String>) userNumber;
            this.userName = userName;
        }
    }

    /**
     * Пререопределние equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserName().equals(user.getUserName()) &&
                getUserNumber().equals(user.getUserNumber());
    }

    /**
     * Пререопределние hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getUserNumber());
    }

    /**
     * Пререопределние toString
     */
    @Override
    public String toString() {
        return "User=" +
                "userName='" + userName + '\'' +
                ", userNumber=" + userNumber;
    }

    /**
     * Метод получаения имени
     * @return имя
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Метод получаения номеров
     * @return номера
     */
    public List<String> getUserNumber() {
        return this.userNumber;
    }

}
