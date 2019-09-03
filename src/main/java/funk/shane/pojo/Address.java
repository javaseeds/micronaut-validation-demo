package funk.shane.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.micronaut.core.annotation.Introspected;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
@Introspected
public class Address {
    @NotBlank(message = "Street Line 1 cannot be blank")
    private String streetLine1;

    private String streetLine2;
    
    private String streetLine3;
    
    @NotBlank(message = "City cannot be blank")
    private String city;
    
    @NotBlank(message = "State Code cannot be blank")
    @Size(min = 2, max = 2, message = "State Code can only be two (2) character long")
    private String stateCode;

    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Postal Code is US 5 digit or 5+4 format")
    @NotBlank(message = "Postal Code cannot be blank")
    private String postalCode;
    
    private String country;

    public Address() { /* empty constructor */ }

    public Address(String streetLine1, String streetLine2, String streetLine3, String city, 
                   String stateCode, String postalCode, String country) {
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.streetLine3 = streetLine3;
        this.city = city;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
        this.country = country;
    }
    
    /* So I know that Lombok has support for toString, I just prefer this style */
    @Override
    public String toString() {
     return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}