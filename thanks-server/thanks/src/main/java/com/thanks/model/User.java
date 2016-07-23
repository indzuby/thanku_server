package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.thanks.util.LengthUtil;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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


    @Column(unique = true, length = 20)
    private String phone;

    private String password;

    private String name;

    @Column(unique = true, length = 50)
    private String email;

    @Column(unique = true)
    private String socialAccessToken;

    private String profilePath = "/images/profile/default.png";

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;


    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Quick> quickList = new ArrayList<>();

}
