package com.thanks.repository;

import com.thanks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by micky on 2016. 7. 17..
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByPhone(String phone);

    User findBySocialAccessToken(String token);
}
