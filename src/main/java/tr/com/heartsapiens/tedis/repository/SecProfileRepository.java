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
import tr.com.heartsapiens.tedis.entity.SecProfile;

import java.util.Date;

/**
 *
 * @author ersin
 */
@Repository
public interface SecProfileRepository extends JpaRepository<SecProfile,Long>{




    @Query("select prof FROM SecProfile as prof WHERE " +
            " durum = case when :durum is null then durum else :durum end "+
            " and  case when name is null then '' else  lower(name) end like  lower(:name) " +
            " and  case when aciklama is null then '' else  lower(aciklama) end like  lower(:aciklama) order by id "
            )
    public Page<SecProfile> findByParameters(
            Pageable var,

            @Param("name") String name,
            @Param("aciklama") String aciklama,
            @Param("durum") Boolean durum

    );
    
}
