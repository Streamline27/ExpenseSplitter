package app.dto;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class EventDTO {
    private Long eventId;
    private String eventName;

    public EventDTO(Long eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }

    public EventDTO() {
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
}
