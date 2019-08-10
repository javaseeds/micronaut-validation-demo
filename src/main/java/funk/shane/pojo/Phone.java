package funk.shane.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Phone {
    @Min(1)
    private int countryCode;

    @Min(201) @Max(999)
    private int areaCode;

    @Min(101) @Max(999)
    private int prefix;

    @Max(9999)
    private int suffix;

    private Integer extension;

    private String usformatted;

    public Phone() { /* empty constructor */ }

    public void generateFormatted() {
        this.usformatted = String.format("+%d (%03d) %03d-%04d %s", this.countryCode, this.areaCode, 
          this.prefix, this.suffix, buildExtension(this.extension));
    }

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
    	return this.usformatted;
    }
    public void setFormatted(String formatted) {
    	this.usformatted = formatted;
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

    private static String buildExtension(final Integer extension) {
        return extension == null ? "" : "x" + extension;
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