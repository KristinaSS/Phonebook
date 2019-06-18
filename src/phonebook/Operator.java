package phonebook;

public enum Operator {
    A1("88"),
    TELENOR("89"),
    VIVACOM("87");

    String opCode;
    Operator(String code) {
        opCode = code;
    }
    String getValue(){
        return opCode;
    }

}
