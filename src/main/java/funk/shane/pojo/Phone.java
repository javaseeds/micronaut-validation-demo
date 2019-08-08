package funk.shane.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Phone {
    private int countryCode;
    private int areaCode;
    private int prefix;
    private int suffix;
    private int extension;
    private String formatted;

    public Phone() { /* empty constructor */ }

    public int getPrefix() {
    	return this.prefix;
    }
    public void setPrefix(int prefix) {
    	this.prefix = prefix;
    }

    public int getSuffix() {
    	return this.suffix;
    }
    public void setSuffix(int suffix) {
    	this.suffix = suffix;
    }

    public int getExtension() {
    	return this.extension;
    }
    public void setExtension(int extension) {
    	this.extension = extension;
    }

    public String getFormatted() {
    	return this.formatted;
    }
    public void setFormatted(String formatted) {
    	this.formatted = formatted;
    }

    public int getAreaCode() {
    	return this.areaCode;
    }
    public void setAreaCode(int areaCode) {
    	this.areaCode = areaCode;
    }

    public int getCountryCode() {
    	return this.countryCode;
    }
    public void setCountryCode(int countryCode) {
    	this.countryCode = countryCode;
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