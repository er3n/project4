/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.TipCihazDto;

/**
 *
 * @author ersin
 */
public interface TipCihazService extends BaseService<TipCihazDto, Long>{

    TPage<TipCihazDto> findByParameters(Pageable pageable, String pad, String pmodel, String paciklama, Boolean durum);

}
