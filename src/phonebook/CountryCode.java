package phonebook;

public enum  CountryCode {
    BULGARIA("359");

    String cCode;
    CountryCode(String code) {
        cCode = code;
    }
    String getValue(){
        return cCode;
    }
}
