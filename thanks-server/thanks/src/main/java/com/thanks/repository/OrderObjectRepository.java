package com.thanks.repository;

import com.thanks.model.Buy;
import com.thanks.model.OrderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by micky on 2016. 7. 23..
 */
@Repository
public interface OrderObjectRepository extends JpaRepository<OrderObject, Long> {

    List<OrderObject> findByOrderIdAndOrderYn(Long userId, boolean orderYn);

    List<OrderObject> findByOrderIdAndOrderYnAndObjectType(Long userId, boolean orderYn, String orderType);
}
