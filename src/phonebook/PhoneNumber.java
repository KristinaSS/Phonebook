package phonebook;

public class PhoneNumber {

    /*I made a PhoneNumber class for better functionality */

    private String prefix = "+";
    private String countryCallCode;
    private String operator;
    private char firstNumber;
    private String number;

    private PhoneNumber() {
    }

    public PhoneNumber(String operator, char firstNumber, String number) {
        this.prefix = "+";
        this.countryCallCode = CountryCode.BULGARIA.getValue();
        this.operator = operator;
        this.firstNumber = firstNumber;
        this.number = number;
    }


    @Override
    public String toString() {
        return prefix + countryCallCode + operator + firstNumber + number;
    }
}
