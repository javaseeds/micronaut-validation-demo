package funk.shane.pojo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Person {
    @NotBlank(message = "Person requires an address")
    private Address address;

    @NotBlank(message = "Person requires a phone")
    private Phone phone;

    @NotBlank(message = "Person requires a birthday")
    @Past(message = "Person's birthday cannot be in present or the future")
    private LocalDate birthDate;

    public Person() { /* empty constructor */ }

    public Address getAddress() {
    	return this.address;
    }

    public void setAddress(Address address) {
    	this.address = address;
    }

    public Phone getPhone() {
    	return this.phone;
    }
    public void setPhone(Phone phone) {
    	this.phone = phone;
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