/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.MsgHastaMesajDto;

import java.util.Date;

/**
 *
 * @author ersin
 */
public interface MsgHastaMesajService extends BaseService<MsgHastaMesajDto, Long>{

    TPage<MsgHastaMesajDto> findByParameters(
            Pageable pageable,
            Long hastaId,
            String pbaslik, String picerik,
            Date dtBasGonZaman, Date dtSonGonZaman,
            String commStatus,Boolean durum);
}
