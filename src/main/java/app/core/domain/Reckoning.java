package app.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Vladislav on 5/31/2016.
 */
public class Reckoning {
    private String person;
    private Double amount;

    public Reckoning(String person, Double amount) {
        this.person = person;
        this.amount = amount;
    }

    public void inverseSign(){
        this.amount = amount * -1;
    }

    public boolean isPositive(){
        return amount>0;
    }

    public boolean isNegative(){
        return amount<0;
    }

    public void add(Double value){
        amount += value;
    }

    public void extract(Double value){
        amount -= value;
    }

    public boolean amountEquals(Reckoning reckoning){
        return Math.abs(amount - reckoning.getAmount()) <= 0.0001;
    }

    /* Getters and Setters */

    public String getPerson() {
        return person;
    }

    public Double getAmount() {
        return amount;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }



}
