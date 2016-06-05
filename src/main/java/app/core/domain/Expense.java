package app.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Vladislav on 5/31/2016.
 */

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long expenseId;

    @Column private String name;
    @Column private String service;
    @Column private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="eventId")
    private Event event;

    private Expense() {
    }

    public Expense(String name, String service, Double amount) {
        this.name = name;
        this.service = service;
        this.amount = amount;
    }



    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getId() {
        return expenseId;
    }

    public void setId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
