/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service.impl;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.heartsapiens.tedis.dto.SecUserDto;
import tr.com.heartsapiens.tedis.entity.KurumKurulus;
import tr.com.heartsapiens.tedis.entity.SecProfile;
import tr.com.heartsapiens.tedis.entity.SecUser;
import tr.com.heartsapiens.tedis.repository.KurumKurulusRepository;
import tr.com.heartsapiens.tedis.repository.SecProfileRepository;
import tr.com.heartsapiens.tedis.repository.SecUserRepository;
import tr.com.heartsapiens.tedis.service.SecUserService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@Transactional
@Service(value = "secUserService")
public class SecUserServiceImpl implements SecUserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    SecUserRepository secUserRepository;
    
     @Autowired
     KurumKurulusRepository kurumKurulusRepository;

    @Autowired
    SecProfileRepository secProfileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public SecUserDto getById(Long id) {
        SecUser secUser = secUserRepository.getOne(id);
        return modelMapper.map(secUser, SecUserDto.class);
    }

    @Override
    public SecUserDto save(SecUserDto entity) {

        SecUser dbentity = modelMapper.map(entity, SecUser.class);

        String encryptedPassword = passwordEncoder.encode(entity.getPassword());
        dbentity.setPassword(encryptedPassword);

        KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(entity.getKurumKurulus().getId());
        dbentity.setKurumKurulus(kurumKurulus);

        List<Long> idList = new ArrayList<>();
        entity.getProfileList().forEach((ob)->{ idList.add(ob.getId()); });
        Set<SecProfile> profiles = new HashSet<>( secProfileRepository.findAllById(idList));
        dbentity.setProfileList(profiles);

        secUserRepository.save(dbentity);



        modelMapper.map(dbentity, entity);

        return entity;
    }

    @Override
    public Boolean delete(Long id) {
         secUserRepository.deleteById(id);
        return true;
    }

    @Override
    public SecUserDto update(Long id, SecUserDto entity) {
       SecUser dbentity =  secUserRepository.getOne(entity.getId());
       dbentity.setEmail(entity.getEmail());
       
       
       
       KurumKurulus kurumKurulus = kurumKurulusRepository.getOne(entity.getKurumKurulus().getId());
       dbentity.setKurumKurulus(kurumKurulus);
       dbentity.setName(entity.getName());
       dbentity.setPassword(entity.getPassword());
       dbentity.setSurname(entity.getSurname());
       dbentity.setUsername(entity.getUsername());
        dbentity.setDurum(entity.getDurum());


        List<Long> idList = new ArrayList<>();
        entity.getProfileList().forEach((ob)->{ idList.add(ob.getId()); });
        Set<SecProfile> profiles = new HashSet<>( secProfileRepository.findAllById(idList));
        dbentity.setProfileList(profiles);


        secUserRepository.save(dbentity);
       
        modelMapper.map(dbentity, entity);
       return entity;
    }

     @Override
    public TPage<SecUserDto> getAll(Pageable pageable) {

        Page<SecUser> secUserResult = secUserRepository.findAll(pageable);

         List<SecUser> list = secUserResult.getContent();

        SecUserDto[] secUserDtoArray = modelMapper.map(list, SecUserDto[].class);

        TPage<SecUserDto> result = new TPage<>(secUserResult, secUserDtoArray);

        return result;

    }

   @Override
    public Boolean enable(Long id) {

        SecUser secUser = secUserRepository.getOne(id);
        secUser.setDurum(Boolean.TRUE);
        secUser = secUserRepository.save(secUser);
        
        return true;

    }

    @Override
    public Boolean disable(Long id) {
        SecUser secUser = secUserRepository.getOne(id);
        secUser.setDurum(Boolean.FALSE);
        secUser = secUserRepository.save(secUser);
        
        return true;
    }

    @Override
    public TPage<SecUserDto> findByParameters(Pageable pageable,
                                              String pusername, String pname, String psurname, String pemail,
                                              Long kurumKurulusId,
                                              Long[] profileIdList,
                                              Boolean durum) {
        long a = System.currentTimeMillis();



        List<SecProfile> profileList = null;

        if(profileIdList != null){
             profileList = secProfileRepository.findAllById( Arrays.asList(profileIdList));
        }

        KurumKurulus kurumKurulus = null;
        if(kurumKurulusId != null){
            kurumKurulus= kurumKurulusRepository.getOne(kurumKurulusId);
        }

        List<Boolean> enabledList = new ArrayList<>();
        if( durum == null ){
            enabledList.add(true);
            enabledList.add(false);
        }else{
            enabledList.add(durum);
        }




        Page<SecUser> secUserResult = secUserRepository.findByParameters(pageable,
                pusername,pname,psurname, pemail,
                kurumKurulus,profileList,enabledList);

        List<SecUser> list = secUserResult.getContent();

        SecUserDto[] secUserDtoArray = modelMapper.map(list, SecUserDto[].class);

        TPage<SecUserDto> result = new TPage<SecUserDto>(secUserResult, secUserDtoArray);

        long b = System.currentTimeMillis();

        result.setProcessDurationMs(b-a);

        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecUser> secUser= secUserRepository.findByUsername(username);
        if(!secUser.isPresent()) {
            throw new UsernameNotFoundException("User with email " + username + " was not found in the database");
        }
        SecUserDto secUserDto = modelMapper.map(secUser.get(), SecUserDto.class);
        return secUserDto;
    }
}
