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
import tr.com.heartsapiens.tedis.entity.KurumKurulus;

import java.util.List;

/**
 *
 * @author ersin
 */
@Repository
public interface KurumKurulusRepository extends JpaRepository<KurumKurulus,Long>{

    @Query("select kurum FROM KurumKurulus as kurum WHERE " +
            " durum in :durumList "+
            " and  case when kod is null then '' else  lower(kod) end like  lower(:kod) " +
            " and  case when ad is null then '' else  lower(ad) end like  lower(:ad) " +
            " and  case when adres is null then '' else  lower(adres) end like  lower(:adres) " +
            " and  case when tel is null then '' else  lower(tel) end like  lower(:tel) " +
            " and  case when eposta is null then '' else  lower(eposta) end like  lower(:eposta) " +
            " order by id "
    )
    Page<KurumKurulus> findByParameters(Pageable var,
                                     @Param("kod") String kod,
                                     @Param("ad") String ad,
                                        @Param("adres") String adres,
                                        @Param("eposta") String eposta,
                                        @Param("tel") String tel,
                                     @Param("durumList") List<Boolean> durumList);
    
    
}
