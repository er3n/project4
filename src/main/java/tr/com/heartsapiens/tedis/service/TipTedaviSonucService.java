/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.TipTedaviSonucDto;

/**
 *
 * @author ersin
 */
public interface TipTedaviSonucService extends BaseService<TipTedaviSonucDto, Long>{

    public TPage<TipTedaviSonucDto>  findByParameters(
            Pageable pageable,
            String kod,
            String ad,
            Boolean durum
    );
}
