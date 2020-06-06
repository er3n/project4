/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import sun.util.resources.LocaleData;
import tr.com.heartsapiens.tedis.entity.LogTedis;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ersin
 */
@Repository
public interface LogTedisRepository extends JpaRepository<LogTedis,Long>{
    public Page<LogTedis> findAllByOrderByIdDesc(Pageable var);


    @Query("select log FROM LogTedis as log WHERE " +
            " methodName  = (case when :methodName is null then  methodName else   :methodName   end) " +
            " and  (:hastaId is null  or log.hastaId = :hastaId)    " +
            " and opTime >=  :basZaman    " +
            " and opTime <= :bitZaman " +
            " and result = (case  when :tip is null then result else :tip end ) " +
            " and opDuration >= (case when  :minSure is null then opDuration else :minSure end)  " +
            " and opDuration <= (case when  :maxSure is null then opDuration else :maxSure end) " +
            " and remoteIp = (case  when :ip is null then remoteIp else :ip end ) " +
            " and user = (case  when :kullanici is null then user else :kullanici end ) " +
            " and  case when exceptionString is null then '' else  lower(exceptionString) end like  lower(:detay) " +
            " order by  id desc ")
    public  Page<LogTedis> findByParameters(
            Pageable var,
            @Param("hastaId") Long hastaId,
            @Param("methodName") String methodName,
            @Param("basZaman") Date basZaman,
            @Param("bitZaman")  Date  bitZaman,
             @Param("tip") String tip,
            @Param("minSure") Long minSure,
            @Param("maxSure") Long maxSure,
            @Param("ip") String ip,
            @Param("kullanici") String kullanici,
            @Param("detay") String detay

    );

}
