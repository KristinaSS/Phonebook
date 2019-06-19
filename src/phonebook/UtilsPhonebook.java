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

        // a function for reading a text file, using nio
        /* I have another function for writing in a text file, the reason i
        *  made it so it writes in another file is because in the original
        * text file i have a few rows that should be skipped because they
        * aren't in the correct format and to show that it works.
        *
        * The function for writing in files is a bonus*/

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

        //function for writing files

        /*This function writes in a file only those pairs that
        * are actually registered in the phonebook, i have a call function in Phonebook class
        * that "calls numbers", if the number dialed isnt in the phonebook you are asked if you
        * want to save the number as a person in your phonebook, the saved numbers are written
        * but the ones that aren't will not be written in this file*/
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

        /*This is a function that first takes a string and
        splits it, then calls another function to check if the phone number
         entered/read is in the correct format, if not returns null
          if it is a new pair is created and returned*/

        String[] split = line.split("[ ][|][ ]");

        PhoneNumber phoneNumber = null;

        phoneNumber = checkPhoneNumber(split[1]);

        if (phoneNumber == null)
            return null;

        Random random = new Random();
        return new Pair(split[0], phoneNumber, random.nextInt(100));
    }

    static String enterName() {

        /* A simple function that uses Scanner to enter a string
        * the string is then checked if its too long, and asks the user as
        * many times as possible to enter the string until it is the correct size
        * at the end returns the string*/

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

        /* A simple function that calls a function that checks
        * a string entered using Scanner and checks if the string entered is
        * in the correct format, if not  the user needs to enter a phoneNumber - string
        * again, in the end the function returns the phoneNumber */

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

        //kinda annoying this method is so long but im working with enums for better future functionality
        //another choice was with patterns but i chose enums

        /* Through ifs, we check if the given string is in the right format, if all
        * ends well at the end of the function an object of class PhoneNumber is created
        * if not returns null*/

        //for numbers like +359 88 8 888888
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
        //for numbers like 00 359 88 8 888888
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

        //for numbers like 0 88 8 888888
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

        /* A simple method that prints the list given
        * then you make a choice by entering a number
        * if the entered number is correct you continue if not you
        * are asked again until you enter the correct number
        * this method returns the choice*/

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
