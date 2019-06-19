package phonebook;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UtilsPhonebook {

    //singleton

    private static UtilsPhonebook utilsPhonebookInstance = null;

    public static UtilsPhonebook getInstance() {

        if (utilsPhonebookInstance == null)
            utilsPhonebookInstance = new UtilsPhonebook();
        return utilsPhonebookInstance;
    }

    private UtilsPhonebook() {
    }

    //other

    private final static Scanner scanner = new Scanner(System.in);

    //Functions

    public static List<String> readTextFile() {
        List<String> lines = Collections.emptyList();//C:\Users\Kristina\IdeaProjects\Phonebook\phoneBook.txt
        try {
            lines = Files.readAllLines(Paths.get("phoneBook.txt"), StandardCharsets.UTF_8);
            System.out.println("Successfully read!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading File");
        }
        return lines;
    }

    public static boolean writeTextFile(Phonebook phonebook) {
        try {
            FileWriter writer = new FileWriter("PhonebookOutput.txt");
            String check = "+359";
            for (Pair pair : phonebook.getPairs()) {
                if (pair.getName().toLowerCase().contains(check.toLowerCase()))
                    continue;
                writer.write(pair.getName() + " | " + pair.getPhoneNumber().toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing File");
        }
        System.out.println("Successfully written!");
        return true;
    }

    static Pair checkPair(String line) {

        String[] split = line.split("[ ][|][ ]");

        PhoneNumber phoneNumber = null;

        phoneNumber = checkPhoneNumber(split[1]);

        if (phoneNumber == null)
            return null;

        Random random = new Random();
        return new Pair(split[0], phoneNumber, random.nextInt(100));
    }

    static String enterName() {
        String name;
        while (true) {
            scanner.nextLine();
            System.out.println("Enter name:");
            name = scanner.nextLine();
            if (name.length() > 30) {
                System.out.println("Name too long");
                continue;
            }
            break;
        }
        return name;
    }

    static PhoneNumber enterPhoneNumber() {
        String phoneNumberStr;
        PhoneNumber phoneNumber;
        scanner.nextLine();
        while (true) {
            System.out.println("Enter PhoneNumber");
            phoneNumberStr = scanner.nextLine();
            phoneNumber = checkPhoneNumber(phoneNumberStr);
            if (phoneNumber == null)
                continue;
            break;
        }
        return phoneNumber;
    }

    public static PhoneNumber checkPhoneNumber(String number) {
        Operator operator = null;
        boolean flag = false;
        String numberStr = null;
        char firstNumber = ' ';

        if (number.charAt(0) == '+'
                && number.length() == 13
                && number.substring(1, 4).equals(CountryCode.BULGARIA.getValue())) {//+359878123456
            for (Operator o : Operator.values()) {
                if (number.substring(4, 6).equals(o.getValue())) {
                    operator = o;
                    flag = true;
                    break;
                }
            }
            if ((number.charAt(6) >= '2' && number.charAt(6) <= '9')
                    && flag
                    && number.substring(7, 13).matches("\\d+")) {

                firstNumber = number.charAt(6);
                numberStr = number.substring(7, 13);
            } else {
                operator = null;
                flag = false;
            }

        }
        if (number.substring(0, 2).equals("00")
                && number.length() == 14
                && number.substring(2, 5).equals(CountryCode.BULGARIA.getValue())) {//00 359 87 8 123456,
            for (Operator o : Operator.values()) {
                if (number.substring(5, 7).equals(o.getValue())) {
                    operator = o;
                    flag = true;
                    break;
                }
            }
            if ((number.charAt(7) >= '2' && number.charAt(7) <= '9')
                    && flag
                    && number.substring(8, 14).matches("\\d+")) {
                firstNumber = number.charAt(7);
                numberStr = number.substring(8, 14);
            } else {
                operator = null;
                flag = false;
            }
        }
        if (number.charAt(0) == '0' && number.length() == 10) { // 0 87 8 123456
            for (Operator o : Operator.values()) {
                if (number.substring(1, 3).equals(o.getValue())) {
                    operator = o;
                    flag = true;
                    break;
                }
            }
            if ((number.charAt(3) >= '2'
                    && number.charAt(3) <= '9')
                    && flag && number.substring(4, 10).matches("\\d+")) {
                firstNumber = number.charAt(3);
                numberStr = number.substring(4, 10);

            } else {
                operator = null;
                flag = false;
            }
        }
        if (numberStr != null) {
            return new PhoneNumber(operator.getValue(), firstNumber, numberStr);
        }
        return null;
    }

    public static int printMenu(List<String> menu) {
        for (String s : menu) {
            System.out.println(s);
        }
        int choice;
        while (true) {
            System.out.println("Please choose from 0 to 7:");
            choice = scanner.nextInt();
            if (choice > 7 || choice < 0)
                continue;
            break;
        }
        return choice;

    }

}
