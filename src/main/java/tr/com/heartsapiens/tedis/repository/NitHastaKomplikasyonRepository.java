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
import tr.com.heartsapiens.tedis.entity.Hasta;
import tr.com.heartsapiens.tedis.entity.NitGozlemDeger;
import tr.com.heartsapiens.tedis.entity.NitHastaKomplikasyon;

/**
 *
 * @author ersin
 */
@Repository
public interface NitHastaKomplikasyonRepository extends JpaRepository<NitHastaKomplikasyon,Long>{
    
      public List<NitHastaKomplikasyon> getAllByHasta(Hasta hasta, Pageable pageable);


    @Query("select haskom FROM NitHastaKomplikasyon as haskom WHERE " +
            " durum in :durumList "+
            " and haskom.hasta.id = :hastaId"+
            " and lower(haskom.olay.ad) like  lower(:polayAd) " +
            " and  case when olayAciklama is null then '' else  lower(olayAciklama) end like  lower(:polayAciklama) " +
            " and  case when haskom.tedaviSonuc.ad is null then '' else  lower(haskom.tedaviSonuc.ad) end like  lower(:ptedaviSonucAd) " +
            " and  haskom.olayZaman between :dtMinZaman and :dtMaxZaman " +
            " order by olayZaman desc "
    )

      Page<NitHastaKomplikasyon> findByParameters(
            Pageable pageable,
            @Param("hastaId") Long hastaId,
            @Param("polayAd") String polayAd,
            @Param("polayAciklama") String polayAciklama,
            @Param("ptedaviSonucAd") String ptedaviSonucAd,
            @Param("dtMinZaman") Date dtMinZaman,
            @Param("dtMaxZaman") Date dtMaxZaman,
            @Param("durumList") List<Boolean> durumList);
}
