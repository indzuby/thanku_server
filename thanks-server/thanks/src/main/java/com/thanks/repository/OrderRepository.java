package com.thanks.repository;

import com.thanks.model.Buy;
import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by micky on 2016. 7. 23..
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderObject, Long> {

    List<OrderObject> findByOrderIdAndOrderYn(Long userId, boolean orderYn);

    List<OrderObject> findByOrderIdAndOrderYnAndObjectType(Long userId, boolean orderYn, String orderType);

    List<OrderObject> findByOrderYnAndObjectTypeAndOrderInfo(boolean orderYn, String orderType, Long orderInfoId);

    @Query(value = "SELECT sum(price)+sum(add_price) as price, count(*) as count, group_concat(comment SEPARATOR ' / ') comment, updated_time as orderDate FROM order_object WHERE order_id= :user AND order_yn = 1 GROUP BY updated_time ORDER BY updated_time DESC",nativeQuery = true)
    List<OrderInfo> getOrderInfo(@Param("user")Long userId);

}
