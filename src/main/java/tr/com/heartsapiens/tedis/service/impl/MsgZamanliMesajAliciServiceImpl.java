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
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajAliciDto;
import tr.com.heartsapiens.tedis.entity.Hasta;
import tr.com.heartsapiens.tedis.entity.MsgZamanliMesajAlici;
import tr.com.heartsapiens.tedis.entity.MsgZamanliMesajKalip;
import tr.com.heartsapiens.tedis.repository.HastaRepository;
import tr.com.heartsapiens.tedis.repository.MsgZamanliMesajAliciRepository;
import tr.com.heartsapiens.tedis.repository.MsgZamanliMesajKalipRepository;
import tr.com.heartsapiens.tedis.service.MsgZamanliMesajAliciService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 * @author ersin
 */
@Transactional
@Service(value = "msgZamanliMesajAliciService")
public class MsgZamanliMesajAliciServiceImpl implements MsgZamanliMesajAliciService {

    @Autowired
    MsgZamanliMesajKalipRepository msgZamanliMesajKalipRepository;

    @Autowired
    MsgZamanliMesajAliciRepository msgZamanliMesajAliciRepository;

    @Autowired
    HastaRepository hastaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MsgZamanliMesajAliciDto getById(Long id) {
        MsgZamanliMesajAlici msgZamanliMesajAlici = msgZamanliMesajAliciRepository.getOne(id);
        return modelMapper.map(msgZamanliMesajAlici, MsgZamanliMesajAliciDto.class);
    }

    @Override
    public MsgZamanliMesajAliciDto save(MsgZamanliMesajAliciDto entity) {
        MsgZamanliMesajAlici dbentity = modelMapper.map(entity, MsgZamanliMesajAlici.class);
        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        MsgZamanliMesajKalip kalip = msgZamanliMesajKalipRepository.getOne(entity.getMesajKalip().getId());
        dbentity.setHasta(hasta);
        dbentity.setMesajKalip(kalip);

        msgZamanliMesajAliciRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        msgZamanliMesajAliciRepository.deleteById(id);
        return true;
    }

    @Override
    public MsgZamanliMesajAliciDto update(Long id, MsgZamanliMesajAliciDto entity) {

        MsgZamanliMesajAlici dbentity = msgZamanliMesajAliciRepository.getOne(id);
        Hasta hasta = hastaRepository.getOne(entity.getHastaId());
        MsgZamanliMesajKalip kalip = msgZamanliMesajKalipRepository.getOne(entity.getMesajKalip().getId());
        dbentity.setCommStatus(entity.getCommStatus());
        dbentity.setHasta(hasta);
        dbentity.setMesajKalip(kalip);
        dbentity.setDurum(entity.getDurum());


        msgZamanliMesajAliciRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        entity.setHastaId(dbentity.getHasta().getId());
        return entity;
    }

    @Override
    public TPage<MsgZamanliMesajAliciDto> getAll(Pageable pageable) {

        Page<MsgZamanliMesajAlici> msgZamanliMesajAliciResult = msgZamanliMesajAliciRepository.findAll(pageable);

        List<MsgZamanliMesajAlici> list = msgZamanliMesajAliciResult.getContent();

        MsgZamanliMesajAliciDto[] msgZamanliMesajAliciDtoArray = modelMapper.map(list, MsgZamanliMesajAliciDto[].class);

        TPage<MsgZamanliMesajAliciDto> result = new TPage<>(msgZamanliMesajAliciResult, msgZamanliMesajAliciDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        MsgZamanliMesajAlici msgZamanliMesajAlici = msgZamanliMesajAliciRepository.getOne(id);
        msgZamanliMesajAlici.setDurum(Boolean.TRUE);
        msgZamanliMesajAlici = msgZamanliMesajAliciRepository.save(msgZamanliMesajAlici);

        return true;

    }

    @Override
    public Boolean disable(Long id) {
        MsgZamanliMesajAlici msgZamanliMesajAlici = msgZamanliMesajAliciRepository.getOne(id);
        msgZamanliMesajAlici.setDurum(Boolean.FALSE);
        msgZamanliMesajAlici = msgZamanliMesajAliciRepository.save(msgZamanliMesajAlici);

        return true;
    }


    @Override
    public TPage<MsgZamanliMesajAliciDto> findByParameters(Pageable pageable,
                                                           Long hastaId,
                                                           String msgBaslik,
                                                           String msgIcerik,
                                                           String pattern,
                                                           Boolean durum) {
        long a = System.currentTimeMillis();


        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<MsgZamanliMesajAlici> msgZamanliMesajAliciResult =
                msgZamanliMesajAliciRepository.findByParameters(
                        pageable,
                        hastaId,
                        msgBaslik,
                        msgIcerik,
                        pattern,
                        durumList
                );

        List<MsgZamanliMesajAlici> list = msgZamanliMesajAliciResult.getContent();

        MsgZamanliMesajAliciDto[] msgZamanliMesajAliciDtoArray = modelMapper.map(list, MsgZamanliMesajAliciDto[].class);

        TPage<MsgZamanliMesajAliciDto> result = new TPage<>(msgZamanliMesajAliciResult, msgZamanliMesajAliciDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
