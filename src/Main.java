import exceptions.*;
import fileHandler.MyFileReader;
import fileHandler.ResultGenerator;
import solvers.Solver;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class Main {
    public static void main(String[] args) throws IOException, DataFormatException {

        try {
            if (args.length != 1)
                throw new IllegalArgumentException("Podano wiecej niz jeden argument wywołania\nProgram oczekuje tylko 1 argumentu, którym ma być ściezka do pliku z danymi");
            String adress = args[0];
            MyFileReader f = new MyFileReader(adress);
            f.readData();
            Solver s = new Solver(f.getPharmacies(), f.getProducers(), f.getConnections());
            s.minimize();
            ResultGenerator resultGenerator = new ResultGenerator();
            resultGenerator.writeData(s.getDeals());


        } catch (IllegalArgumentException | DataFormatException | IOException | DuplicateDataException | NotEnoughDataException | WrongConnectionException | WrongNumberOfConnectionsException | WrongNumericArgumentException | WrongSupplyDemandRationException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());

        }
    }
}
