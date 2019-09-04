package funk.shane.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import funk.shane.util.Utils;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import lombok.extern.slf4j.Slf4j;

@MicronautTest
@Slf4j
public class PersonTest {

    @Inject
    private Validator validator;

    @Test
    @Disabled
    void testHappyPath() {
        final Person testPerson = getTestPerson("person-1.json");
        assertNotNull(testPerson);
        log.info("testPerson: {}", testPerson);

        final Set<ConstraintViolation<Person>> violations = validator.validate(testPerson);
        assertEquals(0, violations.size());
    }

    /* Generate a test object - change as needed */
    private static Person getTestPerson(final String jsonFile) {
        return Utils.getClassFromJsonResource(Person.class, jsonFile);
    }
}