package com.thanks.form;

import com.thanks.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


/**
 * Created by micky on 2016. 7. 23..
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialSignUpForm extends BaseForm{


    @NotBlank
    private String socialAccessToken;

    @NotBlank
    private User.SignUpType signUpType;

    private String name;

    private String email;

    private String phone;

    private String profilePath;

    private String nickname;


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
        Date date = new Date();

        u.setCreateTime(date);
        u.setUpdatedTime(date);
        return u;
    }
}
