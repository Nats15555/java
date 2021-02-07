package phone;

import java.util.ArrayList;

public class User {// передалать это книга а не юзер у юза поля зтринговые
    private String userName;         // буфер имен пользователей
    private ArrayList<String> userNumber;  // бефер номеров пользователей

    public User(String userName, ArrayList<String> userNumber) {
        this.userName = userName;
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return this.userName;
    }

    public ArrayList<String> getUserNumber() {
        return this.userNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserNumber(ArrayList<String> userNumber) {
        this.userNumber = userNumber;
    }
}
