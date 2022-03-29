package elements;

public class Deal {
    private final double price;
    private final int vacNumber;
    private final String pName;
    private final String prodName;

    public Deal(double price, int vacNumber, String pName, String prodName) {
        this.price = price;
        this.vacNumber = vacNumber;
        this.pName = pName;
        this.prodName = prodName;
    }

    @Override
    public String toString() {

        return prodName + "->" + pName + "\t[koszt: " + vacNumber + " * " + Math.round(price*100)/100.0 + " = " + Math.round(100*getTotalCost())/100.0 + " zł]";
    }

    public double getTotalCost() {
        return vacNumber * price;
    }
}
