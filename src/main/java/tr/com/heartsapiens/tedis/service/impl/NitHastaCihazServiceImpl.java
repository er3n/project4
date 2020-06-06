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
import tr.com.heartsapiens.tedis.dto.NitHastaCihazDto;
import tr.com.heartsapiens.tedis.entity.*;
import tr.com.heartsapiens.tedis.entity.NitHastaCihaz;
import tr.com.heartsapiens.tedis.repository.*;
import tr.com.heartsapiens.tedis.repository.NitHastaCihazRepository;
import tr.com.heartsapiens.tedis.service.NitHastaCihazService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "nitHastaCihazService")
public class NitHastaCihazServiceImpl implements NitHastaCihazService {

    @Autowired
    NitHastaCihazRepository nitHastaCihazRepository;

    @Autowired
    HastaRepository hastaRepository;

    @Autowired
    KurumKurulusRepository kurumKurulusRepository;

    @Autowired
    TipCihazRepository cihazTipiRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public NitHastaCihazDto getById(Long id) {
        NitHastaCihaz nitHastaCihaz = nitHastaCihazRepository.getOne(id);
        return modelMapper.map(nitHastaCihaz, NitHastaCihazDto.class);
    }

    @Override
    public NitHastaCihazDto save(NitHastaCihazDto entity) {
        NitHastaCihaz dbentity = modelMapper.map(entity, NitHastaCihaz.class);

        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        dbentity.setHasta(hasta);

        KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(entity.getKurumKurulus().getId());
        dbentity.setKurumKurulus(kurumKurulus);

        TipCihaz cihazTipi = cihazTipiRepository.getOne(entity.getCihazTipi().getId());
        dbentity.setCihazTipi(cihazTipi);

        nitHastaCihazRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        nitHastaCihazRepository.deleteById(id);
        return true;
    }

    @Override
    public NitHastaCihazDto update(Long id, NitHastaCihazDto entity) {
        NitHastaCihaz dbentity = nitHastaCihazRepository.getOne(entity.getId());

      
        dbentity.setAciklama(entity.getAciklama());
        dbentity.setBaslangicZamani(entity.getBaslangicZamani());
        dbentity.setSeriNo(entity.getSeriNo());
        dbentity.setSonlandirmaGerekcesi(entity.getSonlandirmaGerekcesi());
        dbentity.setSonlanmaZamani(entity.getSonlanmaZamani());
        dbentity.setDurum(entity.getDurum());
        
        
        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        dbentity.setHasta(hasta);

        KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(entity.getKurumKurulus().getId());
        dbentity.setKurumKurulus(kurumKurulus);

        TipCihaz cihazTipi = cihazTipiRepository.getOne(entity.getCihazTipi().getId());
        dbentity.setCihazTipi(cihazTipi);

        nitHastaCihazRepository.save(dbentity);

        modelMapper.map(dbentity, entity);
        entity.setHastaId(dbentity.getHasta().getId());

        return entity;
    }

    @Override
    public TPage<NitHastaCihazDto> getAll(Pageable pageable) {

        Page<NitHastaCihaz> nitHastaCihazResult = nitHastaCihazRepository.findAll(pageable);

        List<NitHastaCihaz> list = nitHastaCihazResult.getContent();

        NitHastaCihazDto[] nitHastaCihazDtoArray = modelMapper.map(list, NitHastaCihazDto[].class);

        TPage<NitHastaCihazDto> result = new TPage<>(nitHastaCihazResult, nitHastaCihazDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        NitHastaCihaz nitHastaCihaz = nitHastaCihazRepository.getOne(id);
        nitHastaCihaz.setDurum(Boolean.TRUE);
        nitHastaCihaz = nitHastaCihazRepository.save(nitHastaCihaz);

        return true;

    }

    @Override
    public Boolean disable(Long id) {
        NitHastaCihaz nitHastaCihaz = nitHastaCihazRepository.getOne(id);
        nitHastaCihaz.setDurum(Boolean.FALSE);
        nitHastaCihaz = nitHastaCihazRepository.save(nitHastaCihaz);

        return true;
    }

    @Override
    public TPage<NitHastaCihazDto> findByParameters(Pageable pageable,Long hastaId, String pad, String pmodel, String paciklama, Boolean durum) {
        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<NitHastaCihaz> nitHastaCihazResult = nitHastaCihazRepository.findByParameters(pageable,
                hastaId, pad, pmodel, paciklama, durumList);

        List<NitHastaCihaz> list = nitHastaCihazResult.getContent();

        NitHastaCihazDto[] nitHastaCihazDtoArray = modelMapper.map(list, NitHastaCihazDto[].class);

        TPage<NitHastaCihazDto> result = new TPage<>(nitHastaCihazResult, nitHastaCihazDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
