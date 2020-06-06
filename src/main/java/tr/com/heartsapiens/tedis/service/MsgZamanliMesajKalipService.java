/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajKalipDto;
import tr.com.heartsapiens.tedis.entity.MsgZamanliMesajKalip;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ersin
 */
public interface MsgZamanliMesajKalipService extends BaseService<MsgZamanliMesajKalipDto, Long>{

    TPage<MsgZamanliMesajKalipDto> findByParameters(

            Pageable var,
            String pbaslik,
            String picerik,
            Date dtBasGonZaman,
            Date dtSonGonZaman,
            String ayDowGunSaatDakikaPattern,
            Boolean durum);
}
