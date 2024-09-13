package dbp.week5.asesoriaeventos.Meeting;


import com.fasterxml.jackson.databind.JsonNode;
import dbp.week5.asesoriaeventos.User.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MeetingCreatedEvent extends ApplicationEvent {
    private final User user;
    private final JsonNode responseBody;

    public MeetingCreatedEvent(Object source, User user, JsonNode responseBody) {
        super(source);
        this.user = user;
        this.responseBody = responseBody;
    }

}