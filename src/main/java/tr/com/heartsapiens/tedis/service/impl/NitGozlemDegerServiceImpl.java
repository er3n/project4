/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.heartsapiens.tedis.dto.NitGozlemDegerDto;
import tr.com.heartsapiens.tedis.entity.Hasta;
import tr.com.heartsapiens.tedis.entity.NitGozlemDeger;
import tr.com.heartsapiens.tedis.entity.TipGozlem;
import tr.com.heartsapiens.tedis.repository.HastaRepository;
import tr.com.heartsapiens.tedis.repository.NitGozlemDegerRepository;
import tr.com.heartsapiens.tedis.repository.TipGozlemRepository;
import tr.com.heartsapiens.tedis.service.NitGozlemDegerService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "nitGozlemDegerService")
public class NitGozlemDegerServiceImpl implements NitGozlemDegerService {

    @Autowired
    NitGozlemDegerRepository nitGozlemDegerRepository;

    @Autowired
    HastaRepository hastaRepository;
    
    @Autowired
    TipGozlemRepository tipGozlemRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public NitGozlemDegerDto getById(Long id) {
        NitGozlemDeger nitGozlemDeger = nitGozlemDegerRepository.getOne(id);
        return modelMapper.map(nitGozlemDeger, NitGozlemDegerDto.class);
    }

    @Override
    public NitGozlemDegerDto save(NitGozlemDegerDto entity) {
        NitGozlemDeger dbentity = modelMapper.map(entity, NitGozlemDeger.class);

        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        dbentity.setHasta(hasta);

        nitGozlemDegerRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }


    @Override
    public Boolean delete(Long id) {
        nitGozlemDegerRepository.deleteById(id);
        return true;
    }

    @Override
    public NitGozlemDegerDto update(Long id, NitGozlemDegerDto entity) {
        NitGozlemDeger dbentity = nitGozlemDegerRepository.getOne(entity.getId());
        
        
                
         
        dbentity.setBlnDeger(entity.getBlnDeger());
        dbentity.setDblDeger(entity.getDblDeger()); 
        dbentity.setGozlemZamani(entity.getGozlemZamani());
        dbentity.setLngDeger(entity.getLngDeger());
        dbentity.setStrDeger(entity.getStrDeger());
        dbentity.setTmeDeger(entity.getTimeDeger());
        dbentity.setDurum(entity.getDurum());
        
        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        TipGozlem gozlem = tipGozlemRepository.getOne(entity.getGozlemTipi().getId());
        dbentity.setHasta(hasta);
        dbentity.setGozlemTipi(gozlem);

        
        nitGozlemDegerRepository.save(dbentity);
        
        
       entity= modelMapper.map(dbentity, NitGozlemDegerDto.class);
        entity.setHastaId(dbentity.getHasta().getId());

        return entity;
    }

    @Override
    public TPage<NitGozlemDegerDto> getAll(Pageable pageable) {

        Page<NitGozlemDeger> nitGozlemDegerResult = nitGozlemDegerRepository.findAll(pageable);

        List<NitGozlemDeger> list = nitGozlemDegerResult.getContent();

        NitGozlemDegerDto[] nitGozlemDegerDtoArray = modelMapper.map(list, NitGozlemDegerDto[].class);

        TPage<NitGozlemDegerDto> result = new TPage<>(nitGozlemDegerResult, nitGozlemDegerDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        NitGozlemDeger nitGozlemDeger = nitGozlemDegerRepository.getOne(id);
        nitGozlemDeger.setDurum(Boolean.TRUE);
        nitGozlemDeger = nitGozlemDegerRepository.save(nitGozlemDeger);

        return true;

    }

    @Override
    public Boolean disable(Long id) {
        NitGozlemDeger nitGozlemDeger = nitGozlemDegerRepository.getOne(id);
        nitGozlemDeger.setDurum(Boolean.FALSE);
        nitGozlemDeger = nitGozlemDegerRepository.save(nitGozlemDeger);

        return true;
    }



    @Override
    public TPage<NitGozlemDegerDto> findByParameters(
            Pageable pageable, Long hastaId, String pad, Date minZaman, Date maxZaman, Boolean durum) {
        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<NitGozlemDeger> nitGozlemDegerResult = nitGozlemDegerRepository.findByParameters(pageable, hastaId,
                pad, minZaman, maxZaman,durumList);

        List<NitGozlemDeger> list = nitGozlemDegerResult.getContent();

        NitGozlemDegerDto[] nitGozlemDegerDtoArray = modelMapper.map(list, NitGozlemDegerDto[].class);

        TPage<NitGozlemDegerDto> result = new TPage<>(nitGozlemDegerResult, nitGozlemDegerDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }

}
