package funk.shane.pojo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import funk.shane.util.Utils;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import lombok.extern.slf4j.Slf4j;

@MicronautTest
@Slf4j
public class PhoneTest {

    @Inject
    Validator validator;

    @Test
    void testHappyPath() {
        final Phone testPhone = Utils.getClassFromJsonResource(Phone.class, "phone-1.json");
        assertNotNull(testPhone);
        log.info("testPhone: {}", testPhone);
    }
}