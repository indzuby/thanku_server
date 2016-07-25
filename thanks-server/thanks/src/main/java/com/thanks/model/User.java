package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by micky on 2016. 6. 20..
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "password"})})
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column),
        @AttributeOverride(name = "createTime", column = @Column),
        @AttributeOverride(name = "updatedTime", column = @Column)
})
@Entity
public class User extends BaseModel {

    public enum UserType {
        USER("user"), RIDER("rider");

        public String value;

        UserType(String val) {
            value = val;
        }
    }

    public enum SignUpType {
        EMAIL, FACEBOOK, KAKAO;

        @Override
        public String toString() {
            return this.name();
        }

        @JsonCreator
        public static SignUpType fromText(String s) {

            return SignUpType.valueOf(s.toUpperCase());
        }
    }

    public enum SexType{
        MALE,FEMALE;
    }

    @Column(unique = true, length = 20)
    private String phone;

    private String password;

    private String name;

    @Column
    private String nickname;

    @Column(unique = true, length = 50)
    private String email;

    @Column(unique = true)
    private String socialAccessToken;

    private String profilePath = "/images/profile/default.png";

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @Enumerated(EnumType.STRING)
    private SexType sexType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column
    private String address;

    private boolean smsReceiveYn = true;
    private boolean emailReceiveYn = true;
    private boolean pushReceiveYn = true;

}
