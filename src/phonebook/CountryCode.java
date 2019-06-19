package phonebook;

public enum CountryCode {
    //decided to do enums with better future functionality

    BULGARIA("359");

    String cCode;

    CountryCode(String code) {
        cCode = code;
    }

    public String getValue() {
        return cCode;
    }
}
