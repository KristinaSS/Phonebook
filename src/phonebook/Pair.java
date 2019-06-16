package phonebook;

public class Pair {
    private String name;
    private PhoneNumber phoneNumber;
    private int timesCalled;

    private Pair() {
    }

    public Pair(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTimesCalled() {
        return timesCalled;
    }

    public void setTimesCalled(int timesCalled) {
        this.timesCalled = timesCalled;
    }
}
