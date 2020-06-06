/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.NitHastaKomplikasyonDto;

import java.util.Date;

/**
 *
 * @author ersin
 */
public interface NitHastaKomplikasyonService extends BaseService< NitHastaKomplikasyonDto, Long>{

    TPage<NitHastaKomplikasyonDto> findByParameters(
            Pageable pageable, Long hastaId, String polayAd,
            String polayAciklama,
            String ptedaviSonucAd, Date dtMinZaman, Date dtMaxZaman, Boolean durum);
}
