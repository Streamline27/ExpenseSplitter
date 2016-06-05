package app.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long eventId;

    @Column private String eventName;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Expense> expenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="username")
    private User user;

    public Event() {
    }

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<Expense> getExpenses() {
        if(expenses == null) expenses = new ArrayList<>();
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        if(expenses == null) expenses = new ArrayList<>();
        this.expenses = expenses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
