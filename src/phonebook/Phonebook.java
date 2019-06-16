package phonebook;

import com.sun.deploy.util.SyncAccess;

import javax.naming.Name;
import javax.rmi.CORBA.Util;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static phonebook.UtilsPhonebook.*;

public class Phonebook {

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

    public Phonebook(List<Pair> pairs) {
        this.pairs = pairs;
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
        Pair pair;
        for (String line : readTextFile()) {
            pair = checkPair(line);
            if(pair != null)
                phonebook.getPairs().add(pair);
        }
    }

    public static void addPair(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }
        phonebook.getPairs().add(new Pair(enterName(),enterPhoneNumber(),0));

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
                pair.toStringWithoutTimescalled();
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
        bubbleSort(phonebook.getPairs());

        boolean isEmpty = true;
        for(Pair pair: phonebook.getPairs()){
            pair.toStringWithoutTimescalled();
            isEmpty = false;
        }
        if(isEmpty){
            System.out.println("Phonebook is empty!");
        }
    }

}
