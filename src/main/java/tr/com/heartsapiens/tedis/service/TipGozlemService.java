/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.TipGozlemDto;

/**
 *
 * @author ersin
 */
public interface TipGozlemService extends BaseService<TipGozlemDto, Long>{

    TPage<TipGozlemDto>  findByParameters(Pageable pageable, String pad, String pveritip, String paciklama, Boolean durum);
}
