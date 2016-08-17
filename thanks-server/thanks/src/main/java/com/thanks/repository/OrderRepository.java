package com.thanks.repository;

import com.thanks.model.*;
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

    List<OrderObject> findByRiderAndCompleteYnOrderByCreateTimeDesc(User rider, boolean completeYn);

    @Query(value = "SELECT sum(price)+sum(add_price) as price, count(*) as count, group_concat(comment SEPARATOR ' / ') comment, updated_time as orderDate FROM order_object WHERE order_id= :user AND order_yn = 1 GROUP BY updated_time ORDER BY updated_time DESC",nativeQuery = true)
    List<OrderInfo> getOrderInfo(@Param("user")Long userId);

    @Query(value= "select *,( 6371 * acos( cos( radians(:lon) ) * cos( radians( lat ) ) * cos( radians( lon ) - radians(:lat) ) + sin( radians(:lon) ) * sin( radians( lat ) ) ) ) as distance from buy b left join order_object oo on b.id=oo.id where oo.match_yn=0 having distance <= :distance", nativeQuery = true)
    List<Buy> findNotMatchedBuyByLocation(@Param("lat") Double lat, @Param("lon") Double lon, @Param("distance") Double distance);

    @Query(value= "select *,( 6371 * acos( cos( radians(:lon) ) * cos( radians( lat ) ) * cos( radians( lon ) - radians(:lat) ) + sin( radians(:lon) ) * sin( radians( lat ) ) ) ) as distance from errand e left join order_object oo on e.id=oo.id where oo.match_yn=0 having distance <= :distance ", nativeQuery = true)
    List<Errand> findNotMatchedErrandByLocation(@Param("lat") Double lat, @Param("lon") Double lon, @Param("distance") Double distance);

    @Query(value= "select *,( 6371 * acos( cos( radians(:lon) ) * cos( radians( start_lat ) ) * cos( radians( start_lon ) - radians(:lat) ) + sin( radians(:lon) ) * sin( radians( start_lat ) ) ) ) as distance from quick q left join order_object oo on q.id=oo.id where oo.match_yn=0 having distance <= :distance", nativeQuery = true)
    List<Quick> findNotMatchedQuickByLocation(@Param("lat") Double lat, @Param("lon") Double lon, @Param("distance") Double distance);

    @Query(value= "select *,( 6371 * acos( cos( radians(:lon) ) * cos( radians( lat ) ) * cos( radians( lon ) - radians(:lat) ) + sin( radians(:lon) ) * sin( radians( lat ) ) ) ) as distance from restaurant_order ro left join order_object oo on ro.id=oo.id left join restaurant r on ro.id=r.id where oo.match_yn=0 having distance <= :distance", nativeQuery = true)
    List<RestaurantOrder> findNotMatchedRestaurantOrderByLocation(@Param("lat") Double lat, @Param("lon") Double lon, @Param("distance") Double distance);


}
