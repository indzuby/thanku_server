package com.thanks.form;

import com.thanks.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * Created by micky on 2016. 7. 23..
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialSignUpForm extends BaseForm{


    @NotNull
    @NotBlank
    private String socialAccessToken;

    @NotNull
    @NotBlank
    private User.SignUpType type;

    private String name;

    private String email;

    private String phone;


    public User toUser() {
        User u = modelMapper.map(this, User.class);
        return u;
    }
}
