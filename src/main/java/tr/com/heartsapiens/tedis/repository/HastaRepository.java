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
import tr.com.heartsapiens.tedis.entity.KurumKurulus;

/**
 *
 * @author ersin
 */

@Repository
public interface HastaRepository extends JpaRepository<Hasta,Long>{
    
    // uyruk degerine göre getir
    List<Hasta> getAllByUyruk(String uyruk);

    //aciklamada eşleşenler
    List<Hasta> getAllByAciklamaLike(String aciklama);

    public Page<Hasta> getAllByKurumKurulus(KurumKurulus kurumKurulus, Pageable pageable);



    // :uma null tarih kontrolü yapamadım, :dogtar = CURRENT_DATE  kullandım

    @Query("select hasta FROM Hasta as hasta WHERE " +
            " durum in :durumList "+
            " and  case when uyruk is null then '' else  lower(uyruk) end like  lower(:uyruk) "+
            " and  ( :tcno is null or (tcno = :tcno) )" +
            " and  case when ad is null then '' else  lower(ad) end like  lower(:ad) " +
            " and  case when soyad is null then '' else  lower(soyad) end like  lower(:soyad) " +
            " and  case when email is null then '' else  lower(email) end like  lower(:email) " +
            " and  case when tel is null then '' else  lower(tel) end like  lower(:tel) " +
            " and  case when aciklama is null then '' else  lower(aciklama) end like  lower(:aciklama) " +
            " and ( :dogtar = CURRENT_DATE or dogtar = :dogtar )"+
            " and ( :kurumId is null or (hasta.kurumKurulus.id = :kurumId) )"+

            " order by id "
    )
    public Page<Hasta> findByParameters(
            Pageable pageable,
            @Param("tcno") Long tcno,
            @Param("ad") String ad,
            @Param("soyad") String soyad,
            @Param("email") String email,
            @Param("tel") String tel,
            @Param("dogtar") Date dogtar,
            @Param("aciklama") String aciklama,
            @Param("uyruk") String uyruk,
            @Param("durumList") List<Boolean> durumList,
            @Param("kurumId") Long kurumId
            );
}
