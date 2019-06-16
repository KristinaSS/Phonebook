package phonebook;

public class PhoneNumber {
    private String prefix;
    private short countryCallCode;
    private byte operator;
    private byte firstNumber;
    private int number;

    private PhoneNumber() {
    }
    public PhoneNumber(String prefix, short countryCallCode, byte operator, byte firstNumber, int number) {
        this.prefix = prefix;
        this.countryCallCode = countryCallCode;
        this.operator = operator;
        this.firstNumber = firstNumber;
        this.number = number;
    }

    public PhoneNumber(short countryCallCode, byte operator, byte firstNumber, int number) {
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

    public short getCountryCallCode() {
        return countryCallCode;
    }

    public void setCountryCallCode(short countryCallCode) {
        this.countryCallCode = countryCallCode;
    }

    public byte getOperator() {
        return operator;
    }

    public void setOperator(byte operator) {
        this.operator = operator;
    }

    public byte getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(byte firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
