package funk.shane.pojo;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;

@MicronautTest
public class PhoneTest {

    @Inject
    Validator validator;

    @Test
    void testHappyPath() {
        
    }
}