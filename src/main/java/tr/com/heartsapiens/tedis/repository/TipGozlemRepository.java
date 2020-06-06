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
import tr.com.heartsapiens.tedis.entity.TipGozlem;

import java.util.List;

/**
 *
 * @author ersin
 */
@Repository
public interface TipGozlemRepository extends JpaRepository<TipGozlem,Long>{



    @Query("select gozlem FROM TipGozlem as gozlem WHERE " +
            " durum in :durumList "+
            " and  case when ad is null then '' else  lower(ad) end like  lower(:ad) " +
            " and  case when veriTip is null then '' else  lower(veriTip) end like  lower(:veritip) " +
            " and  case when aciklama is null then '' else  lower(aciklama) end like  lower(:aciklama) order by id "
    )
    public Page<TipGozlem> findByParameters(
            Pageable var,
            @Param("ad")  String ad,
            @Param("veritip") String veritip,
            @Param("aciklama") String aciklama,
            @Param("durumList") List<Boolean> durumList);

}
