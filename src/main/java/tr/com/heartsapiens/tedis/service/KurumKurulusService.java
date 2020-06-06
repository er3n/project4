/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.KurumKurulusDto;

import java.util.List;

/**
 *
 * @author ersin
 */
public interface KurumKurulusService extends BaseService<KurumKurulusDto, Long>{
    TPage<KurumKurulusDto> findByParameters(Pageable pageable,
                                            String kod,
                                            String ad,
                                            String adres,
                                            String eposta,
                                            String tel,
                                            Boolean durum);
}
