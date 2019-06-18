import org.junit.jupiter.api.Test;
import phonebook.Operator;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    @Test
    void getValue() {
        int numberOperators = 3;

        assertEquals(numberOperators, Operator.values().length);

        for(Operator o: Operator.values()){
            assertNotNull(o);
        }
    }
}