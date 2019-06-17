package phonebook;

public class PhoneNumber {
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

    public String getCountryCallCode() {
        return countryCallCode;
    }

    public void setCountryCallCode(String countryCallCode) {
        this.countryCallCode = countryCallCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public char getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(char firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return prefix + countryCallCode +  operator + firstNumber + number;
    }
}
