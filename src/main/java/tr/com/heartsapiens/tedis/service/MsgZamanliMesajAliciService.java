/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajAliciDto;

/**
 *
 * @author ersin
 */
public interface MsgZamanliMesajAliciService extends BaseService<MsgZamanliMesajAliciDto, Long>{

    TPage<MsgZamanliMesajAliciDto> findByParameters(Pageable pageable,
                                                    Long hastaId,
                                                    String msgBaslik,
                                                    String msgIcerik,
                                                    String pattern,
                                                    Boolean durum);
}
