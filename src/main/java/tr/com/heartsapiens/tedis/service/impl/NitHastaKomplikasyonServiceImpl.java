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
import tr.com.heartsapiens.tedis.dto.NitHastaKomplikasyonDto;
import tr.com.heartsapiens.tedis.entity.Hasta;
import tr.com.heartsapiens.tedis.entity.NitHastaKomplikasyon;
import tr.com.heartsapiens.tedis.entity.TipOlay;
import tr.com.heartsapiens.tedis.entity.TipTedaviSonuc;
import tr.com.heartsapiens.tedis.repository.HastaRepository;
import tr.com.heartsapiens.tedis.repository.NitHastaKomplikasyonRepository;
import tr.com.heartsapiens.tedis.repository.TipOlayRepository;
import tr.com.heartsapiens.tedis.repository.TipTedaviSonucRepository;
import tr.com.heartsapiens.tedis.service.NitHastaKomplikasyonService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "nitHastaKomplikasyonService")
public class NitHastaKomplikasyonServiceImpl implements NitHastaKomplikasyonService {

    @Autowired
    NitHastaKomplikasyonRepository nitHastaKomplikasyonRepository;

    @Autowired
    HastaRepository hastaRepository;

    @Autowired
    TipOlayRepository olayRepository;

    @Autowired
    TipTedaviSonucRepository tedaviSonucRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public NitHastaKomplikasyonDto getById(Long id) {
        NitHastaKomplikasyon nitHastaKomplikasyon = nitHastaKomplikasyonRepository.getOne(id);
        return modelMapper.map(nitHastaKomplikasyon, NitHastaKomplikasyonDto.class);
    }

    @Override
    public NitHastaKomplikasyonDto save(NitHastaKomplikasyonDto entity) {
        NitHastaKomplikasyon dbentity = modelMapper.map(entity, NitHastaKomplikasyon.class);

        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        dbentity.setHasta(hasta);

        TipOlay olay = olayRepository.getOne(entity.getOlay().getId());
        dbentity.setOlay(olay);

        TipTedaviSonuc tipTedaviSonuc = tedaviSonucRepository.getOne(entity.getTedaviSonuc().getId());
        dbentity.setTedaviSonuc(tipTedaviSonuc);

        dbentity = nitHastaKomplikasyonRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        nitHastaKomplikasyonRepository.deleteById(id);
        return true;
    }

    @Override
    public NitHastaKomplikasyonDto update(Long id, NitHastaKomplikasyonDto entity) {
        NitHastaKomplikasyon dbentity = nitHastaKomplikasyonRepository.getOne(entity.getId());

        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        TipOlay olay = olayRepository.getOne(entity.getOlay().getId());
        TipTedaviSonuc tedaviSonuc = tedaviSonucRepository.getOne(entity.getTedaviSonuc().getId());

        dbentity.setHasta(hasta);
        dbentity.setOlay(olay);
        dbentity.setOlayAciklama(entity.getOlayAciklama());
        dbentity.setOlayZaman(entity.getOlayZaman());
        dbentity.setTedaviZaman(entity.getTedaviZaman());
        dbentity.setSonucZaman(entity.getSonucZaman());
        dbentity.setTedaviSonuc(tedaviSonuc);

        nitHastaKomplikasyonRepository.save(dbentity);

        modelMapper.map(dbentity, entity);
        entity.setHastaId(dbentity.getHasta().getId());

        return entity;
    }

    @Override
    public TPage<NitHastaKomplikasyonDto> getAll(Pageable pageable) {

        Page<NitHastaKomplikasyon> nitHastaKomplikasyonResult = nitHastaKomplikasyonRepository.findAll(pageable);

        List<NitHastaKomplikasyon> list = nitHastaKomplikasyonResult.getContent();

        NitHastaKomplikasyonDto[] nitHastaKomplikasyonDtoArray = modelMapper.map(list, NitHastaKomplikasyonDto[].class);

        TPage<NitHastaKomplikasyonDto> result = new TPage<>(nitHastaKomplikasyonResult, nitHastaKomplikasyonDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        NitHastaKomplikasyon nitHastaKomplikasyon = nitHastaKomplikasyonRepository.getOne(id);
        nitHastaKomplikasyon.setDurum(Boolean.TRUE);
        nitHastaKomplikasyon = nitHastaKomplikasyonRepository.save(nitHastaKomplikasyon);

        return true;

    }

    @Override
    public Boolean disable(Long id) {
        NitHastaKomplikasyon nitHastaKomplikasyon = nitHastaKomplikasyonRepository.getOne(id);
        nitHastaKomplikasyon.setDurum(Boolean.FALSE);
        nitHastaKomplikasyon = nitHastaKomplikasyonRepository.save(nitHastaKomplikasyon);

        return true;
    }

    @Override
    public TPage<NitHastaKomplikasyonDto> findByParameters(Pageable pageable,
                                                           Long hastaId, String polayAd, String polayAciklama,
                                                            String ptedaviSonucAd,
                                                           Date dtMinZaman, Date dtMaxZaman, Boolean durum) {
        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<NitHastaKomplikasyon> nitHastaKomplikasyonResult =
                nitHastaKomplikasyonRepository.findByParameters(
                        pageable,
                        hastaId,
                        polayAd,
                        polayAciklama,
                        ptedaviSonucAd,
                        dtMinZaman,
                        dtMaxZaman,
                        durumList
                );

        List<NitHastaKomplikasyon> list = nitHastaKomplikasyonResult.getContent();

        NitHastaKomplikasyonDto[] nitHastaKomplikasyonDtoArray = modelMapper.map(list, NitHastaKomplikasyonDto[].class);

        TPage<NitHastaKomplikasyonDto> result = new TPage<>(nitHastaKomplikasyonResult, nitHastaKomplikasyonDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
