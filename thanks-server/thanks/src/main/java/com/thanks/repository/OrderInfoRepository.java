package com.thanks.repository;

import com.thanks.model.OrderInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by micky on 2016. 7. 24..
 */
@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
    List<OrderInfo> findAllByOrderIdOrderByUpdatedTimeDesc(Long id);

//    @PersistenceContext
//    EntityManager em;
//
//    //@Query(value = "SELECT sum(price)+sum(add_price) as price, count(*) as count, group_concat(comment SEPARATOR ' / ') comment, updated_time as orderDate FROM order_object WHERE order_id= :user AND order_yn = 1 GROUP BY updated_time ORDER BY updated_time DESC",nativeQuery = true)
//    public List<OrderInfo> getOrderInfo(@Param("user")Long userId) {
//
//        TypedQuery<OrderInfo> q = em.createQuery("SELECT sum(price)+sum(add_price) as price, count(*) as count, group_concat(comment SEPARATOR ' / ') comment, updated_time as orderDate FROM order_object WHERE order_id = :user AND order_yn = 1 GROUP BY updated_time ORDER BY updated_time DESC",
//                OrderInfo.class);
//        q.setParameter("user", userId);
//        return q.getResultList();
//    }

}
