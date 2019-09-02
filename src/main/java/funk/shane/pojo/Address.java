package funk.shane.pojo;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Address {
    @NotBlank(message = "Street Line 1 cannot be blank")
    private String street_line1;

    private String street_line2;
    
    private String street_line3;
    
    @NotBlank(message = "City cannot be blank")
    private String city;
    
    private String state_full;
    
    private String state_code;
    
    @NotBlank(message = "Postal Code cannot be blank")
    private String postal_code;
    
    private String country;

    public Address() { /* empty constructor */ }

    public Address(String street_line1, String street_line2, String street_line3, String city, String state_full, 
                   String state_code, String postal_code, String country) {
        this.street_line1 = street_line1;
        this.street_line2 = street_line2;
        this.street_line3 = street_line3;
        this.city = city;
        this.state_full = state_full;
        this.state_code = state_code;
        this.postal_code = postal_code;
        this.country = country;
    }
    
    /* So I know that Lombok has support for toString, I just prefer this style */
    @Override
    public String toString() {
     return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}