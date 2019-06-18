import phonebook.CountryCode;

import static org.junit.jupiter.api.Assertions.*;

class CountryCodeTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        int numberCountries = 1;

        assertEquals(numberCountries,CountryCode.values().length);

        for(CountryCode c: CountryCode.values()){
            assertNotNull(c);
        }
    }
}