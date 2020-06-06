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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.heartsapiens.tedis.entity.TipOlay;

import java.util.List;

/**
 *
 * @author ersin
 */
@Repository
public interface TipOlayRepository extends JpaRepository<TipOlay,Long>{


    @Query("select olay FROM TipOlay as olay WHERE " +
            " durum in :durumList "+
            " and  case when kod is null then '' else  lower(kod) end like  lower(:kod) " +
            " and  case when ad is null then '' else  lower(ad) end like  lower(:ad) " +
            " order by id "
    )
    Page<TipOlay> findByParameters(Pageable var,
                                   @Param("kod")   String kod,
                                   @Param("ad")  String ad,
                                   @Param("durumList")  List<Boolean> durumList);
}
