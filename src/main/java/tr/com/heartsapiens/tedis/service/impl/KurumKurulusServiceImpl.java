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
import tr.com.heartsapiens.tedis.dto.KurumKurulusDto;
import tr.com.heartsapiens.tedis.entity.KurumKurulus;
import tr.com.heartsapiens.tedis.repository.KurumKurulusRepository;
import tr.com.heartsapiens.tedis.service.KurumKurulusService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */

@Transactional
@Service(value = "kurumKurulusService")
public class KurumKurulusServiceImpl implements KurumKurulusService{
    
    
    @Autowired
    KurumKurulusRepository kurumKurulusRepository;
    
     @Autowired
    ModelMapper modelMapper;

    @Override
    public KurumKurulusDto getById(Long id) {
         KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(id);
        return modelMapper.map(kurumKurulus, KurumKurulusDto.class);
    }

    @Override
    public KurumKurulusDto save(KurumKurulusDto entity) {
       KurumKurulus kk = modelMapper.map(entity, KurumKurulus.class);
       kurumKurulusRepository.save(kk);
       modelMapper.map(kk, entity);
       return entity;
    }

    @Override
    public Boolean delete(Long id) {
        kurumKurulusRepository.deleteById(id);
        return true;
    }

    @Override
    public KurumKurulusDto update(Long id, KurumKurulusDto entity) {
       KurumKurulus dbentity =  kurumKurulusRepository.getOne(entity.getId());
      
       dbentity.setAd(entity.getAd());
       dbentity.setAdres(entity.getAdres());
       dbentity.setKod(entity.getKod());
       dbentity.setTel(entity.getTel());
        dbentity.setEposta(entity.getEposta());
        dbentity.setDurum(entity.getDurum());
       
       kurumKurulusRepository.save(dbentity);
        modelMapper.map(dbentity, entity);
       return entity;
    }

    @Override
    public TPage<KurumKurulusDto> getAll(Pageable pageable) {

        Page<KurumKurulus> kurumKurulusResult = kurumKurulusRepository.findAll(pageable);

        List<KurumKurulus> list = kurumKurulusResult.getContent();

        KurumKurulusDto[] kurumKurulusDtoArray = modelMapper.map(list, KurumKurulusDto[].class);

        TPage<KurumKurulusDto> result = new TPage<>(kurumKurulusResult, kurumKurulusDtoArray);

        return result;

    }

   @Override
    public Boolean enable(Long id) {

        KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(id);
        kurumKurulus.setDurum(Boolean.TRUE);
        kurumKurulusRepository.save(kurumKurulus); 
        return true;

    }

    @Override
    public Boolean disable(Long id) {
       KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(id);
        kurumKurulus.setDurum(Boolean.FALSE);
        kurumKurulusRepository.save(kurumKurulus); 
        return true;
    }

    @Override
    public TPage<KurumKurulusDto> findByParameters(
            Pageable pageable, String kod, String ad, String adres, String eposta, String tel, Boolean durum) {
        long a = System.currentTimeMillis();

        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }


        Page<KurumKurulus> kurumKurulusResult = kurumKurulusRepository.findByParameters(pageable, kod, ad, adres, eposta,tel,durumList);

        List<KurumKurulus> list = kurumKurulusResult.getContent();

        KurumKurulusDto[] kurumKurulusDtoArray = modelMapper.map(list, KurumKurulusDto[].class);

        TPage<KurumKurulusDto> result = new TPage<>(kurumKurulusResult, kurumKurulusDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }
}
