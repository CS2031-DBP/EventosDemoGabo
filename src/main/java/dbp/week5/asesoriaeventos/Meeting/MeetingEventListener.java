package dbp.week5.asesoriaeventos.Meeting;

import com.fasterxml.jackson.databind.JsonNode;
import dbp.week5.asesoriaeventos.Email.EmailService;
import dbp.week5.asesoriaeventos.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MeetingEventListener {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private EmailService emailService;

    @EventListener
    @Async
    public void onMeetingCreated(MeetingCreatedEvent event) {
        User user = event.getUser();
        JsonNode data = event.getResponseBody();
//        meetingService.createMeetingAsync(user, data.get("meetingId").asText(), data.get("startDate").asText(), data.get("endDate").asText(), data.get("roomUrl").asText(), data.get("hostRoomUrl").asText());
        emailService.sendEmail(user.getEmail(), "Meeting", "Hola " + user.getName() + ", tienes una reunión programada. El enlace a la sala es: " + data.get("roomUrl").asText());
    }
}
