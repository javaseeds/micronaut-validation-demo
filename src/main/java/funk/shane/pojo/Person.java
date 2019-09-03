package funk.shane.pojo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.micronaut.core.annotation.Introspected;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
@Introspected
public class Person {
    @NotBlank(message = "Person requires an address")
    private Address address;

    @NotBlank(message = "Person requires a phone")
    private Phone phone;

    @NotBlank(message = "Person requires a birthday")
    @Past(message = "Person's birthday cannot be in present or the future")
    private LocalDate birthDate;

    public Person() { /* empty constructor */ }

    /* So I know that Lombok has support for toString, I just prefer this style */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}