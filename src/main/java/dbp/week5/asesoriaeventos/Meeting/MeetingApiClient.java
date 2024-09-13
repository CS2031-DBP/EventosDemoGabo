package dbp.week5.asesoriaeventos.Meeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbp.week5.asesoriaeventos.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Map;

@Component
public class MeetingApiClient {

//    final private ApplicationEventPublisher eventPublisher;

//    @Autowired
//    public MeetingApiClient(ApplicationEventPublisher eventPublisher) {
//        this.eventPublisher = eventPublisher;
//    }

    @Value("${WHEREBY_API_KEY}")
    private String wherebyApiKey;

    final private HttpClient httpClient = HttpClient.newHttpClient();

    @Async
    public void createWherebyMeeting(User user) throws IOException, InterruptedException {
        var data = Map.of("endDate", "2024-09-13T14:23:00.000Z", "fields", Collections.singletonList("hostRoomUrl"));

        var request = HttpRequest.newBuilder(URI.create("https://api.whereby.dev/v1/meetings"))
                .header("Authorization", "Bearer " + wherebyApiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(data)))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            var responseBody = new ObjectMapper().readTree(response.body());
            System.out.println("Reunion creada correctamente");
//            eventPublisher.publishEvent(new MeetingCreatedEvent(this, user, responseBody));
        } else {
            throw new RuntimeException("Error creating meeting");
        }
    }
}
