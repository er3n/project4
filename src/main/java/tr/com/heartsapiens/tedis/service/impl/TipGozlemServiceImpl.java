/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.heartsapiens.tedis.dto.TipGozlemDto;
import tr.com.heartsapiens.tedis.entity.TipGozlem;
import tr.com.heartsapiens.tedis.repository.TipGozlemRepository;
import tr.com.heartsapiens.tedis.service.TPage;
import tr.com.heartsapiens.tedis.service.TipGozlemService;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "tipGozlemService")
public class TipGozlemServiceImpl  implements TipGozlemService{
    
    
    @Autowired
    TipGozlemRepository tipGozlemRepository;
    
     @Autowired
    ModelMapper modelMapper;

    @Override
    public TipGozlemDto getById(Long id) {
          TipGozlem tipGozlem = tipGozlemRepository.getOne(id);
        return modelMapper.map(tipGozlem, TipGozlemDto.class);
    }

    @Override
    public TipGozlemDto save(TipGozlemDto entity) {
     TipGozlem dbentity = modelMapper.map(entity, TipGozlem.class);
        tipGozlemRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        tipGozlemRepository.deleteById(id);
        return true;
    }

    @Override
    public TipGozlemDto update(Long id, TipGozlemDto entity) {
        TipGozlem dbentity =  tipGozlemRepository.getOne(entity.getId());
     
        dbentity.setAciklama(entity.getAciklama());
        dbentity.setAd(entity.getAd());
        dbentity.setVeriTip(entity.getVeriTip());
        dbentity.setCheckjs(entity.getCheckJs());
        dbentity.setDurum(entity.getDurum());
        
       tipGozlemRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
       return entity;
    }

    @Override
    public TPage<TipGozlemDto> getAll(Pageable pageable) {

        Page<TipGozlem> tipGozlemResult = tipGozlemRepository.findAll(pageable);

        List<TipGozlem> list = tipGozlemResult.getContent();

        TipGozlemDto[] tipGozlemDtoArray = modelMapper.map(list, TipGozlemDto[].class);

        TPage<TipGozlemDto> result = new TPage<>(tipGozlemResult, tipGozlemDtoArray);

        return result;

    }

     @Override
    public Boolean enable(Long id) {

        TipGozlem tipGozlem = tipGozlemRepository.getOne(id);
        tipGozlem.setDurum(Boolean.TRUE);
        tipGozlem = tipGozlemRepository.save(tipGozlem);
        
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        TipGozlem tipGozlem = tipGozlemRepository.getOne(id);
        tipGozlem.setDurum(Boolean.FALSE);
        tipGozlem = tipGozlemRepository.save(tipGozlem);
        
        return true;
    }

    @Override
    public TPage<TipGozlemDto> findByParameters(Pageable var, String ad, String veritip, String aciklama, Boolean durum) {
        long a = System.currentTimeMillis();

        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<TipGozlem> secProfileResult = tipGozlemRepository.findByParameters(var, ad, veritip, aciklama, durumList);

        List<TipGozlem> list = secProfileResult.getContent();

        TipGozlemDto[] secProfileDtoArray = modelMapper.map(list, TipGozlemDto[].class);

        TPage<TipGozlemDto> result = new TPage<>(secProfileResult, secProfileDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
