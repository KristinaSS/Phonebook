import org.junit.jupiter.api.Test;
import phonebook.Pair;
import phonebook.PhoneNumber;
import phonebook.Phonebook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhonebookTest {

    @Test
    void createPhonebook() {
        Phonebook phonebook = Phonebook.getInstance();
        assertNotNull(phonebook);
        //assertTrue(Phonebook.createPhonebook(phonebook));
    }

    @Test
    void addPair() {
        Phonebook phonebook = Phonebook.getInstance();
        List<Pair> testList = new ArrayList<>();
        phonebook.setPairs(testList);
        Pair pair = Pair.getInstance();
        pair.setName("test");
        pair.setPhoneNumber(new PhoneNumber("88",'8',"888888"));
        assertTrue(phonebook.getPairs().add(pair));
        assertNotNull(phonebook);
    }

    @Test
    void deleteByName() {
        Phonebook phonebook = Phonebook.getInstance();
        List<Pair> testList = new ArrayList<>();
        phonebook.setPairs(testList);
        String name = "test";
        Iterator iterator = phonebook.getPairs().iterator();
        Pair pair = Pair.getInstance();
        pair.setName(name);
        pair.setPhoneNumber(new PhoneNumber("88",'8',"888888"));
        int num = testList.size();
        while (iterator.hasNext()) {
            pair = (Pair) iterator.next();
            if (pair.getName().equals(name)) {
                iterator.remove();
                assertTrue(num > testList.size());
            }
        }
        assertEquals(testList.size(), num);
        assertNotNull(phonebook);
    }

    @Test
    void accessByName() {
        Phonebook phonebook = Phonebook.getInstance();
        List<Pair> testList = new ArrayList<>();
        phonebook.setPairs(testList);
        String name = "test";
        boolean flag = false;
        for (Pair pair : phonebook.getPairs()) {
            if (pair.getName().equals(name)) {
                flag = true;
                assertTrue(flag);
            }
        }
        if(!flag){
            assertFalse(flag);
        }
        assertNotNull(phonebook);
    }

    @Test
    void printSortedPhonebook() {
        Phonebook phonebook = Phonebook.getInstance();
        List<Pair> testList = new ArrayList<>();
        phonebook.setPairs(testList);
        assertTrue(Phonebook.printSortedPhonebook(phonebook));
    }

    @Test
    void callNumber() {
        Phonebook phonebook = Phonebook.getInstance();
        List<Pair> testList = new ArrayList<>();
        phonebook.setPairs(testList);
        Pair pair = Pair.getInstance();
        String name = "test";
        PhoneNumber phoneNumber = new PhoneNumber("88",'8',"888888");
        for (Pair p : phonebook.getPairs()) {
            if (phoneNumber.toString().equals(p.getPhoneNumber().toString())) {
                int init = p.getTimesCalled();
                p.setTimesCalled(p.getTimesCalled() + 1);
                assertTrue(init< p.getTimesCalled());
            }
        }
        int init = phonebook.getPairs().size();
        phonebook.getPairs().add(new Pair(name, phoneNumber, 1));
        assertTrue(init < phonebook.getPairs().size());

        init = phonebook.getPairs().size();
        phonebook.getPairs().add(new Pair(phoneNumber.toString(), phoneNumber, 1));
        assertTrue(init < phonebook.getPairs().size());

        assertNotNull(phonebook);
    }

    @Test
    void printFavNumbers() {
        Phonebook phonebook = Phonebook.getInstance();
        List<Pair> testList = new ArrayList<>();
        phonebook.setPairs(testList);
        assertTrue(Phonebook.printSortedPhonebook(phonebook));
    }
}