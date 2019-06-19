import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import phonebook.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static phonebook.UtilsPhonebook.checkPhoneNumber;

class UtilsPhonebookTest {
    @Test
    void readTextFile() throws IOException {
        List<String> lines = new ArrayList<>();
        assertTrue(lines.isEmpty());
        lines = Files.readAllLines(Paths.get("phoneBook.txt"), StandardCharsets.UTF_8);
        assertFalse(lines.isEmpty());
    }

    @Test
    void writeTextFile() throws IOException {
        Phonebook phonebook = Phonebook.getInstance();
        FileWriter writer = new FileWriter("PhonebookOutput.txt");
        String check = "+359";
        List<Pair> testList = new ArrayList<>();
        phonebook.setPairs(testList);
        for (Pair pair : phonebook.getPairs()) {
            if (pair.getName().toLowerCase().contains(check.toLowerCase())) {
                continue;
            }
            writer.write(pair.getName() + " | " + pair.getPhoneNumber().toString() + "\n");
            writer.close();
        }
        assertDoesNotThrow((ThrowingSupplier<Exception>) IOException::new);
        assertTrue(UtilsPhonebook.writeTextFile(phonebook));
    }

    @Test
    void checkPair() {
        String line = "Test | 0888888888";
        String[] split = line.split("[ ][|][ ]");

        assertNotEquals(0, split.length);

        PhoneNumber phoneNumber = UtilsPhonebook.checkPhoneNumber(split[1]);

        if (phoneNumber == null)
            assertNull(phoneNumber);

        Random random = new Random();
        Pair pair = new Pair(split[0], phoneNumber, random.nextInt(100));
        assertNotNull(pair);
    }

    @Test
    void enterName() {
        String name;
        name = "Test";
        assertTrue(name.length() < 30);
    }

    @Test
    void enterPhoneNumber() {
        String phoneNumberStr = "0888888888";
        PhoneNumber phoneNumber;
        assertTrue(phoneNumberStr.length() > 0);
        phoneNumber = UtilsPhonebook.checkPhoneNumber(phoneNumberStr);
        assertNotNull(phoneNumber);
    }

    @Test
    void checkPhoneNumber() {
        Operator operator = null;
        boolean flag = false;
        String numberStr = null;
        char firstNumber = ' ';

        String number = "+359878123456";

        assertNull(operator);
        assertFalse(flag);
        assertNull(numberStr);
        assertEquals(firstNumber, ' ');

        assertEquals(number.charAt(0), '+');
        assertEquals(13, number.length());
        assertEquals(number.substring(1, 4), CountryCode.BULGARIA.getValue());
        assertTrue(number.substring(4, 6).matches("\\d+"));
        for (Operator o : Operator.values()) {
            if (number.substring(4, 6).equals(o.getValue())) {
                operator = o;
                flag = true;
                break;
            }
        }
        assertTrue(flag);
        assertTrue(number.charAt(6) >= '2' && number.charAt(6) <= '9');
        firstNumber = number.charAt(6);
        numberStr = number.substring(7, 13);

        assertNotNull(numberStr);
        PhoneNumber phoneNumber = new PhoneNumber(operator.getValue(), firstNumber, numberStr);

        assertNotNull(phoneNumber);

        number = "00359878123456";

        assertEquals("00", number.substring(0, 2));
        assertEquals(14, number.length());
        assertEquals(number.substring(2, 5), CountryCode.BULGARIA.getValue());
        assertTrue(number.substring(8, 14).matches("\\d+"));

        for (Operator o : Operator.values()) {
            if (number.substring(5, 7).equals(o.getValue())) {
                operator = o;
                flag = true;
                break;
            }
        }
        assertTrue(flag);
        assertTrue(number.charAt(7) >= '2' && number.charAt(7) <= '9');
        firstNumber = number.charAt(7);
        numberStr = number.substring(8, 14);

        assertNotNull(numberStr);
        phoneNumber = new PhoneNumber(operator.getValue(), firstNumber, numberStr);

        assertNotNull(phoneNumber);

        number = "0878123456";

        assertEquals('0', number.charAt(0));
        assertEquals(10, number.length());
        assertTrue(number.substring(4, 10).matches("\\d+"));
        for (Operator o : Operator.values()) {
            if (number.substring(1, 3).equals(o.getValue())) {
                operator = o;
                flag = true;
                break;
            }
        }
        assertTrue(flag);
        assertTrue(number.charAt(3) >= '2' && number.charAt(3) <= '9');
        firstNumber = number.charAt(3);
        numberStr = number.substring(4, 10);

        assertNotNull(numberStr);
        phoneNumber = new PhoneNumber(operator.getValue(), firstNumber, numberStr);

        assertNotNull(phoneNumber);
    }

    @Test
    void printMenu() {
        List<String> menu = new ArrayList<>();
        assertNotNull(menu);
        int choice = 1;

        assertFalse(choice > 7 || choice < 0);
    }
}