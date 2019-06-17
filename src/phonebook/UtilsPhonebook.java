package phonebook;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UtilsPhonebook {

    private static UtilsPhonebook utilsPhonebookInstance = null;

    public static UtilsPhonebook getInstance() {

        if (utilsPhonebookInstance == null)
            utilsPhonebookInstance = new UtilsPhonebook();
        return utilsPhonebookInstance;
    }

    private UtilsPhonebook() {
    }

    private final static Scanner scanner = new Scanner(System.in);

    //Functions

    static List<String> readTextFile() {
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

    public static void writeTextFile(Phonebook phonebook){
        try {
            FileWriter writer = new FileWriter("PhonebookOutput.txt");
            for (Pair pair : phonebook.getPairs()) {
                writer.write(pair.getName() + " | " + pair.getPhoneNumber().toString()+"\n");
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error writing File");
        }
        System.out.println("Successfully written!");
    }

    static Pair checkPair(String line) {

        String[] split = line.split("[ ][|][ ]");

        PhoneNumber phoneNumber = null;

        phoneNumber = checkPhoneNumber(split[1]);

        if(phoneNumber == null)
            return null;

        return new Pair(split[0] ,phoneNumber,0);
    }

    static String enterName(){
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

    static PhoneNumber enterPhoneNumber(){
        String phoneNumberStr;
        PhoneNumber phoneNumber;

        while(true){
            System.out.println("Enter PhoneNumber");
            phoneNumberStr = scanner.nextLine();
            phoneNumber =checkPhoneNumber(phoneNumberStr);
            if(phoneNumber == null)
                continue;
            break;
        }
        return phoneNumber;
    }

    private static PhoneNumber checkPhoneNumber(String number){
        Operator operator = null;
        boolean flag = false;
        String numberStr = null;
        char firstNumber = ' ';

        if (number.charAt(0) == '+'&& number.length()== 13) {
            if (number.substring(1, 4).equals(CountryCode.BULGARIA.getValue())) {  //+359878123456
                for(Operator o: Operator.values()){
                    if(number.substring(4, 6).equals(o.getValue())){
                        operator = o;
                        flag = true;
                        break;
                    }
                }
                if ((number.charAt(6) >= '2' && number.charAt(6) <= '9') && flag) {
                    firstNumber = number.charAt(6);
                    numberStr = number.substring(7,13);
                }else{
                    operator = null;
                    flag = false;
                }

            }
        }
            if (number.substring(0, 2).equals("00")&& number.length()== 14) {//00 359 87 8 123456,
                if (number.substring(2, 5).equals(CountryCode.BULGARIA.getValue())) {
                    for(Operator o: Operator.values()){
                        if(number.substring(5, 7).equals(o.getValue())){
                            operator = o;
                            flag = true;
                            break;
                        }
                    }
                    if ((number.charAt(7) >= '2' && number.charAt(7) <= '9')&& flag) {
                        firstNumber = number.charAt(7);
                        numberStr = number.substring(8,14);
                    }else{
                        operator = null;
                        flag = false;
                    }
                }
            }
            if (number.charAt(0) == '0' && number.length()== 10) { // 0 87 8 123456
                    for(Operator o: Operator.values()){
                        if(number.substring(1, 3).equals(o.getValue())){
                            operator = o;
                            flag = true;
                            break;
                        }
                    }
                    if ((number.charAt(3) >= '2' && number.charAt(3) <= '9')&& flag) {
                        firstNumber = number.charAt(3);
                        numberStr = number.substring(4,10);

                    }else{
                        operator = null;
                        flag = false;
                    }
                }
        if (numberStr!= null){
            PhoneNumber phoneNumber = new PhoneNumber(operator.getValue(),firstNumber,numberStr);

            return phoneNumber;
        }
        return null;
    }


}
