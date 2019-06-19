package phonebook;

public enum Operator {
    //decided to do enums for better future functionality

    A1("88"),
    TELENOR("89"),
    VIVACOM("87");

    String opCode;

    Operator(String code) {
        opCode = code;
    }

    public String getValue() {
        return opCode;
    }

}
