/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.heartsapiens.tedis.entity.MsgHastaMesaj;

/**
 *
 * @author ersin
 */
@Repository
public interface MsgHastaMesajRepository extends JpaRepository<MsgHastaMesaj,Long>{


    @Query("select ozel FROM MsgHastaMesaj as ozel WHERE " +

            " durum in :durumList "+
            " and id = :hastaId "+
            " and  case when baslik is null then '' else  lower(baslik) end like  lower(:pbaslik) " +
            " and  case when icerik is null then '' else  lower(icerik) end like  lower(:picerik) " +
            " and  case when commStatus is null then '' else  lower(commStatus) end like  lower(:pcommStatus) " +
            " and ( basGonderiZamani is null or (basGonderiZamani>= :dtBasGonZaman) )"+
            " and ( sonGonderiZamani is null or (sonGonderiZamani <= :dtSonGonZaman) )"+
            " order by id "
    )
    Page<MsgHastaMesaj> findByParameters(
            Pageable var,
            @Param("hastaId") Long hastaId,
            @Param("pbaslik") String pbaslik,
            @Param("picerik") String picerik,
             @Param("dtBasGonZaman") Date dtBasGonZaman,
            @Param("dtSonGonZaman") Date dtSonGonZaman,
            @Param("pcommStatus") String pcommStatus,
            @Param("durumList") List<Boolean> durumList);



}