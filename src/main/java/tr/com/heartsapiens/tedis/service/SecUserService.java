/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import tr.com.heartsapiens.tedis.dto.KurumKurulusDto;
import tr.com.heartsapiens.tedis.dto.SecProfileDto;
import tr.com.heartsapiens.tedis.dto.SecUserDto;

/**
 *
 * @author ersin
 */
public interface SecUserService extends BaseService< SecUserDto, Long>, UserDetailsService {

    public TPage<SecUserDto> findByParameters(Pageable pageable,
                                              String pusername, String pname, String psurname, String pemail,
                                              Long kurumKurulusId,
                                              Long[] profileList,
                                              Boolean durum);
}
