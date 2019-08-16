package funk.shane.pojo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PhoneTest {

    @Test
    void testHappyPath() {
        String x = "Dog";
        assertThat(x).isEqualTo("Dog");
    }
    
}