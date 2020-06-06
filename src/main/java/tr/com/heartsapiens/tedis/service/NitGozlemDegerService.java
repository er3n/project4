/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.NitGozlemDegerDto;

import java.util.Date;

/**
 *
 * @author ersin
 */
public interface NitGozlemDegerService extends BaseService<NitGozlemDegerDto, Long>{

    public TPage<NitGozlemDegerDto> findByParameters(
            Pageable pageable, Long hastaId, String pad, Date minZaman, Date maxZaman, Boolean durum);
}
