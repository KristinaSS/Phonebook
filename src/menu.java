import phonebook.Phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static phonebook.Phonebook.createPhonebook;

public class menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<String> menu = new ArrayList<>();
        menu.add("Menu");
        menu.add("1. Create Phonebook");
        menu.add("2. Add Number ");
        menu.add("3. Delete Number By Name ");
        menu.add("4. Access Number By Name ");
        menu.add("5. Print all pairs(name,numbers) sorted By Name ");
        menu.add("0. Exit");

        Phonebook phonebook = null;

        while (true) {
            switch (printMenu(menu)) {
                case 1:
                    phonebook = Phonebook.getInstance();
                    createPhonebook(phonebook);
                    continue;
                case 2:
                    continue;
                case 3:
                    continue;
                case 4:
                    continue;
                case 5:
                    continue;
                case 0:
                    System.out.println("Goodbye!");
                    break;
            }
            break;
        }
        System.exit(0);
    }

    private static int printMenu(List<String> menu){
        for(String s: menu){
            System.out.println(s);
        }
        int choice;
        while(true){
            System.out.println("Please choose from 0 to 5:");
            choice = scanner.nextInt();
            if(choice>5 || choice<0)
                continue;
            break;
        }
        return choice;

    }
}
