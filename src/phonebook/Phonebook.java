package phonebook;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Phonebook {
    private List<Pair> pairs;

    private static Phonebook phonebookInstance = null;

    public static Phonebook getInstance() {
        if (phonebookInstance == null)
            phonebookInstance = new Phonebook();
        return phonebookInstance;
    }

    private Phonebook() {
    }

    public Phonebook(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public static Phonebook createPhonebook(List<Pair> phoneBookList) {
        Phonebook phonebook = Phonebook.getInstance();
        if (!phoneBookList.isEmpty()) {
            System.out.println("A phonebook already exists");
            return phonebook;
        }
        for (String line : readTextFile()) {
            if(checkPair(line)){

            }
        }

        return phonebook;
    }

    public static List<String> readTextFile() {
        List<String> lines = Collections.emptyList();//C:\Users\Kristina\IdeaProjects\Phonebook\phoneBook.txt
        try {
            lines = Files.readAllLines(Paths.get("phoneBook.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading File");
        }
        return lines;
    }

    public static Pair checkPair(String line) {

        boolean result = false;
        String[] split = line.split(" ");
        PhoneNumber phoneNumber = null;

        if (split[1].charAt(0) == '+') {
            if (split[1].substring(1, 4).equals(CountryCode.BULGARIA)) {
                if (split[1].substring(4, 6).equals(Operator.A1.toString())
                        || split[1].substring(4, 6).equals(Operator.TELENOR.toString())
                        || split[1].substring(4, 6).equals(Operator.VIVACOM.toString())) {
                    if (split[1].charAt(6) >= '2' || split[1].charAt(0) <= '9') {
                        phoneNumber.setPrefix("+");
                        phoneNumber.setCountryCallCode(CountryCode.BULGARIA);
                        phoneNumber.setFirstNumber(split[1].charAt(6));
                        phoneNumber.setNumber(split[1].substring(7,13));
                        result = true;
                    }
                }
            }
        } else {
            if (split[1].substring(0, 2).equals("00")) {
                if (split[1].substring(2, 5).equals("359")) {
                    if (split[1].substring(5, 7).equals(Operator.A1.toString())
                            || split[1].substring(5, 7).equals(Operator.TELENOR.toString())
                            || split[1].substring(5, 7).equals(Operator.VIVACOM.toString())) {
                        if (split[1].charAt(8) >= '2' || split[1].charAt(0) <= '9') {
                            result = true;
                        }
                    }
                } else {
                    if (split[1].charAt(0) == '0') {
                            if (split[1].substring(1, 3).equals(Operator.A1.toString())
                                    || split[1].substring(1, 3).equals(Operator.TELENOR.toString())
                                    || split[1].substring(1, 3).equals(Operator.VIVACOM.toString())) {
                                if (split[1].charAt(3) >= '2' || split[1].charAt(0) <= '9') {
                                    result = true;
                                }
                            }
                        }

                }
            }

        }
        return result;
    }

    public static Pair createPair( String[] split){
        Pair pair = new Pair();
        pair.setName(split[0]);

    }
}
