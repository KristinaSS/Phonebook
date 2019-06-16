package phonebook;

public class PhoneNumber {
    private String prefix;
    private CountryCode countryCallCode;
    private Operator operator;
    private char firstNumber;
    private String number;

    private PhoneNumber() {
    }
    public PhoneNumber(String prefix, CountryCode countryCallCode, Operator operator, char firstNumber, String number) {
        this.prefix = prefix;
        this.countryCallCode = countryCallCode;
        this.operator = operator;
        this.firstNumber = firstNumber;
        this.number = number;
    }

    public PhoneNumber(CountryCode countryCallCode, Operator operator, char firstNumber, String number) {
        this.countryCallCode = countryCallCode;
        this.operator = operator;
        this.firstNumber = firstNumber;
        this.number = number;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public CountryCode getCountryCallCode() {
        return countryCallCode;
    }

    public void setCountryCallCode(CountryCode countryCallCode) {
        this.countryCallCode = countryCallCode;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
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
}
