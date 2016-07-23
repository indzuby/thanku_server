package com.thanks.repository;

import com.thanks.model.Inquire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zuby on 2016. 7. 23..
 */
@Repository
public interface InquireRepository extends JpaRepository<Inquire,Long>{

}
