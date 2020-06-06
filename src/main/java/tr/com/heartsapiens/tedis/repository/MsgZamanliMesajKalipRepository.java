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
import tr.com.heartsapiens.tedis.entity.MsgZamanliMesajKalip;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ersin
 */
@Repository
public interface MsgZamanliMesajKalipRepository extends JpaRepository<MsgZamanliMesajKalip,Long>{


    @Query("select kalip FROM MsgZamanliMesajKalip as kalip WHERE " +
            " durum in :durumList "+
            " and  case when baslik is null then '' else  lower(baslik) end like  lower(:pbaslik) " +
            " and  case when icerik is null then '' else  lower(icerik) end like  lower(:picerik) " +
            " and ( basGonderiZamani is null or (basGonderiZamani>= :dtBasGonZaman) )"+
            " and ( sonGonderiZamani is null or (sonGonderiZamani <= :dtSonGonZaman) )"+
            " and ( :ayDowGunSaatDakikaPattern = '' or case when ayDowGunSaatDakikaPattern is null then '' else" +
            "  lower(ayDowGunSaatDakikaPattern) end =  lower(:ayDowGunSaatDakikaPattern) ) " +
            " order by id "
    )
    Page<MsgZamanliMesajKalip> findByParameters(
            Pageable var,
            @Param("pbaslik") String pbaslik,
            @Param("picerik") String picerik,
            @Param("dtBasGonZaman") Date dtBasGonZaman,
            @Param("dtSonGonZaman") Date dtSonGonZaman,
            @Param("ayDowGunSaatDakikaPattern") String ayDowGunSaatDakikaPattern,
            @Param("durumList") List<Boolean> durumList);


}
