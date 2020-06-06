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
import tr.com.heartsapiens.tedis.entity.MsgZamanliMesajAlici;

import java.util.List;

/**
 *
 * @author ersin
 */
@Repository
public interface MsgZamanliMesajAliciRepository extends JpaRepository<MsgZamanliMesajAlici,Long>{


    @Query("select haszam FROM MsgZamanliMesajAlici as haszam WHERE " +
            " durum in :durumList "+
            " and haszam.hasta.id = :hastaId"+
            " and lower(haszam.mesajKalip.baslik) like  lower(:msgBaslik) " +
            " and lower(haszam.mesajKalip.icerik) like  lower(:msgIcerik) " +
            " and lower(haszam.mesajKalip.ayDowGunSaatDakikaPattern) like  lower(:pattern) " +
            " order by id desc "
    )
    Page<MsgZamanliMesajAlici> findByParameters(
            Pageable pageable,
            @Param("hastaId") Long hastaId,
            @Param("msgBaslik") String msgBaslik,
            @Param("msgIcerik") String msgIcerik,
            @Param("pattern") String pattern,
            @Param("durumList") List<Boolean> durumList);
    
}
