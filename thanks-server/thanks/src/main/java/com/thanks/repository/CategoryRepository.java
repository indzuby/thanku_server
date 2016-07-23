package com.thanks.repository;

import com.thanks.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 22..
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByOrderByPriorityAsc();


}
