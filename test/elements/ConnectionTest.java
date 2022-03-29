package elements;

import exceptions.WrongNumericArgumentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {

    private Connection connection;
    int phId = 1;
    int prId = 9;
    private final Producer pr = new Producer(prId);
    private final Pharmacy ph = new Pharmacy(phId);
    private final int correctBoughtVaccines = 25;
    private final double correctPrice = 23.23;


    @Test(expected = WrongNumericArgumentException.class)
    public void pass_price_smaller_than_zero() {
        //give
        double incorrectPrice = -3;
        //when
        connection = new Connection(pr, ph, correctBoughtVaccines, incorrectPrice);
        //then
        assert false;
    }

    @Test(expected = WrongNumericArgumentException.class)
    public void pass_vaccines_smaller_than_zero() {
        //give
        int incorrectBoughtVaccines = -3;
        //when
        connection = new Connection(pr, ph, incorrectBoughtVaccines, correctPrice);
        //then
        assert false;
    }

    @Test
    public void pass_correct_data() {
        connection = new Connection(pr, ph, correctBoughtVaccines, correctPrice);
        assertEquals(correctBoughtVaccines, connection.getVaccines());
        assertEquals(correctPrice, connection.getPrice(), 0);
        assertEquals(phId, connection.getPharmacy().getId());
        assertEquals(prId, connection.getProducer().getId());

    }

}