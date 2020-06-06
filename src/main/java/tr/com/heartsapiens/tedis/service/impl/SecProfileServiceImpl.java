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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.heartsapiens.tedis.dto.LogTedisDto;
import tr.com.heartsapiens.tedis.dto.SecProfileDto;
import tr.com.heartsapiens.tedis.entity.LogTedis;
import tr.com.heartsapiens.tedis.entity.SecProfile;
import tr.com.heartsapiens.tedis.repository.SecProfileRepository;
import tr.com.heartsapiens.tedis.service.SecProfileService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "secProfileService")
public class SecProfileServiceImpl implements SecProfileService {

    @Autowired
    SecProfileRepository secProfileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public SecProfileDto getById(Long id) {
        SecProfile secProfileDto = secProfileRepository.getOne(id);
        return modelMapper.map(secProfileDto, SecProfileDto.class);
    }

    @Override
    public SecProfileDto save(SecProfileDto entity) {
        SecProfile dbentity = modelMapper.map(entity, SecProfile.class);
        secProfileRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
        return entity;
    }

    @Override
    public Boolean delete(Long id) {
        secProfileRepository.deleteById(id);
        return true;
    }

    @Override
    public SecProfileDto update(Long id, SecProfileDto entity) {
        SecProfile dbentity =  secProfileRepository.getOne(entity.getId());

        dbentity.setName(entity.getName());
        dbentity.setAciklama(entity.getAciklama());
        dbentity.setDurum(entity.getDurum());
        
        
        secProfileRepository.save(dbentity);
       
        modelMapper.map(dbentity, entity);
       return entity;
    }

    @Override
    public TPage<SecProfileDto> getAll(Pageable pageable) {

        Page<SecProfile> secProfileResult = secProfileRepository.findAll(pageable);

        List<SecProfile> list = secProfileResult.getContent();

        SecProfileDto[] secProfileDtoArray = modelMapper.map(list, SecProfileDto[].class);

        TPage<SecProfileDto> result = new TPage<>(secProfileResult, secProfileDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        SecProfile secProfile = secProfileRepository.getOne(id);
        secProfile.setDurum(Boolean.TRUE);
        secProfile = secProfileRepository.save(secProfile);
        
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        SecProfile secProfile = secProfileRepository.getOne(id);
        secProfile.setDurum(Boolean.FALSE);
        secProfile = secProfileRepository.save(secProfile);
        
        return true;
    }

    public TPage<SecProfileDto> findByParameters(
            Pageable var,
            String name,
            String aciklama,
            Boolean durum

    ){
        long a = System.currentTimeMillis();

        Page<SecProfile> secProfileResult = secProfileRepository.findByParameters(var,name,aciklama,durum);

        List<SecProfile> list = secProfileResult.getContent();

        SecProfileDto[] secProfileDtoArray = modelMapper.map(list, SecProfileDto[].class);

        TPage<SecProfileDto> result = new TPage<>(secProfileResult, secProfileDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b-a);

        return result;

    }

}
