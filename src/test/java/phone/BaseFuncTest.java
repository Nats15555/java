package phone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseFuncTest {

    @Test
    void baseFuncPhoneBookConcurrent() {
        PhoneBookConcurrent phoneBookConcurrent = new PhoneBookConcurrent();
        User user = new User("SomeName");
        phoneBookConcurrent.addNewUser(user);
        phoneBookConcurrent.addNumberPhoneToUser(new User("SomeName"), "8825");
        assertEquals("PhoneNumbers[8825]", phoneBookConcurrent.findNumbersPhoneFromName("SomeName"));
        phoneBookConcurrent.addNumberPhoneToUser(new User("SomeName"), "8822225");
        assertTrue(phoneBookConcurrent.findNumbersPhoneFromName("SomeName").contains("8822225"));
        assertTrue(phoneBookConcurrent.findNumberPhoneFromUser(new User("SomeName")).contains("8822225"));
        assertEquals("SomeName", phoneBookConcurrent.findUserFromName("SomeName").getUserName());
        phoneBookConcurrent.deleteNumberPhoneToUser(new User("SomeName"), "8822225");
        assertFalse(phoneBookConcurrent.findNumberPhoneFromUser(new User("SomeName")).contains("8822225"));
        phoneBookConcurrent.deleteUser(user);
        assertNull(phoneBookConcurrent.findUserFromName("SomeName"));
    }

    @Test
    void baseFuncPhoneBookNoConcurrent() {
        PhoneBookNoConcurrent phoneBookNoConcurrent = new PhoneBookNoConcurrent();
        phoneBookNoConcurrent.addNewUser(new User("SomeName"));
        phoneBookNoConcurrent.addNumberPhoneToUser(new User("SomeName"), "8825");
        assertEquals("PhoneNumbers[8825]", phoneBookNoConcurrent.findNumbersPhoneFromName("SomeName"));
        phoneBookNoConcurrent.addNumberPhoneToUser(new User("SomeName"), "8822225");
        assertTrue(phoneBookNoConcurrent.findNumbersPhoneFromName("SomeName").contains("8822225"));
        assertTrue(phoneBookNoConcurrent.findNumberPhoneFromUser(new User("SomeName")).contains("8822225"));
        assertEquals("SomeName", phoneBookNoConcurrent.findUserFromName("SomeName").getUserName());
        phoneBookNoConcurrent.deleteNumberPhoneToUser(new User("SomeName"), "8822225");
        assertFalse(phoneBookNoConcurrent.findNumberPhoneFromUser(new User("SomeName")).contains("8822225"));
        phoneBookNoConcurrent.deleteUser(new User("SomeName"));
        assertNull(phoneBookNoConcurrent.findUserFromName("SomeName"));
    }

}
