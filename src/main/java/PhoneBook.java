import java.util.ArrayList;

public class PhoneBook {

    private ArrayList<String> userName;         // буфер имен пользователей
    private ArrayList<String> userNumber;  // бефер номеров пользователей

    public PhoneBook(ArrayList<String> userName, ArrayList<String> userNumber) {
        this.userName = userName;
        this.userNumber = userNumber;
    }

    public ArrayList<String> addUser(String name) {
        System.out.println(this.userName);
        System.out.println(this.userNumber);

        int index = this.userName.indexOf(name);
        if (index == -1) {
            this.userName.add(name);
            this.userNumber.add("-");
        }
        System.out.println(this.userName);
        System.out.println(this.userNumber);
        return this.userName;
        // возвращает состояние выполнения функции, True если выполнилось, False если нет
        // добавление пользовтеля, в номер телефона прочерк.
    }

    public ArrayList<String> delUser(String name) {
        int index = this.userName.indexOf(name);
        System.out.println(this.userName);
        System.out.println(this.userNumber);
        if (index > -1) {
            this.userName.remove(index);
            this.userNumber.remove(index);
            System.out.println(this.userName);
            System.out.println(this.userNumber);
        }
        return this.userName;
        /* удаляет пользователя по имени, если прочер то и прочек удалить(и номер удалить),
         **
         */
        // возвращает состояние выполнения функции, True если выполнилось, False если нет
    }

    public ArrayList<String> addNumder(String name, String phoneNum) {
        int index = this.userName.indexOf(name);
        System.out.println(this.userName);
        System.out.println(this.userNumber);
        if (index > -1 && this.userNumber.get(index).equals("-")) {//добавлене у того у кого нет телефонов вообще
            this.userNumber.set(index, phoneNum);
        } else if (index > -1 && !this.userNumber.get(index).equals("-")) {
            String[] buff = this.userNumber.get(index).split(",");
            int i = buff.length;
            boolean ex = false;
            if (i > 1) {
                while (i - 1 > -1) {
                    if (buff[i - 1].equals(phoneNum)) {
                        ex = true;
                    }
                    i--;
                }
                if (!ex) {
                    this.userNumber.set(index, this.userNumber.get(index) + "," + phoneNum);
                }
            } else {
                this.userNumber.set(index, this.userNumber.get(index) + "," + phoneNum);
            }
        } else {
            System.out.println("Пользователь которому вы хотите добавить телефон еще не добавлен");
        }
        System.out.println(this.userName);
        System.out.println(this.userNumber);
        return this.userNumber;
        /* добавляет номер по имени, если имен больше одного,
         ** то спрашивает кому именно добавить.
         */
        // возвращает состояние выполнения функции, True если выполнилось, False если нет

    }

    public ArrayList<String> delNumder(String name, String number) {
        int index = this.userName.indexOf(name);
        if (index > -1 && this.userNumber.get(index).equals("-")) {
            System.out.println("У данного пользователя нет номеров телефона которые можно удалить");
        } else if (index > -1 && !this.userNumber.get(index).equals("-")) {
            String[] m = this.userNumber.get(index).split(",");
            int buff = m.length - 1;
            StringBuilder sort = new StringBuilder();
            while (buff >= 0) {
                if (!m[buff].equals(number)) {
                    if (sort.toString().equals("")) {
                        sort.append(m[buff]);
                    } else {
                        sort.append(",").append(m[buff]);
                    }
                }
                buff--;
            }
            if(this.userNumber.get(index).equals(number) || this.userNumber.get(index).equals("-")){
                this.userNumber.set(index,"-");
            }else{
                this.userNumber.set(index, sort.toString());
            }
        } else {
            System.out.println("Данного пользователя нет в cиcтеме, удалене номер невозможно");
        }
        /* удаляет номер по имени.
         */
        return this.userNumber;
    }

    public String foundName(String phoneNum) {
        int i = this.userNumber.size() - 1;
        while (i > -1) {
            String[] buff = this.userNumber.get(i).split(",");
            if (buff.length > 1 || buff[0].equals(phoneNum)) {
                int len = buff.length - 1;
                while (len > -1) {
                    if (buff[len].equals(phoneNum)) {
                        return this.userName.get(i);
                    }
                    len--;
                }
            }
            i--;
        }
        return "Такого номера нет ни у кого";
        /* ищет имя пользователя по номеру телефона, возвращает Имя
         */
    }


    public String foundNumbers(String name) {
        int index = this.userName.indexOf(name);
        if (index > -1) {
            System.out.println(this.userNumber.get(index));
            return this.userNumber.get(index);
        } else {
            return "Данного пользователя нет в cиcтеме";
        }

        /*возвращает все номера пользователя

         **формат вывода:
         **1. Миша -:- +71234567890, +79876543210
         **2. Миша -:- +73214567890
         */
    }
}
