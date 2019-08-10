package funk.shane.pojo;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    public Address(String street_line1, String street_line2, String street_line3, String city, String state_full, String state_code, String postal_code, String country) {
        this.street_line1 = street_line1;
        this.street_line2 = street_line2;
        this.street_line3 = street_line3;
        this.city = city;
        this.state_full = state_full;
        this.state_code = state_code;
        this.postal_code = postal_code;
        this.country = country;
    }

    public String getStreet_line3() {
        return this.street_line3;
    }

    public void setStreet_line3(String street_line3) {
        this.street_line3 = street_line3;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_full() {
        return this.state_full;
    }

    public void setState_full(String state_full) {
        this.state_full = state_full;
    }

    public String getState_code() {
        return this.state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getPostal_code() {
        return this.postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address street_line1(String street_line1) {
        this.street_line1 = street_line1;
        return this;
    }

    public Address street_line2(String street_line2) {
        this.street_line2 = street_line2;
        return this;
    }

    public Address street_line3(String street_line3) {
        this.street_line3 = street_line3;
        return this;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public Address state_full(String state_full) {
        this.state_full = state_full;
        return this;
    }

    public Address state_code(String state_code) {
        this.state_code = state_code;
        return this;
    }

    public Address postal_code(String postal_code) {
        this.postal_code = postal_code;
        return this;
    }

    public Address country(String country) {
        this.country = country;
        return this;
    }

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