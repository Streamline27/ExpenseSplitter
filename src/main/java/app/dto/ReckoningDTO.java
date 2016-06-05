package app.dto;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class ReckoningDTO {

    private String person;
    private Double amount;

    public ReckoningDTO(String person, Double amount) {
        this.person = person;
        this.amount = amount;
    }

    public ReckoningDTO() {
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
