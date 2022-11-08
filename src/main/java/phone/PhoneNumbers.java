package phone;

import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;

public class PhoneNumbers implements Comparable<PhoneNumbers> {

    private ConcurrentSkipListSet<String> userNumbers;

    public PhoneNumbers(ConcurrentSkipListSet<String> userNumbers) {
        this.userNumbers = userNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumbers that = (PhoneNumbers) o;
        return Objects.equals(userNumbers, that.userNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNumbers);
    }

    @Override
    public String toString() {
        return "PhoneNumbers" + userNumbers;
    }

    public ConcurrentSkipListSet<String> getUserNumbers() {
        return userNumbers;
    }

    public void setUserNumbers(ConcurrentSkipListSet<String> userNumbers) {
        this.userNumbers = userNumbers;
    }

    @Override
    public int compareTo(PhoneNumbers o) {
        Integer integer = userNumbers.size();
        Integer integer1 = o.userNumbers.size();
        return integer.compareTo(integer1);
    }
}
