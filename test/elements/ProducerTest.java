package elements;

import exceptions.WrongNumericArgumentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProducerTest {
    private final String correctName = "producent";
    private final int correctId = 2;
    private final int correctProduction = 8;

    @Test(expected = IllegalArgumentException.class)
    public void name_is_blank() {
        String wrongName = " ";
        Producer p = new Producer(wrongName, correctId, correctProduction);
    }

    @Test(expected = WrongNumericArgumentException.class)
    public void id_smaller_than_zero() {
        int wrongId = -1;
        Producer p = new Producer(correctName, wrongId, correctProduction);
    }

    @Test(expected = WrongNumericArgumentException.class)
    public void production_smaller_than_zero() {
        int wrongProduction = -1;
        Producer p = new Producer(correctName, correctId, wrongProduction);

    }

    @Test
    public void correct_data() {
        Producer p = new Producer(correctName, correctId, correctProduction);
        assertEquals(correctName, p.getName());
        assertEquals(correctId, p.getId());
        assertEquals(correctProduction, p.getDailyProduction());
        assertEquals(correctProduction, p.getVacAvailable());
    }
}