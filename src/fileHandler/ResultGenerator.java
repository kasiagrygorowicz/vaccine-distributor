package fileHandler;

import elements.Deal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultGenerator {
    private final FileWriter fr;

    public ResultGenerator() throws IOException {
        fr = new FileWriter("wyniki_minimalizacji.txt");
    }

    public void writeData(List<Deal> deals) throws IOException {
        double result=0;
        for (Deal d : deals) {
            result+=d.getTotalCost();
            fr.write(d.toString() + "\n");
            System.out.println(d.toString());
        }
        System.out.printf("Całkowity koszt %.2f zł",result);
        fr.flush();
        fr.close();
    }
}
