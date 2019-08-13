package funk.shane.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@Slf4j
public class Phone {
    private static final String US_FORMAT = "+%d (%03d) %03d-%04d %s";

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

    public String generateFormatted(@NotBlank(message = "Phone cannot be blank") 
                                    final Phone phone) {
        log.debug("Phone object: {}", phone);                                
        return String.format(US_FORMAT, phone.getCountryCode(), phone.getAreaCode(), phone.getPrefix(), 
          phone.getSuffix(), buildExtension(phone.getExtension()));
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