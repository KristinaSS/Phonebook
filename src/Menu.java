import phonebook.Phonebook;
import phonebook.UtilsPhonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static phonebook.Phonebook.*;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> menu = new ArrayList<>();
        menu.add("Menu");
        menu.add("1. Create Phonebook");
        menu.add("2. Add Number ");
        menu.add("3. Delete Number By Name ");
        menu.add("4. Access Number By Name ");
        menu.add("5. Print all pairs(name,numbers) sorted By Name ");
        menu.add("6. Call Number");
        menu.add("7. See Favourite Numbers(Most Called)");
        menu.add("0. Exit");

        Phonebook phonebook = null;

        while (true) {
            System.out.println("\n");
            switch (UtilsPhonebook.printMenu(menu)) {
                case 1:
                    phonebook = Phonebook.getInstance();
                    createPhonebook(phonebook);
                    continue;
                case 2:
                    addPair(phonebook);
                    continue;
                case 3:
                    deleteByName(phonebook);
                    continue;
                case 4:
                    accessByName(phonebook);
                    continue;
                case 5:
                    printSortedPhonebook(phonebook);
                    continue;
                case 6:
                    callNumber(phonebook);
                    continue;
                case 7:
                    printFavNumbers(phonebook);
                    continue;
                case 0:
                    System.out.println("Goodbye!");
                    if (phonebook != null)
                        UtilsPhonebook.writeTextFile(phonebook);
                    break;
            }
            break;
        }
        System.exit(0);
    }
}
