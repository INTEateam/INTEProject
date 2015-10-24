package inte.projekt;

/**
 * Created by Nicklas on 2015-10-24.
 */
public class Money {
    int amount;

    Money(int amount){
        this.amount = amount;
    }

    public void add(int amountToAdd){
        this.amount = this.amount + amountToAdd;
    }

    @Override
    public String toString() {
        return amount + ".00";
    }
}