/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.SecProfileDto;
import tr.com.heartsapiens.tedis.entity.LogTedis;
import tr.com.heartsapiens.tedis.entity.SecProfile;

/**
 *
 * @author ersin
 */
public interface SecProfileService extends BaseService<SecProfileDto, Long>{

    public TPage<SecProfileDto> findByParameters(
            Pageable var,
            String name,
            String aciklama,
            Boolean durum
    );
}
