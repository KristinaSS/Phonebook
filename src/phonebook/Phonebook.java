package phonebook;

import java.util.*;

import static phonebook.UtilsPhonebook.*;

public class Phonebook {

 /* One Phonebook has one list of pairs(name, number)*/

    private final static Scanner scanner = new Scanner(System.in);

    private List<Pair> pairs;

    //Singleton

    private static Phonebook phonebookInstance = null;

    public static Phonebook getInstance() {
        if (phonebookInstance == null)
            phonebookInstance = new Phonebook();
        return phonebookInstance;
    }

    //Constructors

    private Phonebook() {
    }

    //Getters and setters

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    //Functions

    public static boolean createPhonebook(Phonebook phonebook) {

        /*One phonebook can exist at a time and also this is done so when
        * you create a phonebook so you dont overwrite the phonebook you have if you
        * made any changes vise versa if you dont have a phonebook created you cant do
        * anything, add, delete etc.*/

        if (phonebook.getPairs() != null) {
            System.out.println("Phonebook already exists");
            return false;
        }
        Pair pair;
        List<Pair> phonelogs = new ArrayList<>();
        for (String line : readTextFile()) {
            pair = checkPair(line);
            if (pair != null) {
                phonelogs.add(pair);
            }
        }
        phonebook.setPairs(phonelogs);
        return true;
    }

    /*For the other functions if no phonebook is created you
    * will not be able to procceed and you will be thrown back to
    * the main menu as many times until you either exit the application or
    * you create a phonebook*/

    public static void addPair(Phonebook phonebook) {
        /* A function that adds pairs to phonebook , to do this
        * it calls functions to enter the new name and number
        * if a pair with the same number already exists then you would be asked to
        * enter another number*/

        if (phonebook == null) {
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        PhoneNumber phoneNumber;
        boolean flag = false;
        while (true) {
            phoneNumber = enterPhoneNumber();
            for (Pair num : phonebook.getPairs()) {
                if (num.getPhoneNumber().toString().compareTo(phoneNumber.toString()) == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                break;
            System.out.println("PhoneNumber already exits in PhoneBook");
            flag = false;
        }
        phonebook.getPairs().add(new Pair(enterName(), phoneNumber, 0));

        System.out.println("Number Successfully added");
    }

    public static void deleteByName(Phonebook phonebook) {

        /*The user would be asked to enter the name of the pair they would like to
        * delete through another method, then the method would check if a pair with this name exists
        * this is not case sensitive. If no such pair with name exists then a message is set that no
        * such pair exists */

        if (phonebook == null) {
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        String name = enterName().toUpperCase();
        String temp;
        Pair pair;
        Iterator iterator = phonebook.getPairs().iterator();
        while (iterator.hasNext()) {
            pair = (Pair) iterator.next();
            temp = pair.getName().toUpperCase();
            if (temp.equals(name)) {
                iterator.remove();
                System.out.println("Successfully Deleted!");
                return;
            }
        }
        System.out.println("No pair with " + name + " exists!");
    }

    public static void accessByName(Phonebook phonebook) {
        /*The user would be asked to enter the name of the pair they would like to
         * access through another method, then the method would check if a pair with this name exists
         * this is not case sensitive. If no such pair with name exists then a message is set that no
         * such pair exists */

        if (phonebook == null) {
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        String name = enterName().toUpperCase();
        String temp;
        for (Pair pair : phonebook.getPairs()) {
            temp =pair.getName().toUpperCase();
            if (temp.equals(name)) {
                System.out.println(pair.toStringWithoutTimescalled());
                return;
            }
        }
        System.out.println("No pair with " + name + " exists!");
    }

    public static boolean printSortedPhonebook(Phonebook phonebook) {
        /*Through an interface named Comparor i compare the list with its
        comparor method in such a way that the list is sorted in an alphabetical order
        by the name of the pairs. Then i print the list. If the list is empty a message is output
        it is empty*/

        if (phonebook == null) {
            System.out.println("You have not created a phonebook yet!");
            return false;
        }
        phonebook.getPairs().sort((o1, o2) -> String.valueOf(o1.getName()).compareTo(o2.getName()));

        boolean isEmpty = true;
        for (Pair pair : phonebook.getPairs()) {
            String s1 = String.format("%-30s", "Name: " + pair.getName());
            String s2 = String.format("%s", pair.getPhoneNumber().toString());
            System.out.println(s1 + s2);
            isEmpty = false;
        }
        if (isEmpty) {
            System.out.println("Phonebook is empty!");
        }
        return true;
    }

    public static boolean callNumber(Phonebook phonebook) {

        /*Through methods you enter the phonenumber and check if that phonenumber exists in
        * our phonebook. If is is then the counter of the amount of times that the person have
        * made outgoing calls is increased by one. If it doesnt exist then the user is asked
        * if he/she would like to save this number to the phonebook if they answer yes then the
        * number will be saved. If no a new pair will be created with the number as a name, but while
        * writing in a file apon exiting they will not be written in the file*/

        if (phonebook == null) {
            System.out.println("You have not created a phonebook yet!");
            return false;
        }
        PhoneNumber phoneNumber = enterPhoneNumber();

        System.out.println("Dialing phoneNumber:");

        for (Pair pair : phonebook.getPairs()) {
            if (phoneNumber.toString().equals(pair.getPhoneNumber().toString())) {
                pair.setTimesCalled(pair.getTimesCalled() + 1);
                System.out.println("Successfully called number, ended outgoing call!");
                return true;
            }
        }
        String choice;
        while (true) {
            System.out.println("No such Number exits in phone book, would you like to add it to your Phonebook? Y/N");
            choice = scanner.next();
            choice = choice.toUpperCase();
            if (choice.equals("Y") || choice.equals("N")) {
                if (choice.equals("Y")) {
                    phonebook.getPairs().add(new Pair(enterName(), phoneNumber, 1));
                    System.out.println("New number Added");
                    return true;
                } else {
                    phonebook.getPairs().add(new Pair(phoneNumber.toString(), phoneNumber, 1));
                    return true;
                }

            }
        }
    }

    public static void printFavNumbers(Phonebook phonebook) {

        /*In this method again we will sort the phonebook using Comparor but this time in an desc
        * order by the timesCalled of each number. for it to look nicer, the name and the phonenumber are saved
        * as two dif strings and formated. the formated strings are printed. This is done only to the first five
        * pairs*/

        if (phonebook == null) {
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        phonebook.getPairs().sort((o1, o2) -> Integer.compare(o2.getTimesCalled(), o1.getTimesCalled()));

        int counter = 0;
        System.out.println("Name -----------------------------Number-----------TimesCalled");
        System.out.println("==============================================================");
        for (Pair pair : phonebook.getPairs()) {
            counter++;
            String s1 = String.format("%-30s", "Name: " + pair.getName());
            String s2 = String.format("%s", pair.getPhoneNumber().toString() + "    " + pair.getTimesCalled());
            System.out.println(counter + ". " + s1 + s2);
            if (counter == 5)
                break;

        }
        if (counter == 0) {
            System.out.println("Phonebook is empty!");
        }
    }


}
