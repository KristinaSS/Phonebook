package phonebook;

import com.sun.deploy.util.SyncAccess;

import javax.naming.Name;
import javax.rmi.CORBA.Util;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static phonebook.UtilsPhonebook.*;

public class Phonebook {

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

    public static void createPhonebook(Phonebook phonebook) {
        if(phonebook.getPairs()!= null){
            System.out.println("Phonebook already exists");
            return;
        }
        Pair pair;
        List<Pair> phonelogs = new ArrayList<>();
        for (String line : readTextFile()) {
            pair = checkPair(line);
            if(pair != null){
                phonelogs.add(pair);
            }
        }
        phonebook.setPairs(phonelogs);
    }

    public static void addPair(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        PhoneNumber phoneNumber;
        boolean flag = false;
        while(true){
            phoneNumber = enterPhoneNumber();
            for(Pair num: phonebook.getPairs()){
                if(num.getPhoneNumber().toString().compareTo(phoneNumber.toString())==0){
                    flag = true;
                }
            }
            if(!flag)
                break;
            System.out.println("PhoneNumber already exits in PhoneBook");
        }
        phonebook.getPairs().add(new Pair(enterName(),phoneNumber,0));

        System.out.println("Number Successfully added");

    }

    public static void deleteByName(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        String name = enterName();
        Pair pair;
        Iterator iterator = phonebook.getPairs().iterator();
        while(iterator.hasNext()){
            pair = (Pair)iterator.next();
            if(pair.getName().equals(name)){
                iterator.remove();
                System.out.println("Successfully Deleted!");
                return;
            }
        }
        System.out.println("No pair with "+ name + " exists!");
    }

    public static void accessByName(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        String name = enterName();
        for(Pair pair: phonebook.getPairs()){
            if(pair.getName().equals(name)) {
                System.out.println(pair.toStringWithoutTimescalled());
                return;
            }
        }
        System.out.println("No pair with "+ name + " exists!");
    }

    public static void printSortedPhonebook(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        phonebook.getPairs().sort((o1, o2) -> String.valueOf(o1.getName()).compareTo(o2.getName()));

        boolean isEmpty = true;
        for(Pair pair: phonebook.getPairs()){
            String s1 =String.format("%-30s","Name: " + pair.getName());
            String s2 =String.format("%s",pair.getPhoneNumber().toString());
            System.out.println(s1+s2);
            isEmpty = false;
        }
        if(isEmpty){
            System.out.println("Phonebook is empty!");
        }
    }

    public static void callNumber(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        PhoneNumber phoneNumber = enterPhoneNumber();

        for(Pair pair: phonebook.getPairs()){
            if(phoneNumber.toString().equals(pair.getPhoneNumber().toString())){
                pair.setTimesCalled(pair.getTimesCalled()+1);
                System.out.println("Successfully called number, ended outgoing call!");
                return;
            }
        }
        String choice;
        while(true){
            System.out.println("No such Number exits in phone book, would you like to add it to your Phonebook? Y/N");
            choice = scanner.next();
            choice = choice.toUpperCase();
            if(choice.equals("Y") || choice.equals("N")){
                if(choice.equals("Y")){
                    phonebook.getPairs().add(new Pair(enterName(),phoneNumber,1));
                    System.out.println("New number Added");
                    return;
                }else {
                    phonebook.getPairs().add(new Pair(phoneNumber.toString(), phoneNumber, 1));
                    return;
                }

            }
        }
    }

    public static void printFavNumbers(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        phonebook.getPairs().sort((o1, o2) -> Integer.compare(o2.getTimesCalled(), o1.getTimesCalled()));

        int counter = 0;
        System.out.println("Name -----------------------------Number-----------TimesCalled");
        System.out.println("==============================================================");
        for(Pair pair: phonebook.getPairs()){
            counter++;
            String s1 =String.format("%-30s","Name: " + pair.getName());
            String s2 =String.format("%s",pair.getPhoneNumber().toString() + "    " + pair.getTimesCalled());
            System.out.println(counter+". "+s1+s2);
            if(counter == 5)
                break;

        }
        if(counter == 0){
            System.out.println("Phonebook is empty!");
        }
    }

}
