package team.marela.backend.api.endpoints.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.marela.backend.api.interfaces.test.TestApiInterface;

@RestController
@RequiredArgsConstructor
public class TestApi implements TestApiInterface {

    @GetMapping
    public String test() {
        return "all good";
    }
}
