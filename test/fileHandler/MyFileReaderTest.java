package fileHandler;

import elements.Connection;
import elements.Pharmacy;
import elements.Producer;
import exceptions.DuplicateDataException;
import exceptions.NotEnoughDataException;
import exceptions.WrongNumericArgumentException;
import exceptions.WrongSupplyDemandRationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.zip.DataFormatException;

import static org.junit.Assert.*;

public class MyFileReaderTest {
    private MyFileReader f;
    private final String absolute="./Dystrybutor Szczepionek/test/fileHandler/test_files/";

    @Test(expected= DataFormatException.class)
    public void no_section_sign_at_the_beginning() throws IOException, DataFormatException {
        //given
        String path=absolute+"dane1.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= DataFormatException.class)
    public void first_line_of_file_is_empty() throws IOException, DataFormatException {
        //given
        String path=absolute+"dane2.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;

    }


    @Test(expected= DataFormatException.class)
    public void to_many_new_section_characters() throws IOException, DataFormatException {
        //given
        String path=absolute+"dane3.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;

    }

    @Test(expected= DataFormatException.class)
    public void to_little_new_section_characters() throws IOException, DataFormatException {
        //given
        String path=absolute+"dane4.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;

    }

    @Test(expected= DataFormatException.class)
    public void line_in_the_middle_is_empty() throws IOException, DataFormatException {
        //given
        String path=absolute+"dane5.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;

    }

    @Test(expected= DuplicateDataException.class)
    public void two_pharmacies_with_the_same_id() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane6.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= DuplicateDataException.class)
    public void two_producers_with_the_same_id() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane7.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= DuplicateDataException.class)
    public void two_identical_connections() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane8.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NotEnoughDataException.class)
    public void no_pharmacies_given_in_a_file() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane9.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NotEnoughDataException.class)
    public void no_producer_given_in_a_file() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane10.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NumberFormatException.class)
    public void pharmacy_id_is_not_integer() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane11.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NumberFormatException.class)
    public void pharmacy_demand_is_not_integer() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane12.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }


    @Test(expected= NumberFormatException.class)
    public void producer_id_is_not_integer() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane13.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NumberFormatException.class)
    public void producer_production_is_not_integer() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane14.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NumberFormatException.class)
    public void connection_pharmacy_id_is_not_integer() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane15.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NumberFormatException.class)
    public void connection_producer_id_is_not_integer() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane16.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NumberFormatException.class)
    public void connection_numer_of_vaccines_is_not_integer() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane17.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= NumberFormatException.class)
    public void connection_price_is_not_double() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane18.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= WrongNumericArgumentException.class)
    public void connection_price_is_less_than_zero() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane19.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= WrongSupplyDemandRationException.class)
    public void total_supply_is_less_than_demand() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane20.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test(expected= WrongSupplyDemandRationException.class)
    public void total_supply_is_less_than_demand_in_connections() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane21.txt";
        //when
        f = new MyFileReader(path);
        //then
        f.readData();
        assert false;
    }

    @Test
    public void creates_three_sets() throws IOException,DataFormatException {
        //given
        String path=absolute+"dane22.txt";
        int expectedLengthOfProducersAndPharmacies=3;
        int expectedLengthOfConnections=9;
        //when
        f = new MyFileReader(path);
        f.readData();
        Set<Producer> producers=f.getProducers();
        Set<Pharmacy> pharmacies=f.getPharmacies();
        Set<Connection> connections=f.getConnections();
        //then
        assertEquals(expectedLengthOfProducersAndPharmacies, producers.size());
        assertEquals(expectedLengthOfProducersAndPharmacies,pharmacies.size());
        assertEquals(expectedLengthOfConnections,expectedLengthOfConnections);
    }










}