package com.freedom.misc.request;

import com.freedom.misc.constants.Enums;
import com.freedom.misc.util.validatorutil.EnumValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.freedom.misc.constants.Constants.*;

@Data
public class AddEmployeeRequest {

    @NotBlank(message = NAME_INVALID)
    @Length(max = 23, message = "")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "")
    private String name;

    @NotBlank(message = ADDRESS_INVALID)
    @Length(max = 23, message = "")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "")
    private String address;

    @NotNull(message = CONTACT_INVALID)
    private Long contact;

    @NotNull(message = DOB_INVALID)
    private String dob;

    @NotNull(message = GENDER_INVALID)
    @EnumValidator(enumClass = Enums.GENDER.class, message = GENDER_INVALID)
    private String gender;
}