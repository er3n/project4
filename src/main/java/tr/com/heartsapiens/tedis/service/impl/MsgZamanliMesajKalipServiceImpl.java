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
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajKalipDto;
import tr.com.heartsapiens.tedis.entity.MsgZamanliMesajKalip;
import tr.com.heartsapiens.tedis.repository.MsgZamanliMesajKalipRepository;
import tr.com.heartsapiens.tedis.service.MsgZamanliMesajKalipService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 * @author ersin
 */
@Transactional
@Service(value = "msgZamanliMesajKalipService")
public class MsgZamanliMesajKalipServiceImpl implements MsgZamanliMesajKalipService {

    @Autowired
    MsgZamanliMesajKalipRepository msgZamanliMesajKalipRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MsgZamanliMesajKalipDto getById(Long id) {
        MsgZamanliMesajKalip msgZamanliMesajKalip = msgZamanliMesajKalipRepository.getOne(id);
        return modelMapper.map(msgZamanliMesajKalip, MsgZamanliMesajKalipDto.class);
    }

    @Override
    public MsgZamanliMesajKalipDto save(MsgZamanliMesajKalipDto entity) {
        MsgZamanliMesajKalip dbentity = modelMapper.map(entity, MsgZamanliMesajKalip.class);
        msgZamanliMesajKalipRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        msgZamanliMesajKalipRepository.deleteById(id);
        return true;
    }

    @Override
    public MsgZamanliMesajKalipDto update(Long id, MsgZamanliMesajKalipDto entity) {
        MsgZamanliMesajKalip dbentity = msgZamanliMesajKalipRepository.getOne(entity.getId());

        dbentity.setDurum(entity.getDurum());
        dbentity.setBasGonderiZamani(entity.getBasGonderiZamani());
        dbentity.setBaslik(entity.getBaslik());
        dbentity.setIcerik(entity.getIcerik());
        dbentity.setNotIn(entity.getNotIn());
        dbentity.setQueryhql(entity.getQueryhql());
        dbentity.setSonGonderiZamani(entity.getSonGonderiZamani());
        dbentity.setAyDowGunSaatDakikaPattern(entity.getAyDowGunSaatDakikaPattern());

        msgZamanliMesajKalipRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public TPage<MsgZamanliMesajKalipDto> getAll(Pageable pageable) {

        Page<MsgZamanliMesajKalip> msgZamanliMesajKalipResult = msgZamanliMesajKalipRepository.findAll(pageable);

        List<MsgZamanliMesajKalip> list = msgZamanliMesajKalipResult.getContent();

        MsgZamanliMesajKalipDto[] msgZamanliMesajKalipDtoArray = modelMapper.map(list, MsgZamanliMesajKalipDto[].class);

        TPage<MsgZamanliMesajKalipDto> result = new TPage<>(msgZamanliMesajKalipResult, msgZamanliMesajKalipDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        MsgZamanliMesajKalip msgZamanliMesaj = msgZamanliMesajKalipRepository.getOne(id);
        msgZamanliMesaj.setDurum(Boolean.TRUE);
        msgZamanliMesaj = msgZamanliMesajKalipRepository.save(msgZamanliMesaj);

        return true;

    }

    @Override
    public Boolean disable(Long id) {
        MsgZamanliMesajKalip msgZamanliMesaj = msgZamanliMesajKalipRepository.getOne(id);
        msgZamanliMesaj.setDurum(Boolean.FALSE);
        msgZamanliMesaj = msgZamanliMesajKalipRepository.save(msgZamanliMesaj);

        return true;
    }


    @Override
    public TPage<MsgZamanliMesajKalipDto> findByParameters(
            Pageable var,
            String pbaslik,
            String picerik,
            Date dtBasGonZaman,
            Date dtSonGonZaman,
            String ayDowGunSaatDakikaPattern,
            Boolean durum) {


        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<MsgZamanliMesajKalip> msgZamanliMesajKalipResult = msgZamanliMesajKalipRepository.findByParameters(var,
                pbaslik,
                picerik,
                dtBasGonZaman,
                dtSonGonZaman,
                ayDowGunSaatDakikaPattern,
                durumList);

        List<MsgZamanliMesajKalip> list = msgZamanliMesajKalipResult.getContent();

        MsgZamanliMesajKalipDto[] msgZamanliMesajKalipDtoArray = modelMapper.map(list, MsgZamanliMesajKalipDto[].class);

        TPage<MsgZamanliMesajKalipDto> result = new TPage<>(msgZamanliMesajKalipResult, msgZamanliMesajKalipDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }

}
