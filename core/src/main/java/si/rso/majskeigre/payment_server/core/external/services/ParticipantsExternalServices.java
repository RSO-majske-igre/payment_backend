package si.rso.majskeigre.payment_server.core.external.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import si.rso.majskeigre.payment_server.core.external.models.ParticipantDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantsExternalServices {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${external.url.majskeigre_participants_url}")
    private String baseUrl;

    public ParticipantDto getParticipant(UUID participantId) {
        return restTemplate.getForEntity(
                String.format("%s/participants/%s", baseUrl, participantId.toString()),
                ParticipantDto.class
        ).getBody();
    }
}
