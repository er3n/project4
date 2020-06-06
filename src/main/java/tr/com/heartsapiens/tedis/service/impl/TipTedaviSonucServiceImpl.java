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
import tr.com.heartsapiens.tedis.dto.TipTedaviSonucDto;
import tr.com.heartsapiens.tedis.entity.TipTedaviSonuc;
import tr.com.heartsapiens.tedis.repository.TipTedaviSonucRepository;
import tr.com.heartsapiens.tedis.service.TPage;
import tr.com.heartsapiens.tedis.service.TipTedaviSonucService;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "tipTedaviSonucService")
public class TipTedaviSonucServiceImpl implements TipTedaviSonucService {

    @Autowired
    TipTedaviSonucRepository tipTedaviSonucRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public TipTedaviSonucDto getById(Long id) {
        TipTedaviSonuc tipTedaviSonuc = tipTedaviSonucRepository.getOne(id);
        return modelMapper.map(tipTedaviSonuc, TipTedaviSonucDto.class);
    }

    @Override
    public TipTedaviSonucDto save(TipTedaviSonucDto entity) {
        TipTedaviSonuc dbentity = modelMapper.map(entity, TipTedaviSonuc.class);
        tipTedaviSonucRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        tipTedaviSonucRepository.deleteById(id);
        return true;
    }

    @Override
    public TipTedaviSonucDto update(Long id, TipTedaviSonucDto entity) {
        TipTedaviSonuc dbentity = tipTedaviSonucRepository.getOne(entity.getId());
        dbentity.setAd(entity.getAd());

        dbentity.setCihazSonlandirir(entity.isCihazSonlandirir());
        dbentity.setHastaPasifEder(entity.isHastaPasifEder());

        dbentity.setKod(entity.getKod());
        dbentity.setDurum(entity.getDurum());

        tipTedaviSonucRepository.save(dbentity);

        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public TPage<TipTedaviSonucDto> getAll(Pageable pageable) {

        Page<TipTedaviSonuc> tipTedaviSonucResult = tipTedaviSonucRepository.findAll(pageable);

        List<TipTedaviSonuc> list = tipTedaviSonucResult.getContent();

        TipTedaviSonucDto[] tipTedaviSonucDtoArray = modelMapper.map(list, TipTedaviSonucDto[].class);

        TPage<TipTedaviSonucDto> result = new TPage<>(tipTedaviSonucResult, tipTedaviSonucDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        TipTedaviSonuc tipTedaviSonuc = tipTedaviSonucRepository.getOne(id);
        tipTedaviSonuc.setDurum(Boolean.TRUE);
        tipTedaviSonucRepository.save(tipTedaviSonuc);
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        TipTedaviSonuc tipTedaviSonuc = tipTedaviSonucRepository.getOne(id);
        tipTedaviSonuc.setDurum(Boolean.FALSE);
        tipTedaviSonucRepository.save(tipTedaviSonuc);
        return true;
    }


    @Override
    public TPage<TipTedaviSonucDto> findByParameters(Pageable var, String kod, String ad, Boolean durum) {
        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<TipTedaviSonuc> tipTedaviSonucResult = tipTedaviSonucRepository.findByParameters(var, kod, ad, durumList);

        List<TipTedaviSonuc> list = tipTedaviSonucResult.getContent();

        TipTedaviSonucDto[] tipTedaviSonucDtoArray = modelMapper.map(list, TipTedaviSonucDto[].class);

        TPage<TipTedaviSonucDto> result = new TPage<>(tipTedaviSonucResult, tipTedaviSonucDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }

}
