package team.marela.backend.api.interfaces.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
public interface TestApiInterface {

    @GetMapping
    String test();
}
