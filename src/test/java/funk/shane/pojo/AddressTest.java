package funk.shane.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import funk.shane.util.Utils;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;

@MicronautTest
public class AddressTest {
    private static final Logger log = LoggerFactory.getLogger(AddressTest.class);

    @Inject
    private Validator validator;

    /**
     * The first test of the validation is that all validations pass.  Here, all the Address class 
     * required validations pass.  Subsequent tests will go outside the boundaries of the Validation
     */
    @Test
    void testHappyPath() {
        final Address testAddress = getTestAddress("address-1.json");
        assertNotNull(testAddress);
        log.info("testAddress: {}", testAddress);

        final Set<ConstraintViolation<Address>> violations = validator.validate(testAddress);
        assertEquals(0, violations.size());        
    }

    @Test
    void testBlankAddyLine1() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setStreetLine1("");

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("Street Line 1 cannot be blank", addyViolation.getMessage());    
    }

    @Test
    void testNullAddyLine1() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setStreetLine1(null);

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("Street Line 1 cannot be blank", addyViolation.getMessage());    
    }

    @Test
    void testBlankCity() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setCity("");

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("City cannot be blank", addyViolation.getMessage());    
    }

    @Test
    void testNullCity() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setCity(null);

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("City cannot be blank", addyViolation.getMessage());    
    }
    @Test
    void testStateCodeTooSmall() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setStateCode("X");

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("State Code can only be two (2) character long", addyViolation.getMessage());    
    }

    @Test
    void testStateCodeTooLarge() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setStateCode("HEY");

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("State Code can only be two (2) character long", addyViolation.getMessage());    
    }

    @Test
    void testBlankStateCode() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setStateCode("");

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(2, violations.size());

        final List<String> checkStrings = 
            List.of("State Code can only be two (2) character long", "State Code cannot be blank");

        List<String> validationStrings = violations.stream()
            .map(v -> v.getMessage())
            .sorted()
            .collect(Collectors.toList());
        
        assertEquals(checkStrings, validationStrings);    
   }

    @Test
    void testNullStateCode() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setStateCode(null);

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("State Code cannot be blank", addyViolation.getMessage());    
    }

    @Test
    void testNullPostalCode() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setPostalCode(null);

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());


        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("Postal Code cannot be blank", addyViolation.getMessage());    
    }

    @Test
    void testBlankPostalCode() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setPostalCode("");

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(2, violations.size());

        final List<String> checkStrings =
            List.of("Postal Code cannot be blank", "Postal Code in US is 5 digit or 5+4 format");

        List<String> validationStrings = violations.stream()
            .map(v -> v.getMessage())
            .sorted()
            .collect(Collectors.toList());
    
        assertEquals(checkStrings, validationStrings);    
    }

    @Test
    void testPostalCodePlus4HappyPath() {
        final Address plus4Addy = getTestAddress("address-1.json");
        plus4Addy.setPostalCode("12345-6789");

        final Set<ConstraintViolation<Address>> violations = validator.validate(plus4Addy);
        assertEquals(0, violations.size());
    }

    @Test
    void testPostCodePlus4Not4Digits() {
        final Address badAddy = getTestAddress("address-1.json");
        badAddy.setPostalCode("12345-67");

        final Set<ConstraintViolation<Address>> violations = validator.validate(badAddy);
        assertEquals(1, violations.size());

        ConstraintViolation<Address> addyViolation = violations.iterator().next();
        assertEquals("Postal Code in US is 5 digit or 5+4 format", addyViolation.getMessage());    
    }

    /* Generate a test object - change as needed */
    private static Address getTestAddress(final String jsonFile) {
        return Utils.getClassFromJsonResource(Address.class, jsonFile);
    }
}