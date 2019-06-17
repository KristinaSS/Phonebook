package phonebook;

public class Pair {
    private String name;
    private PhoneNumber phoneNumber;
    private int timesCalled;

    private Pair() {
    }

    Pair(String name, PhoneNumber phoneNumber, int timesCalled) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.timesCalled = timesCalled;
    }

    String getName() {
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

    @Override
    public String toString() {
        return "Pair{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", timesCalled=" + timesCalled +
                '}';
    }
    public String toStringWithoutTimescalled() {
        return "Pair{" +
                "name='" + name + "   " + phoneNumber +'}';
    }
}
