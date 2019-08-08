package funk.shane.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
}