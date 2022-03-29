package elements;

import exceptions.WrongNumericArgumentException;

public class Connection implements Comparable<Connection> {
    private final Pharmacy pharmacy;
    private final Producer producer;
    private final double price;
    private final int vaccines;

    public Connection(Producer producer, Pharmacy pharmacy, int vaccines, double price) {
        if (price < 0) {
            throw new WrongNumericArgumentException("Błąd ceny szczepionki, cena nie może byc ujemna");
        }
        if(vaccines<0){
            throw new WrongNumericArgumentException("Błąd w ilości kupionych szczepionek, ilość nie może byc ujemna");
        }
        this.pharmacy = pharmacy;
        this.producer = producer;
        this.price = price;
        this.vaccines = vaccines;
    }

    public double getPrice() {
        return price;
    }

    public int getVaccines() {
        return vaccines;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public Producer getProducer() {
        return producer;
    }

    @Override
    public int hashCode() {
        return (this.pharmacy.getId() + this.producer.getId()) * 111;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Connection)) {
            return false;
        }
        Connection connection = (Connection) o;
        return connection.getPharmacy().getId() == this.getPharmacy().getId() && connection.getProducer().getId() == this.producer.getId();
    }

    @Override
    public int compareTo(Connection o) {
        if (this.pharmacy.getId() == o.pharmacy.getId()) {
            return this.producer.getId() - o.producer.getId();
        }
        return this.pharmacy.getId() - o.pharmacy.getId();
    }
}
