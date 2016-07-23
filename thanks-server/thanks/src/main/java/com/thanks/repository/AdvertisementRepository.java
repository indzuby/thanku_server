package com.thanks.repository;

import com.thanks.model.Advertisement;
import com.thanks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zuby on 2016. 7. 21..
 */
@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByTypeAndStartTimeBeforeAndEndTimeAfterOrderByPriorityAsc(Advertisement.AdvertisementType type, Date dat1, Date date2);
}
