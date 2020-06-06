/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.heartsapiens.tedis.dto.LogTedisDto;
import tr.com.heartsapiens.tedis.entity.LogTedis;
import tr.com.heartsapiens.tedis.repository.LogTedisRepository;
import tr.com.heartsapiens.tedis.service.LogTedisService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "logTedisService")
public class LogTedisServiceImpl implements LogTedisService {

    @Autowired
    LogTedisRepository logTedisRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public LogTedisDto getById(Long id) {
        LogTedis logTedis = logTedisRepository.getOne(id);
        return modelMapper.map(logTedis, LogTedisDto.class);
    }

    @Override
    public LogTedisDto save(LogTedisDto entity) {
        LogTedis dbentity = modelMapper.map(entity, LogTedis.class);
        logTedisRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
         logTedisRepository.deleteById(id);
        return true;
    }

    @Override
    public LogTedisDto update(Long id, LogTedisDto entity) {
        LogTedis dbentity =  logTedisRepository.getOne(entity.getId());


        dbentity.setExceptionString(entity.getExceptionString());
        dbentity.setMethodName(entity.getMethodName());
        dbentity.setOpDuration(entity.getOpDuration());
        dbentity.setOpTime(entity.getOpTime());
        dbentity.setParameters(entity.getParameters());
        dbentity.setRemoteIp(entity.getRemoteIp());
        dbentity.setResult(entity.getResult());
        dbentity.setUser(entity.getUser());

        
        
       logTedisRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
       return entity;
    }

    @Override
    public TPage<LogTedisDto> getAll(Pageable pageable) {

        long a = System.currentTimeMillis();

        Page<LogTedis> logTedisResult = logTedisRepository.findAllByOrderByIdDesc(pageable);

        List<LogTedis> list = logTedisResult.getContent();

        LogTedisDto[] logTedisDtoArray = modelMapper.map(list, LogTedisDto[].class);

        TPage<LogTedisDto> result = new TPage<>(logTedisResult, logTedisDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b-a);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        LogTedis logTedis = logTedisRepository.getOne(id);
         logTedisRepository.save(logTedis);
        return true;

    }

    @Override
    public Boolean disable(Long id) {
       LogTedis logTedis = logTedisRepository.getOne(id);
         logTedisRepository.save(logTedis);
        return true;
    }



    @Override
    public  TPage<LogTedisDto> findByParameters(Pageable pageable,Long hastaId, String methodName, Date basZaman, Date bitZaman, String tip, Long minSure, Long maxSure, String ip, String kullanici, String detay) {
        long a = System.currentTimeMillis();

        Page<LogTedis> logTedisResult = logTedisRepository.findByParameters(
                pageable,hastaId,methodName, basZaman,bitZaman, tip,minSure,maxSure,ip,kullanici,detay);

        List<LogTedis> list = logTedisResult.getContent();

        LogTedisDto[] logTedisDtoArray = modelMapper.map(list, LogTedisDto[].class);

        TPage<LogTedisDto> result = new TPage<>(logTedisResult, logTedisDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b-a);

        return result;
    }
}
