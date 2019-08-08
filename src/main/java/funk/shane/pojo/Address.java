package funk.shane.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Address {
    private String street_line1;
    private String street_line2;
    private String street_line3;
    private String city;
    private String state_full;
    private String state_code;
    private String postal_code;
    private String country;

    public Address() { /* */ }
 
    public String getStreet_line1() {
        return street_line1;
    }
    public void setStreet_line1(String street_line1) {
        this.street_line1 = street_line1;
    }
 
    public String getStreet_line2() {
        return street_line2;
    }
    public void setStreet_line2(String street_line2) {
        this.street_line2 = street_line2;
    }

    @Override
    public String toString() {
     return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(19, 37, this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}