/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.TipOlayDto;

/**
 *
 * @author ersin
 */
public interface TipOlayService extends BaseService<TipOlayDto, Long>{

    TPage<TipOlayDto> findByParameters(Pageable pageable, String kod, String ad, Boolean durum);
}
