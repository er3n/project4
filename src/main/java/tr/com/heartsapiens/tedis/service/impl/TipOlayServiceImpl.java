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
import tr.com.heartsapiens.tedis.dto.TipOlayDto;
import tr.com.heartsapiens.tedis.entity.TipOlay;
import tr.com.heartsapiens.tedis.repository.TipOlayRepository;
import tr.com.heartsapiens.tedis.service.TPage;
import tr.com.heartsapiens.tedis.service.TipOlayService;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "tipOlayService")
public class TipOlayServiceImpl implements TipOlayService {

    @Autowired
    TipOlayRepository tipOlayRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public TipOlayDto getById(Long id) {
        TipOlay tipOlay = tipOlayRepository.getOne(id);
        return modelMapper.map(tipOlay, TipOlayDto.class);
    }

    @Override
    public TipOlayDto save(TipOlayDto entity) {
        TipOlay dbentity = modelMapper.map(entity, TipOlay.class);
        tipOlayRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
         tipOlayRepository.deleteById(id);
        return true;
    }

    @Override
    public TipOlayDto update(Long id, TipOlayDto entity) {
       TipOlay dbentity =  tipOlayRepository.getOne(entity.getId());
     
       dbentity.setAd(entity.getAd());
       dbentity.setKod(entity.getKod());
        dbentity.setDurum(entity.getDurum());

       tipOlayRepository.save(dbentity);
       
        modelMapper.map(dbentity, entity);
       return entity;
    }

    @Override
    public TPage<TipOlayDto> getAll(Pageable pageable) {

        Page<TipOlay> tipOlayResult = tipOlayRepository.findAll(pageable);

        List<TipOlay> list = tipOlayResult.getContent();

        TipOlayDto[] tipOlayDtoArray = modelMapper.map(list, TipOlayDto[].class);

        TPage<TipOlayDto> result = new TPage<>(tipOlayResult, tipOlayDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        TipOlay tipOlay = tipOlayRepository.getOne(id);
        tipOlay.setDurum(Boolean.TRUE);
        tipOlay = tipOlayRepository.save(tipOlay);
        
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        TipOlay tipOlay = tipOlayRepository.getOne(id);
        tipOlay.setDurum(Boolean.FALSE);
        tipOlay = tipOlayRepository.save(tipOlay);
        
        return true;
    }

    @Override
    public TPage<TipOlayDto> findByParameters(Pageable var, String kod, String ad, Boolean durum) {
        long a = System.currentTimeMillis();

        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<TipOlay> secProfileResult = tipOlayRepository.findByParameters(var, kod, ad, durumList);

        List<TipOlay> list = secProfileResult.getContent();

        TipOlayDto[] secProfileDtoArray = modelMapper.map(list, TipOlayDto[].class);

        TPage<TipOlayDto> result = new TPage<>(secProfileResult, secProfileDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
