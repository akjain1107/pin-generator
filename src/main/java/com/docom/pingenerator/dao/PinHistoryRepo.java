package com.docom.pingenerator.dao;

import com.docom.pingenerator.entity.PinHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface PinHistoryRepo extends JpaRepository<PinHistory,Long> {
    @Query(value = "SELECT * FROM pin_history where msisdn=:msisdn and validated =:validated and " +
            "generation_time between :before_time and :current_time order by generation_time desc",
            nativeQuery=true)
    List<PinHistory> getPinDetailsByMSISDN(@Param("msisdn")String msisdn, @Param("validated")Integer validated,
                                           @Param("before_time")Date before_time, @Param("current_time")Date current_time);
    @Transactional
    @Modifying
    @Query(value = "update pin_history as p set p.validated=:validated where p.msisdn=:msisdn and p.pin=:pin",
            nativeQuery=true)
    int updatePinStatus(@Param("validated")Integer validated,
                        @Param("msisdn") String msisdn,@Param("pin") String pin );


    @Transactional
    @Modifying
    @Query(value = "update pin_history as p set p.validated=:validated where p.msisdn=:msisdn and " +
            "p.generation_time <:before_time",
            nativeQuery=true)
    int discardPin(@Param("validated")Integer validated,
                        @Param("msisdn") String msisdn,
                   @Param("before_time")Date before_time);

    @Transactional
    @Modifying
    @Query(value = "update pin_history as p set p.retries=p.retries+1 where p.msisdn=:msisdn and " +
            "p.generation_time between :before_time and :current_time",
            nativeQuery=true)
    int attempTry(@Param("msisdn") String msisdn,
                   @Param("before_time")Date before_time,@Param("current_time")Date current_time);
}
