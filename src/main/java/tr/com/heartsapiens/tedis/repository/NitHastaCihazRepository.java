/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.repository;
 
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.heartsapiens.tedis.entity.Hasta;
import tr.com.heartsapiens.tedis.entity.NitHastaCihaz;

/**
 *
 * @author ersin
 */
@Repository
public interface NitHastaCihazRepository extends JpaRepository<NitHastaCihaz,Long>{
      public List<NitHastaCihaz> getAllByHasta(Hasta hasta, Pageable pageable);




      @Query("select hascihaz FROM NitHastaCihaz as hascihaz WHERE " +
              " durum in :durumList "+
              " and hascihaz.hasta.id = :hastaId"+
              " and lower(hascihaz.cihazTipi.ad) like  lower(:ad) " +
              " and lower(hascihaz.cihazTipi.model) like  lower(:model) " +
              " and  case when aciklama is null then '' else  lower(aciklama) end like  lower(:aciklama) order by baslangicZamani desc "
      )
      Page<NitHastaCihaz> findByParameters(
              Pageable pageable,
              @Param("hastaId")   Long hastaId,
              @Param("ad")  String ad,
              @Param("model")  String model,
              @Param("aciklama")  String aciklama,
              @Param("durumList")  List<Boolean> durumList);
}
