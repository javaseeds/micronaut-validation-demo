package funk.shane.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Person {
    private Address address;
    private Phone phone;

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