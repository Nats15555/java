package phone;


import java.util.ArrayList;

public class PhoneBook {

    final String regexNum = "(\\d+|(\\+|-|\\*|#|-))*";

    private ArrayList<User> users;//пользователи

    public PhoneBook(ArrayList<User> user) {
        this.users = user;
    }

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
        ArrayList<String> emptyNum = new ArrayList();
        this.users.add(new User(name, emptyNum));
        return true;
        // добавление пользовтеля, в номер телефона пуcтоту.
    }

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
         */
        // возвращает состояние выполнения функции, True если выполнилось, False если нет
    }

    public boolean addNumder(String name, String phoneNum) {
        if (name != null && phoneNum != null) {
            name.trim();
            phoneNum.trim();
        }else return false; //Не правильный формат номера
        if (phoneNum.matches(this.regexNum) && !phoneNum.equals("")) {
            int indexUser = Integer.parseInt(foundNumbers(name).get(foundNumbers(name).size() - 1));
            ArrayList<String> buffNum = this.users.get(indexUser).getUserNumber();
            if (!buffNum.contains(phoneNum)) {
                buffNum.add(phoneNum);
                this.users.get(indexUser).setUserNumber(buffNum);
                return true;
            } else {
                return false;//Пользователь которому вы хотите добавить уже имеет этот номер
            }
        }
        return false;
        /*добавляет номер по имени,
         возвращает состояние выполнения функции, True если выполнилось, False если нет*/
    }


    public boolean delNumder(String name, String number) {
        if(name!=null && number!=null){
            name.trim();
            number.trim();
        }else return false;
        if (number.matches(this.regexNum) && !number.equals("")) {
            int indexUser=Integer.parseInt(foundNumbers(name).get(foundNumbers(name).size()-1));
            ArrayList<String> buffNum = this.users.get(indexUser).getUserNumber();
            if(buffNum.contains(number)){
                buffNum.remove(number);
                this.users.get(indexUser).setUserNumber(buffNum);
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


    public ArrayList<String> foundNumbers(String name) {
        ArrayList<String> reT=new ArrayList<String>();
        if (name != null) name.trim();
        else {
            reT.add("Пользователь не может быть null");
            return reT;
        }
        int indexUser = this.users.size() - 1;
        while (indexUser > -1) {
            User user = this.users.get(indexUser);
            if (user.getUserName().equals(name)) {
                reT.add(user.getUserNumber().toString());
                reT.add(String.valueOf(indexUser));
                return reT;
            }
            indexUser--;
        }
        reT.add("Данного пользователя нет в cиcтеме");
        return reT;
        //возвращает все номера пользователя, поcледнее значене это индекc в cиcтеме
    }

}
