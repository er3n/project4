/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.heartsapiens.tedis.dto.NitHastaKomplikasyonDto;
import tr.com.heartsapiens.tedis.dto.NitKompliksyonTedaviDto;
import tr.com.heartsapiens.tedis.dto.TipTedaviDto;
import tr.com.heartsapiens.tedis.entity.NitHastaKomplikasyon;
import tr.com.heartsapiens.tedis.entity.NitKomplikasyonTedavi;
import tr.com.heartsapiens.tedis.entity.TipTedavi;
import tr.com.heartsapiens.tedis.repository.NitHastaKomplikasyonRepository;
import tr.com.heartsapiens.tedis.repository.NitKompliksyonTedaviRepository;
import tr.com.heartsapiens.tedis.repository.TipTedaviRepository;
import tr.com.heartsapiens.tedis.service.NitHastaKomplikasyonService;
import tr.com.heartsapiens.tedis.service.NitKompliksyonTedaviService;
import tr.com.heartsapiens.tedis.service.TPage;
import tr.com.heartsapiens.tedis.service.TipTedaviService;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "nitKompliksyonTedaviServiceImpl")
public class NitKompliksyonTedaviServiceImpl implements NitKompliksyonTedaviService {

    @Autowired
    NitKompliksyonTedaviRepository nitKomplikasyonTedaviRepository;

    @Autowired
    NitHastaKomplikasyonService nitHastaKomplikasyonService;

    @Autowired
    TipTedaviService tipTedaviService;

    @Autowired
    NitHastaKomplikasyonRepository nitHastaKomplikasyonRepository;

    @Autowired
    TipTedaviRepository tipTedaviRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public NitKompliksyonTedaviDto getById(Long id) {
        NitKomplikasyonTedavi nitKomplikasyonTedavi = nitKomplikasyonTedaviRepository.getOne(id);

        NitHastaKomplikasyonDto hastaKomplikasyonDto = nitHastaKomplikasyonService.getById(nitKomplikasyonTedavi.getNitHastaKomplikasyon().getId());
        TipTedaviDto tipTedavi = tipTedaviService.getById(nitKomplikasyonTedavi.getTipTedavi().getId());

        NitKompliksyonTedaviDto result = new NitKompliksyonTedaviDto();
        result.setId(id);
        result.setTedaviAciklama(nitKomplikasyonTedavi.getTedaviAciklama());

        result.setNitHastaKomplikasyon(hastaKomplikasyonDto);
        result.setTipTedavi(tipTedavi);

        return result;
    }

    @Override
    public NitKompliksyonTedaviDto save(NitKompliksyonTedaviDto entity) {
        NitKomplikasyonTedavi nitKomplikasyonTedavi = new NitKomplikasyonTedavi();
        nitKomplikasyonTedavi.setTedaviAciklama(entity.getTedaviAciklama());

        NitHastaKomplikasyon hastaKomplikasyon = nitHastaKomplikasyonRepository.getOne(entity.getNitHastaKomplikasyon().getId());
        TipTedavi tipTedavi = tipTedaviRepository.getOne(entity.getTipTedavi().getId());

        nitKomplikasyonTedavi.setNitHastaKomplikasyon(hastaKomplikasyon);
        nitKomplikasyonTedavi.setTipTedavi(tipTedavi);

        nitKomplikasyonTedaviRepository.save(nitKomplikasyonTedavi);
        modelMapper.map(nitKomplikasyonTedavi, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        nitKomplikasyonTedaviRepository.deleteById(id);
        return true;
    }

    @Override
    public NitKompliksyonTedaviDto update(Long id, NitKompliksyonTedaviDto entity) {
        NitKomplikasyonTedavi dbentity = nitKomplikasyonTedaviRepository.getOne(entity.getId());

        dbentity.setTedaviAciklama(entity.getTedaviAciklama());

        NitHastaKomplikasyon hastaKomplikasyon = nitHastaKomplikasyonRepository.getOne(entity.getNitHastaKomplikasyon().getId());
        TipTedavi tipTedavi = tipTedaviRepository.getOne(entity.getTipTedavi().getId());

        dbentity.setNitHastaKomplikasyon(hastaKomplikasyon);
        dbentity.setTipTedavi(tipTedavi);
        dbentity.setDurum(entity.getDurum());

        nitKomplikasyonTedaviRepository.save(dbentity);

        entity.setNitHastaKomplikasyon(nitHastaKomplikasyonService.getById(dbentity.getNitHastaKomplikasyon().getId()));
        entity.setTipTedavi(tipTedaviService.getById(dbentity.getTipTedavi().getId()));

        return entity;

    }

    @Override
    public TPage<NitKompliksyonTedaviDto> getAll(Pageable pageable) {

        Page<NitKomplikasyonTedavi> nitKomplikasyonTedaviResult = nitKomplikasyonTedaviRepository.findAll(pageable);

        List<NitKomplikasyonTedavi> list = nitKomplikasyonTedaviResult.getContent();

        NitKompliksyonTedaviDto[] nitKomplikasyonTedaviDtoArray = modelMapper.map(list, NitKompliksyonTedaviDto[].class);

        TPage<NitKompliksyonTedaviDto> result = new TPage<>(nitKomplikasyonTedaviResult, nitKomplikasyonTedaviDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        NitKomplikasyonTedavi nitKomplikasyonTedavi = nitKomplikasyonTedaviRepository.getOne(id);
        nitKomplikasyonTedavi.setDurum(Boolean.TRUE);
        nitKomplikasyonTedavi = nitKomplikasyonTedaviRepository.save(nitKomplikasyonTedavi);

        return true;

    }

    @Override
    public Boolean disable(Long id) {
        NitKomplikasyonTedavi nitKomplikasyonTedavi = nitKomplikasyonTedaviRepository.getOne(id);
        nitKomplikasyonTedavi.setDurum(Boolean.FALSE);
        nitKomplikasyonTedavi = nitKomplikasyonTedaviRepository.save(nitKomplikasyonTedavi);

        return true;
    }
}
