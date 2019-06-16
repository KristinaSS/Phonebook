package phonebook;

public class Phonebook {
    private String name;
    private PhoneNumber phoneNumber;

    private Phonebook() {
    }

    public Phonebook(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;

    }
}
