package com.thanks.form;

import com.thanks.model.User;
import com.thanks.util.LengthUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by micky on 2016. 7. 18..
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmailSignUpForm extends BaseForm {

    @Email
    @NotNull
    @NotBlank
    @Length(max = LengthUtil.DEFAULT_STRING_MAX_LENGTH)
    private String email;

    @NotBlank
    @NotNull
    @Length(max = LengthUtil.DEFAULT_STRING_MAX_LENGTH)
    private String name;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    @Length(max = LengthUtil.DEFAULT_STRING_MAX_LENGTH)
    private String phone;

    public User toRider() {
        return toUser(true);
    }

    public User toUser() {
        return toUser(false);
    }

    public User toUser(boolean isRider) {
        User u = modelMapper.map(this, User.class);
        if(isRider)
            u.setType(User.UserType.RIDER);
        else
            u.setType(User.UserType.USER);
        u.setSignUpType(User.SignUpType.EMAIL);

        return u;
    }
}
