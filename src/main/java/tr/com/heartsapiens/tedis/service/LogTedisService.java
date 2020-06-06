/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.LogTedisDto;
import tr.com.heartsapiens.tedis.entity.LogTedis;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ersin
 */
public interface LogTedisService extends BaseService<LogTedisDto, Long> {

    public TPage<LogTedisDto> findByParameters(
            Pageable var,
            Long hastaId,
            String methodName,
            Date basZaman,
            Date bitZaman,
            String tip,
            Long minSure,
            Long maxSure,
            String ip,
            String kullanici,
            String detay

    );
}
