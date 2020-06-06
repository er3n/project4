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
import tr.com.heartsapiens.tedis.dto.MsgHastaMesajDto;
import tr.com.heartsapiens.tedis.entity.Hasta;
import tr.com.heartsapiens.tedis.entity.MsgHastaMesaj;
import tr.com.heartsapiens.tedis.repository.HastaRepository;
import tr.com.heartsapiens.tedis.repository.MsgHastaMesajRepository;
import tr.com.heartsapiens.tedis.service.MsgHastaMesajService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "msgHastaMesajService")
public class MsgHastaMesajServiceImpl implements MsgHastaMesajService {

    @Autowired
    MsgHastaMesajRepository msgHastaMesajRepository;

    @Autowired
    HastaRepository hastaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MsgHastaMesajDto getById(Long id) {
        MsgHastaMesaj msgHastaMesaj = msgHastaMesajRepository.getOne(id);
        return modelMapper.map(msgHastaMesaj, MsgHastaMesajDto.class);
    }

    @Override
    public MsgHastaMesajDto save(MsgHastaMesajDto entity) {
      
        MsgHastaMesaj dbentity =  modelMapper.map(entity, MsgHastaMesaj.class);

        
        Hasta hasta = hastaRepository.getOne(entity.getHasta().getId());
        dbentity.setHasta(hasta);
        

        msgHastaMesajRepository.save(dbentity);

        modelMapper.map(dbentity, entity);

        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        msgHastaMesajRepository.deleteById(id);
        return true;
    }

    @Override
    public MsgHastaMesajDto update(Long id, MsgHastaMesajDto entity) {
        MsgHastaMesaj dbentity = msgHastaMesajRepository.getOne(id);

        dbentity.setBasGonderiZamani(entity.getBasGonderiZamani());
        dbentity.setBaslik(entity.getBaslik()); 
        dbentity.setGerceklesmeZamani(entity.getGerceklesmeZamani());

        Hasta hasta = hastaRepository.getOne(entity.getHasta().getId());
        dbentity.setHasta(hasta);
        dbentity.setIcerik(entity.getIcerik());

        dbentity.setCommStatus(entity.getCommStatus());

        dbentity.setSonGonderiZamani(entity.getSonGonderiZamani());
        dbentity.setDurum(entity.getDurum());

        msgHastaMesajRepository.save(dbentity);

        modelMapper.map(dbentity, entity);

        return entity;
    }

    @Override
    public TPage<MsgHastaMesajDto> getAll(Pageable pageable) {

        Page<MsgHastaMesaj> msgHastaMesajResult = msgHastaMesajRepository.findAll(pageable);

        List<MsgHastaMesaj> list = msgHastaMesajResult.getContent();

        MsgHastaMesajDto[] msgHastaMesajDtoArray = modelMapper.map(list, MsgHastaMesajDto[].class);

        TPage<MsgHastaMesajDto> result = new TPage<>(msgHastaMesajResult, msgHastaMesajDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        MsgHastaMesaj msgHastaMesaj = msgHastaMesajRepository.getOne(id);
        msgHastaMesaj.setDurum(Boolean.TRUE);
        msgHastaMesajRepository.save(msgHastaMesaj);
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        MsgHastaMesaj msgHastaMesaj = msgHastaMesajRepository.getOne(id);
        msgHastaMesaj.setDurum(Boolean.FALSE);
        msgHastaMesajRepository.save(msgHastaMesaj);
        return true;
    }

    @Override
    public TPage<MsgHastaMesajDto> findByParameters(Pageable var, Long hastaId, String pbaslik, String picerik, Date dtBasGonZaman,
                                                    Date dtSonGonZaman,   String commStatus, Boolean durum) {
        long a = System.currentTimeMillis();

        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<MsgHastaMesaj> msgHastaMesajResult = msgHastaMesajRepository.findByParameters(var,
                 hastaId,
                pbaslik, picerik,
                dtBasGonZaman,
                dtSonGonZaman,
                commStatus, durumList);

        List<MsgHastaMesaj> list = msgHastaMesajResult.getContent();

        MsgHastaMesajDto[] msgHastaMesajDtoArray = modelMapper.map(list, MsgHastaMesajDto[].class);

        TPage<MsgHastaMesajDto> result = new TPage<>(msgHastaMesajResult, msgHastaMesajDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
