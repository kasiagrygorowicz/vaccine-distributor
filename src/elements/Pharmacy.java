package elements;

import exceptions.WrongNumericArgumentException;

public class Pharmacy implements Comparable<Pharmacy> {
    private final String name;
    private int id;
    private final int dailyDemand;
    private int vaxNum;

    public Pharmacy(int id) {
        this.id = id;
        this.dailyDemand = 0;
        this.vaxNum = 0;
        this.name = null;

    }

    public Pharmacy(String name, int id, int dailyDemand) {
        if (id < 0) {
            throw new WrongNumericArgumentException("Błąd id apteki, wartość id nie może być mniejsza od zera");
        }
        if (dailyDemand <= 0) {
            throw new WrongNumericArgumentException("Błąd zapotrzebowania, wartość zapotrzebowania musi być większa od zera");
        }
        if (name.isBlank()) throw new IllegalArgumentException("Nazwa apteki nie może być pusta");
        this.name = name;
        this.id = id;

        this.dailyDemand = dailyDemand;
        vaxNum = dailyDemand;
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

    public int getDailyDemand() {
        return dailyDemand;
    }

    public int getVaxNum() {
        return vaxNum;
    }

    public void setVaxNum(int vaxNum) {
        this.vaxNum = vaxNum;
    }

    @Override
    public int hashCode() {
        return this.id * 1234;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Pharmacy)) {
            return false;
        }
        Pharmacy pharmacy = (Pharmacy) o;
        return pharmacy.id == this.id;

    }

    @Override
    public int compareTo(Pharmacy o) {
        return this.id - o.getId();
    }
}
