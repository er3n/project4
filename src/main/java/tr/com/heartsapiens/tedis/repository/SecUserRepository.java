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
import tr.com.heartsapiens.tedis.entity.SecProfile;
import tr.com.heartsapiens.tedis.entity.SecUser;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author ersin
 */
@Repository
public interface SecUserRepository extends JpaRepository<SecUser,Long>{

    @Query("select distinct user FROM SecUser user inner join user.profileList p WHERE" +
            " ( user.durum in :enabledList)  "+
            " and  lower(user.username) like  lower(:username) " +
            " and  lower(user.name)  like  lower(:name) " +
            " and  lower(user.surname)  like  lower(:surname) " +
            " and  lower(user.email)  like  lower(:email) " +
            " and  ( :kurumKurulus is null or user.kurumKurulus = :kurumKurulus) " +
            " and  ( :profileList is null or p in :profileList ) " +
            " and p.durum = true " +
            " order by user.id "
    )
    public Page<SecUser> findByParameters(Pageable var,
                                          @Param("username") String username,
                                          @Param("name") String name,
                                          @Param("surname") String surname,
                                          @Param("email") String email,
                                          @Param("kurumKurulus") KurumKurulus kurumKurulus,
                                          @Param("profileList") List<SecProfile> profileList,
                                          @Param("enabledList") List<Boolean> enabledList);

    Optional<SecUser> findByUsername(String username);

}
