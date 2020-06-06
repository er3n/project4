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
import tr.com.heartsapiens.tedis.entity. NitGozlemDeger;

/**
 *
 * @author ersin
 */
@Repository
public interface NitGozlemDegerRepository extends JpaRepository< NitGozlemDeger,Long>{    
    public List<NitGozlemDeger> getAllByHasta(Hasta hasta, Pageable pageable);



    @Query("select hasgoz FROM NitGozlemDeger as hasgoz WHERE " +
            " durum in :durumList "+
            " and hasgoz.hasta.id = :hastaId"+
            " and  lower(hasgoz.gozlemTipi.ad) like  lower(:ad) " +
            " and  gozlemZamani between :minZaman and :maxZaman order by gozlemZamani desc "
    )
    Page<NitGozlemDeger> findByParameters(
            Pageable pageable,
            @Param("hastaId")   Long hastaId,
            @Param("ad")  String ad,
            @Param("minZaman") Date minZaman,
            @Param("maxZaman")  Date maxZaman,
            @Param("durumList")  List<Boolean> durumList);
}
