package phonebook;

import com.sun.deploy.util.SyncAccess;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Phonebook {
    final static Scanner scanner = new Scanner(System.in);

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

    public static void createPhonebook(Phonebook phonebook) {
        if (!phonebook.getPairs().isEmpty()) {
            System.out.println("A phonebook already exists");
            return;
        }
        Pair pair;
        for (String line : readTextFile()) {
            pair = checkPair(line);
            if(pair != null)
                phonebook.getPairs().add(pair);
        }
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

        String[] split = line.split(" ");
        PhoneNumber phoneNumber = null;


        if(phoneNumber == null)
            return null;

        return new Pair(split[0],phoneNumber);
    }

    public static void addPair(Phonebook phonebook){
        if(phonebook == null){
            System.out.println("You have not created a phonebook yet!");
            return;
        }

    }
    public static String enterName(){
        String name;
        while (true){
            System.out.println("Enter name:");
            name = scanner.nextLine();
            if(name.length()>30)
                continue;
            break;
        }
        return name;
    }

    public static PhoneNumber enterPhoneNumber(){
        String phoneNumberStr;
        PhoneNumber phoneNumber;

        while(true){
            System.out.println("Enter PhoneNumber");
            phoneNumberStr = scanner.nextLine();

        }
    }
    public static PhoneNumber checkPhoneNumber(String number){
        Operator operator = null;
        boolean flag = false;

        PhoneNumber phoneNumber = null;

        if (number.charAt(0) == '+'&& number.length()== 13) {
            if (number.substring(1, 4).equals(CountryCode.BULGARIA.toString())) {  //+359878123456
                for(Operator o: Operator.values()){
                    if(number.substring(4, 6).equals(o.toString())){
                        operator = o;
                        flag = true;
                        break;
                    }
                }
                if ((number.charAt(6) >= '2' || number.charAt(6) <= '9') && flag) {
                    phoneNumber.setPrefix("+");
                    phoneNumber.setCountryCallCode(CountryCode.BULGARIA);
                    phoneNumber.setOperator(operator);
                    phoneNumber.setFirstNumber(number.charAt(6));
                    phoneNumber.setNumber(number.substring(7,13));
                }
            }
        }
        else {
            operator = null;
            flag = false;
            if (number.substring(0, 2).equals("00")&& number.length()== 14) {//00 359 87 8 123456,
                if (number.substring(2, 5).equals(CountryCode.BULGARIA.toString())) {
                    for(Operator o: Operator.values()){
                        if(number.substring(5, 7).equals(o.toString())){
                            operator = o;
                            flag = true;
                            break;
                        }
                    }
                    if ((number.charAt(7) >= '2' || number.charAt(7) <= '9')&& flag) {
                        phoneNumber.setPrefix("+");
                        phoneNumber.setCountryCallCode(CountryCode.BULGARIA);
                        phoneNumber.setOperator(operator);
                        phoneNumber.setFirstNumber(number.charAt(7));
                        phoneNumber.setNumber(number.substring(8,14));
                    }
                }
            } else {
                operator = null;
                flag = false;
                if (number.charAt(0) == '0' && number.length()== 10) { // 0 87 8 123456
                    for(Operator o: Operator.values()){
                        if(number.substring(1, 3).equals(o.toString())){
                            operator = o;
                            flag = true;
                            break;
                        }
                    }
                    if ((number.charAt(3) >= '2' || number.charAt(3) <= '9')&& flag) {
                        phoneNumber.setPrefix("+");
                        phoneNumber.setCountryCallCode(CountryCode.BULGARIA);
                        phoneNumber.setOperator(operator);
                        phoneNumber.setFirstNumber(number.charAt(2));
                        phoneNumber.setNumber(number.substring(4,10));
                    }
                }
            }

        }
        return phoneNumber;
    }

}
