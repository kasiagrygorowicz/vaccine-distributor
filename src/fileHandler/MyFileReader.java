package fileHandler;

import elements.Connection;
import elements.Pharmacy;
import elements.Producer;
import exceptions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.DataFormatException;


public class MyFileReader {
    private final BufferedReader br;
    private final Set<Pharmacy> pharmacies;
    private final Set<Producer> producers;
    private final Set<Connection> connections;
    private int lineNumber = 0;
    private int hashTag = 0;

    public MyFileReader(String fileAdress) throws FileNotFoundException {
        this.br = new BufferedReader(new FileReader(fileAdress));
        this.pharmacies = new HashSet<>();
        this.producers = new HashSet<>();
        this.connections = new HashSet<>();
    }

    public void readData() throws IOException, DataFormatException {

        String line;
        String[] data;


        line = br.readLine();
        lineNumber++;

        if (line.isEmpty()) {
            throw new DataFormatException("Błąd w linijce: " + lineNumber + " Plik nie może zawierać pustych linii");
        }
        if (line.replaceAll("\\s+", "").charAt(0) != '#') {
            throw new DataFormatException("W pierwszej linijce pliku musi znajdować się '#', oznacza on początke bloku zawierającego dane aptek");
        }
        hashTag++;

        while (hashTag == 1 && (line = br.readLine()) != null) {
            lineNumber++;
            if (line.isEmpty()) {
                throw new DataFormatException("Błąd w linijce: " + lineNumber + " Plik nie może zawierać pustych linii");
            }
            data = line.split("\\|");
            if (!checkPharmacyOrProducer(data.length, line)) break;
            addProducer(data);
        }

        while (hashTag == 2 && (line = br.readLine()) != null) {
            lineNumber++;
            if (line.isEmpty()) {
                throw new DataFormatException("Błąd w linijce: " + lineNumber + " Plik nie może zawierać pustych linii");
            }
            data = line.split("\\|");
            if (!checkPharmacyOrProducer(data.length, line)) break;
            addPharmacy(data);
        }

        while ((hashTag == 3 && (line = br.readLine()) != null)) {
            lineNumber++;
            if (line.isEmpty()) {
                throw new DataFormatException("Błąd w linijce: " + lineNumber + " Plik nie może zawierać pustych linii");
            }
            data = line.split("\\|");
            if (!checkConnection(data.length, line)) break;
            addConnection(data);
        }

        if (hashTag != 3) {
            throw new DataFormatException("Niewłaściwa liczba sekcji, są " + hashTag + " a powinno być 3");
        }
        if (pharmacies.size() == 0)
            throw new NotEnoughDataException("Błąd danych\nW podanym pliku nie ma danych aptek");
        if (producers.size() == 0)
            throw new NotEnoughDataException("Błąd danych\nW podanym pliku nie ma danych producentow");

        isTotalSupplyAndDemandCorrect();
        checkCorrectnessOfConnections();

    }

    private void addConnection(String[] data) {
        String message1 = "Błąd w linijce: " + lineNumber + "w argumencie pierwszym\nId producenta musi być wartością całkowitą";
        String message2 = "Błąd w linijce: " + lineNumber + " w argumencie drugim\nId apteki musi być wartością całkowitą";
        String message3 = "Błąd w linijce: " + lineNumber + " w argumencie trzecim\nMaksymalna liczba szczepionek jaką producent zoobowiązał sie dostarczyc po określonej cenie musi byc wartością całkowitą";
        String message4 = "Błąd w linijce: " + lineNumber + " w argumencie czwartym\nCena za szczepionke musi być wartością typu double";
        double[] myValues = parseValues(data[0].replaceAll("\\s+", ""), data[1].replaceAll("\\s+", ""), data[2].replaceAll("\\s+", ""), data[3].replaceAll("\\s+", ""), message1, message2, message3, message4);
        Connection connection = new Connection(new Producer((int) myValues[0]), new Pharmacy((int) myValues[1]), (int) myValues[2], myValues[3]);

        if (!connections.add(connection))
            throw new DuplicateDataException("Błąd w linijce: " + lineNumber + "\nTakie połaczenie juz istnieje, połączenia muszą byc unikatowe");

    }

    private void addProducer(String[] data) {
        String message1 = "Błąd w linijce: " + lineNumber + "w argumencie pierwszym\nId producenta musi być wartością całkowitą";
        String message2 = "Błąd w linijce: " + lineNumber + " w argumencie trzecim\nDzienna produkcja producenta musi być wartością całkowitą";
        double[] myValues = parseValues(data[0].replaceAll("\\s+", ""), data[2].replaceAll("\\s+", ""), message1, message2);
        Producer producer = new Producer(data[1], (int) myValues[0], (int) myValues[1]);
        if (!producers.add(producer))
            throw new DuplicateDataException("Błąd w linijce: " + lineNumber + "\nProducent o podanym id juz istnieje, producenci nie moga sie powtarzać");
    }

    private void addPharmacy(String[] data) {
        String message1 = "Błąd w linijce: " + lineNumber + "w argumencie pierwszym\nId apteki musi być wartością całkowitą";
        String message2 = "Błąd w linijce: " + lineNumber + " w argumencie trzecim\nZapotrzebowanie apteki musi być wartością całkowitą";
        double[] myValues = parseValues(data[0].replaceAll("\\s+", ""), data[2].replaceAll("\\s+", ""), message1, message2);
        Pharmacy pharmacy = new Pharmacy(data[1], (int) myValues[0], (int) myValues[1]);
        if (!pharmacies.add(pharmacy))
            throw new DuplicateDataException("Błąd w linijce: " + lineNumber + "\nApteka o podanym id juz istnieje, apteki nie moga się powtarzać");
    }


    private boolean checkPharmacyOrProducer(int length, String line) throws DataFormatException {
        if (line.replaceAll("\\s+", "").charAt(0) == '#') {
            hashTag++;
            return false;
        }
        if (length != 3) {
            throw new DataFormatException("Błąd w linijce: " + lineNumber + "\nNiewłaściwa liczba danych. Wymagane 3 argumenty oddzielone 2 znakami '|'\n Znak '|' nie może być częścią nazwy.");
        }
        return true;
    }

    public Set<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public Set<Producer> getProducers() {
        return producers;
    }

    public Set<Connection> getConnections() {
        return connections;
    }

    private boolean checkConnection(int length, String line) throws DataFormatException {
        if (line.replaceAll("\\s+", "").charAt(0) == '#') {
            throw new DataFormatException("Podano znak # w linijce " + lineNumber + " sygnalizujący kolejną sekcje z danymi\nProgram posiada już wszytskie niezbędne sekcje danych potrzebne do działania\nNie może istnieć kolejna sekcja");
        }
        if (length != 4) {
            throw new DataFormatException("Błąd w linijce: " + lineNumber + "\nNiewłaściwa liczba danych. Wymagane 4 argumenty oddzielone 3 znakami '|'\n Znak '|' nie może być częścią nazwy.");
        }
        return true;
    }


    private double[] parseValues(String id, String vaccines, String message1, String message2) {
        double[] myValues = new double[2];
        try {
            myValues[0] = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(message1);
        }
        try {
            myValues[1] = Integer.parseInt(vaccines);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(message2);
        }
        return myValues;
    }

    private double[] parseValues(String idProducer, String idPharmacy, String maxSupply, String price, String message1, String message2, String message3, String message4) {
        double[] myValues = new double[4];
        double[] tmp = parseValues(idProducer, idPharmacy, message1, message2);
        myValues[0] = tmp[0];
        myValues[1] = tmp[1];
        try {
            myValues[2] = Integer.parseInt(maxSupply);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(message3);
        }
        try {
            myValues[3] = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(message4);
        }
        return myValues;
    }


    private void isTotalSupplyAndDemandCorrect() {
        int demand = 0;
        int supply = 0;
        for (Pharmacy p : pharmacies) {

            demand += p.getDailyDemand();
        }
        for (Producer pr : producers) {
            supply += pr.getDailyProduction();
        }

        if (demand > supply) {
            throw new WrongSupplyDemandRationException("Błąd w danych pliku\nZpotrzebowanie aptek nie może być wieksze od produkcji producentów");
        }
    }


    private void checkCorrectnessOfConnections() {
        int numberOfProducers = producers.size();
        int helper;
        int totalSupply;
        for (Pharmacy p : pharmacies) {
            helper = 0;
            totalSupply = 0;

            for (Connection c : connections) {
                if (p.equals(c.getPharmacy())) {
                    if (!producers.contains(c.getProducer()))
                        throw new WrongConnectionException("Apteka o id " + p.getId() + " zawiera połączenie z producentem który nie istnieje o id " + c.getProducer().getId());
                    totalSupply += c.getVaccines();
                    helper++;
                }
            }
            if (helper != numberOfProducers) {
                if (helper > numberOfProducers) {
                    throw new WrongNumberOfConnectionsException("Błąd w liczbie połączeń\nZa duzo połączeń\nPowinno istnieć jedno połaczenie na każdą parę apteki i producenta\nDla apteki o id " + p.getId() + " istnieją " + helper + " połaczenia, a powinno istnieć " + numberOfProducers);
                } else {
                    throw new WrongNumberOfConnectionsException("Błąd w liczbie połączeń\nZa mało połączeń\nPowinno istnieć jedno połaczenie na każdą parę apteki i producenta\nDla apteki o id " + p.getId() + " istnieją " + helper + " połaczenia, a powinno istnieć " + numberOfProducers);

                }
            }
            if (totalSupply < p.getDailyDemand())
                throw new WrongSupplyDemandRationException("Dla apteki o id " + p.getId() + " która potrzebuje " + p.getDailyDemand() + " dostarczane jest tylko " + totalSupply + " szczepionek\nPotrzeba dostarczyć wiecej szczepionek!");
        }

        int correctNumberOfConnections = producers.size() * pharmacies.size();
        int actualNumberOfConnections = connections.size();
        if (actualNumberOfConnections > correctNumberOfConnections) {
            throw new WrongConnectionException("Istnieją nadmiarowe połaczenia między aptekami i producentami, którzy nie istnieją\nSą " + actualNumberOfConnections + " połączenia, a powinno ich być " + correctNumberOfConnections);
        }
    }

}
