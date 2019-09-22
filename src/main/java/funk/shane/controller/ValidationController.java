package funk.shane.controller;

import funk.shane.pojo.Person;
import funk.shane.util.Utils;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;

@Controller("/api")
@Slf4j
public class ValidationController {
    
    @Get(uri = "/v1/valid", produces = {MediaType.TEXT_PLAIN})
    public String validDemo(@Body Person person) {
        log.info("inbound person: {}", person);

        return String.format("Person [%s] was vaild", person);
    }

    @Get(uri = "/v1/get-a-person", produces = {MediaType.APPLICATION_JSON})
    public Person getPerson() {
        return Utils.getClassFromJsonResource(Person.class, "person-1.json");
    }
}