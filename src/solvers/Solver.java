package solvers;

import elements.Connection;
import elements.Deal;
import elements.Pharmacy;
import elements.Producer;

import java.util.*;

public class Solver {
    private final List<Pharmacy> pharmacies;
    private final List<Producer> producers;
    private final List<Connection> connections;
    private final List<Deal> deals;

    public Solver(Set<Pharmacy> pharmacies, Set<Producer> producers, Set<Connection> connections) {
        this.pharmacies = new ArrayList<>(pharmacies);
        this.producers = new ArrayList<>(producers);
        this.connections = new ArrayList<>(connections);
        this.deals = new ArrayList<>();
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void minimize() {

        Collections.sort(pharmacies);
        Collections.sort(producers);
        Collections.sort(connections);

        while (true) {

            ArrayList<Double> row = new ArrayList<>();
            ArrayList<Double> column = new ArrayList<>();

            if (pharmacies.size() == 1) {
                buy_for_pharmacy(0, producers.size(), pharmacies.size());
                break;
            } else {

                int i = 0;
                for (Pharmacy p : pharmacies) {

                    List<Connection> con = new ArrayList<>();
                    for (int j = 0; j < producers.size(); j++) {
                        con.add(connections.get(i * producers.size() + j));
                    }
                    if (con.size() == 0) {

                        throw new RuntimeException("Błąd programu, połączenia nie istnieją\nNaprawa przewidziana w kolejnej aktualizacji xd");
                    } else if (con.size() == 1) {
                        row.add(-8.0);
                    } else {
                        Collections.sort(con, new CustomComparator());
                        row.add((con.get(1).getPrice()) - (con.get(0).getPrice()));
                    }
                    i++;
                }

                int k = 0;
                if (producers.size() == 1) {
                    double maxRow = Collections.max(row);
                    int index;
                    index = row.indexOf(maxRow);
                    buy_for_pharmacy(index, producers.size(), pharmacies.size());

                } else {

                    for (Producer p : producers) {
                        List<Connection> con = new ArrayList<>();
                        int l = 0;

                        while (l < pharmacies.size()) {
                            con.add(connections.get(l * producers.size() + k));
                            l++;
                        }
                        if (con.size() == 1) {
                            System.out.println("ooo");
                            column.add(-9.0);
                        } else {
                            Collections.sort(con, new CustomComparator());
                            column.add((con.get(1).getPrice()) - (con.get(0).getPrice()));
                        }
                        k++;

                    }

                    int index, id;
                    double maxColumns, maxRow;
                    maxColumns = Collections.max(column);
                    maxRow = Collections.max(row);

                    if (maxColumns > maxRow) {

                        index = column.indexOf(maxColumns);
                        int producerId = producers.get(index).getId();
                        List<Double> prod = new ArrayList<>();
                        int l = 0;
                        while (l < pharmacies.size()) {
                            prod.add(connections.get(l * producers.size() + index).getPrice());
                            l++;
                        }
                        double minValue = Collections.min(prod);
                        int pharmacyIndex = prod.indexOf(minValue);
                        buy_for_pharmacy(pharmacyIndex, producers.size(), pharmacies.size());

                    } else {
                        index = row.indexOf(maxRow);
                        id = pharmacies.get(index).getId();
                        buy_for_pharmacy(index, producers.size(), pharmacies.size());
                    }

                }
            }
        }
    }

    private void buy_for_pharmacy(int index, int pharmacyJUmp, int producerJump) {
        int procuderJump = producerJump;
        ArrayList<Connection> connnectionsForPharmacy = new ArrayList<>();
        for (int i = 0; i < pharmacyJUmp; i++) {
            connnectionsForPharmacy.add(connections.get(index * pharmacyJUmp + i));
        }
        Collections.sort(connnectionsForPharmacy, new CustomComparator());
        int h = 0;
        int needsToBuy = pharmacies.get(index).getVaxNum();
        String pharmacyName = pharmacies.get(index).getName();

        while (h < pharmacyJUmp) {

            Connection c = connnectionsForPharmacy.get(h);
            int canBuy = c.getVaccines();
            int isAvailable = 0;
            String producerName;
            int procuderIndex = 0;
            for (Producer p : producers) {
                if (p.equals(c.getProducer())) {
                    isAvailable = p.getVacAvailable();
                    procuderIndex = producers.indexOf(p);
                    break;
                }
            }
            int buys = 0;
            if (isAvailable <= canBuy) {
                canBuy = isAvailable;
            }
            if (needsToBuy <= canBuy) {
                buys = needsToBuy;
                isAvailable -= buys;
                needsToBuy -= buys;
                deals.add(new Deal(c.getPrice(), buys, pharmacyName, producers.get(procuderIndex).getName()));
                if (isAvailable == 0) {
                    removeAllConnectionsWithProducer(procuderIndex, pharmacies.size());
                    producers.remove(procuderIndex);
                }
                removeAllConnectionsWithPharmacies(index, producers.size());
                pharmacies.remove(index);

            } else {
                buys = canBuy;
                needsToBuy -= buys;
                producers.get(procuderIndex).setVacAvailable(isAvailable - buys);
                pharmacies.get(index).setVaxNum(needsToBuy);
                if (buys != 0) {
                    deals.add(new Deal(c.getPrice(), buys, pharmacyName, producers.get(procuderIndex).getName()));
                }

                if (isAvailable - buys == 0) {
                    removeAllConnectionsWithProducer(procuderIndex, pharmacies.size());
                    producers.remove(procuderIndex);
                }
            }
            if (needsToBuy == 0) {
                break;
            }
            h++;
        }

    }

    private void removeAllConnectionsWithPharmacies(int pharmacyIndex, int pharmacyJump) {
        for (int i = 0; i < pharmacyJump; i++) {
            connections.remove(pharmacyIndex * pharmacyJump);
        }
    }

    private void removeAllConnectionsWithProducer(int producerIndex, int producerJump) {
        for (int i = 0; i < producerJump; i++) {
            connections.remove(i * producers.size() - i);
        }
    }

    private class CustomComparator implements Comparator<Connection> {

        @Override
        public int compare(Connection o1, Connection o2) {
            double n = o1.getPrice() - o2.getPrice();
            if (n > 0) return 1;
            if (n < 0) return -1;
            else return 0;
        }
    }
}


