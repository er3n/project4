/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.heartsapiens.tedis.dto.HastaDto;
import tr.com.heartsapiens.tedis.entity.Hasta;
import tr.com.heartsapiens.tedis.entity.KurumKurulus;
import tr.com.heartsapiens.tedis.repository.HastaRepository;
import tr.com.heartsapiens.tedis.repository.KurumKurulusRepository;
import tr.com.heartsapiens.tedis.service.HastaService;
import tr.com.heartsapiens.tedis.service.TPage;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "hastaService")

public class HastaServiceImpl extends AbstractService implements HastaService {

    @Autowired
    HastaRepository hastaRepository;

    @Autowired
    KurumKurulusRepository kurumKurulusRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public HastaDto getById(Long id) {
        Hasta hasta = hastaRepository.getOne(id);
        return modelMapper.map(hasta, HastaDto.class);
    }

    @Override

    public HastaDto save( HastaDto hastaDto) {

        Hasta hasta = modelMapper.map(hastaDto, Hasta.class);
        KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(hastaDto.getKurumKurulus().getId());
        hasta.setKurumKurulus(kurumKurulus);
        hastaRepository.save(hasta);

        return modelMapper.map(hasta, HastaDto.class);

    }

    @Override
    public Boolean delete(Long id) {
        hastaRepository.deleteById(id);
        return true;
    }

    @Override
    public HastaDto update(Long id, HastaDto entity) {

//        http://progressivecoder.com/setting-hibernate-envers-spring-boot/
//        https://github.com/juanca87/example-envers        
//          uma burası önemli, ilk kez history oluşturacağız
        Hasta hasta = hastaRepository.getOne(id);
        if (hasta == null) {
            throw new IllegalArgumentException("kayıt bulunamadı! :" + id);
        }

        hasta.setAciklama(entity.getAciklama());
        hasta.setAd(entity.getAd());
        hasta.setDogtar(entity.getDogtar());
        hasta.setSoyad(entity.getSoyad());
        hasta.setTcno(entity.getTcno());
        hasta.setUyruk(entity.getUyruk());
        hasta.setDurum(entity.getDurum());
        hasta.setEmail(entity.getEmail());
        hasta.setTel(entity.getTel());

        // kurum kurulusu
        //uma burası önemli, ilk kez history oluşturacağız
        KurumKurulus kk = kurumKurulusRepository.getOne(entity.getKurumKurulus().getId());
        hasta.setKurumKurulus(kk);

        hastaRepository.save(hasta);
        return modelMapper.map(hasta, HastaDto.class);
    }

    @Override
    public TPage<HastaDto> getAll(Pageable pageable) {

        Page<Hasta> hastaResult = hastaRepository.findAll(pageable);

        List<Hasta> list = hastaResult.getContent();

        HastaDto[] hastaDtoArray = modelMapper.map(list, HastaDto[].class);

        TPage<HastaDto> result = new TPage<>(hastaResult, hastaDtoArray);

        return result;

    }

    @Override
    public Boolean enable(Long id) {

        Hasta hasta = hastaRepository.getOne(id);
        hasta.setDurum(Boolean.TRUE);
        hastaRepository.save(hasta);
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        Hasta hasta = hastaRepository.getOne(id);
        hasta.setDurum(Boolean.FALSE);
        hastaRepository.save(hasta);
        return true;
    }

    @Override
    public TPage<HastaDto> getAllByKurumKurulusId(Long kurumKurulusId, Pageable pageable) {

        KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(kurumKurulusId);

        Page<Hasta> hastaResult = hastaRepository.getAllByKurumKurulus(kurumKurulus, pageable);

        List<Hasta> list = hastaResult.getContent();

        HastaDto[] hastaDtoArray = modelMapper.map(list, HastaDto[].class);

        TPage<HastaDto> result = new TPage<>(hastaResult, hastaDtoArray);

        return result;
    }

    @Override
    public TPage<HastaDto> findByParameters(Pageable pageable, Long ptcno,
                                            String pad, String psoyad, String pemail, String ptel,
                                            Date pdogtar,
                                            String aciklama,
                                            String puyruk,
                                            Boolean durum, Long kurumId) {
        long a = System.currentTimeMillis();

        List<Boolean> durumList = new ArrayList<>();
        if (durum == null) {
            durumList.add(true);
            durumList.add(false);
        } else {
            durumList.add(durum);
        }



        Page<Hasta> hastaResult = hastaRepository.findByParameters(
                pageable,
                ptcno,
                pad,
                psoyad,
                pemail,
                ptel,
                pdogtar,
                aciklama,
                puyruk,
                durumList,
                kurumId);

        List<Hasta> list = hastaResult.getContent();

        HastaDto[] hastaDtoArray = modelMapper.map(list, HastaDto[].class);

        TPage<HastaDto> result = new TPage<>(hastaResult, hastaDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b - a);

        return result;
    }

}
