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
import tr.com.heartsapiens.tedis.dto.TipCihazDto;
import tr.com.heartsapiens.tedis.entity.TipCihaz;
import tr.com.heartsapiens.tedis.repository.TipCihazRepository;
import tr.com.heartsapiens.tedis.service.TPage;
import tr.com.heartsapiens.tedis.service.TipCihazService;

/**
 * @author ersin
 */
@Transactional
@Service(value = "tipCihazService")
public class TipCihazServiceImpl implements TipCihazService {

    @Autowired
    TipCihazRepository tipCihazRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public TipCihazDto getById(Long id) {
        TipCihaz tipCihaz = tipCihazRepository.getOne(id);
        return modelMapper.map(tipCihaz, TipCihazDto.class);
    }

    @Override
    public TipCihazDto save(TipCihazDto entity) {
        TipCihaz dbentity = modelMapper.map(entity, TipCihaz.class);
        tipCihazRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        tipCihazRepository.deleteById(id);
        return true;
    }

    @Override
    public TipCihazDto update(Long id, TipCihazDto entity) {
        TipCihaz dbentity = tipCihazRepository.getOne(entity.getId());

        dbentity.setAciklama(entity.getAciklama());
        dbentity.setAd(entity.getAd());
        dbentity.setModel(entity.getModel());
        dbentity.setDurum(entity.getDurum());

        tipCihazRepository.save(dbentity);

        modelMapper.map(dbentity, entity);
        return entity;

    }

    @Override
    public TPage<TipCihazDto> getAll(Pageable pageable) {

        Page<TipCihaz> tipCihazResult = tipCihazRepository.findAll(pageable);

        List<TipCihaz> list = tipCihazResult.getContent();

        TipCihazDto[] tipCihazDtoArray = modelMapper.map(list, TipCihazDto[].class);

        TPage<TipCihazDto> result = new TPage<>(tipCihazResult, tipCihazDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        TipCihaz tipCihaz = tipCihazRepository.getOne(id);
        tipCihaz.setDurum(Boolean.TRUE);
        tipCihaz = tipCihazRepository.save(tipCihaz);

        return true;

    }

    @Override
    public Boolean disable(Long id) {
        TipCihaz tipCihaz = tipCihazRepository.getOne(id);
        tipCihaz.setDurum(Boolean.FALSE);
        tipCihaz = tipCihazRepository.save(tipCihaz);

        return true;
    }

    @Override
    public TPage<TipCihazDto> findByParameters(Pageable var, String ad, String model, String aciklama, Boolean durum) {
        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<TipCihaz> secProfileResult = tipCihazRepository.findByParameters(var, ad, model, aciklama, durumList);

        List<TipCihaz> list = secProfileResult.getContent();

        TipCihazDto[] secProfileDtoArray = modelMapper.map(list, TipCihazDto[].class);

        TPage<TipCihazDto> result = new TPage<>(secProfileResult, secProfileDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
