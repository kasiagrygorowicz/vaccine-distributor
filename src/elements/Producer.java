package elements;

import exceptions.WrongNumericArgumentException;

public class Producer implements Comparable<Producer> {
    private final String name;
    private int id;
    private final int dailyProduction;
    private int vacAvailable;


    public Producer(int id) {
        this.id = id;
        this.dailyProduction = 0;
        this.vacAvailable = 0;
        this.name = null;
    }

    public int getDailyProduction() {
        return dailyProduction;
    }

    public Producer(String name, int id, int dailyProduction) {
        if (id < 0) {
            throw new WrongNumericArgumentException("Błąd id producenta, wartość id nie może być mniejsza od zera");
        }
        if (dailyProduction < 0) {
            throw new WrongNumericArgumentException("Błąd produkcji, wartość dziennej produkcji szczepionek nie może być ujemna");
        }
        if (name.isBlank()) throw new IllegalArgumentException("Nazwa producenta nie może być pusta");
        this.name = name;
        this.id = id;
        this.dailyProduction = dailyProduction;
        vacAvailable = dailyProduction;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVacAvailable() {
        return vacAvailable;
    }

    public void setVacAvailable(int vacAvailable) {
        this.vacAvailable = vacAvailable;
    }

    @Override
    public int hashCode() {
        return this.id * 1238;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Producer)) {
            return false;
        }
        Producer producer = (Producer) o;
        return producer.id == this.id;
    }

    @Override
    public int compareTo(Producer o) {
        return this.id - o.getId();
    }
}
