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
import tr.com.heartsapiens.tedis.dto.TipTedaviDto;
import tr.com.heartsapiens.tedis.entity.TipTedavi;
import tr.com.heartsapiens.tedis.repository.TipTedaviRepository;
import tr.com.heartsapiens.tedis.service.TPage;
import tr.com.heartsapiens.tedis.service.TipTedaviService;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "tipTedaviService")
public class TipTedaviServiceImpl implements TipTedaviService {

    @Autowired
    TipTedaviRepository tipTedaviRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public TipTedaviDto getById(Long id) {
        TipTedavi tipTedavi = tipTedaviRepository.getOne(id);
        return modelMapper.map(tipTedavi, TipTedaviDto.class);
    }

    @Override
    public TipTedaviDto save(TipTedaviDto entity) {
        TipTedavi dbentity = modelMapper.map(entity, TipTedavi.class);
        tipTedaviRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
          tipTedaviRepository.deleteById(id);
        return true;
    }

    @Override
    public TipTedaviDto update(Long id, TipTedaviDto entity) {
       TipTedavi dbentity =  tipTedaviRepository.getOne(entity.getId());
       dbentity.setAd(entity.getAd());
       dbentity.setKod(entity.getKod());
       dbentity.setDurum(entity.getDurum());
 
       tipTedaviRepository.save(dbentity);
       
        modelMapper.map(dbentity, entity);
       return entity;
    }

     @Override
    public TPage<TipTedaviDto> getAll(Pageable pageable) {

        Page<TipTedavi> tipTedaviResult = tipTedaviRepository.findAll(pageable);

         List<TipTedavi> list = tipTedaviResult.getContent();

        TipTedaviDto[] tipTedaviDtoArray = modelMapper.map(list, TipTedaviDto[].class);

        TPage<TipTedaviDto> result = new TPage<>(tipTedaviResult, tipTedaviDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        TipTedavi tipTedavi = tipTedaviRepository.getOne(id);
        tipTedavi.setDurum(Boolean.TRUE);
        tipTedavi = tipTedaviRepository.save(tipTedavi);
        
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        TipTedavi tipTedavi = tipTedaviRepository.getOne(id);
        tipTedavi.setDurum(Boolean.FALSE);
        tipTedavi = tipTedaviRepository.save(tipTedavi);
        
        return true;
    }

    @Override
    public TPage<TipTedaviDto> findByParameters(Pageable var, String kod, String ad, Boolean durum) {
        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<TipTedavi> tipTedaviResult = tipTedaviRepository.findByParameters(var, kod, ad, durumList);

        List<TipTedavi> list = tipTedaviResult.getContent();

        TipTedaviDto[] tipTedaviDtoArray = modelMapper.map(list, TipTedaviDto[].class);

        TPage<TipTedaviDto> result = new TPage<>(tipTedaviResult, tipTedaviDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
