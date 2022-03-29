package elements;

import exceptions.WrongNumericArgumentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PharmacyTest {
    private Pharmacy p;
    private final String correctName = "producent";
    private final int correctId = 2;
    private final int correctDemand = 8;

    @Test(expected = IllegalArgumentException.class)
    public void name_is_blank() {
        String wrongName = " ";
        p = new Pharmacy(wrongName, correctId, correctDemand);
    }

    @Test(expected = WrongNumericArgumentException.class)
    public void id_smaller_than_zero() {
        int wrongId = -1;
        p = new Pharmacy(correctName, wrongId, correctDemand);
    }

    @Test(expected = WrongNumericArgumentException.class)
    public void production_smaller_than_zero() {
        int wrongDemand = -1;
        p = new Pharmacy(correctName, correctId, wrongDemand);

    }

    @Test
    public void correct_data() {
        p = new Pharmacy(correctName, correctId, correctDemand);
        assertEquals(correctName, p.getName());
        assertEquals(correctId, p.getId());
        assertEquals(correctDemand, p.getDailyDemand());
        assertEquals(correctDemand, p.getVaxNum());
    }


}