/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.api;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.heartsapiens.tedis.dto.KurumKurulusDto;
import tr.com.heartsapiens.tedis.dto.SecProfileDto;
import tr.com.heartsapiens.tedis.dto.SecUserDto;
import tr.com.heartsapiens.tedis.entity.KurumKurulus;
import tr.com.heartsapiens.tedis.service.SecUserService;
import tr.com.heartsapiens.tedis.service.TPage;

import java.util.List;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.SecUserRest.Path)
@CrossOrigin(origins = "http://localhost:4200")
public class SecUserRest implements BaseRest<SecUserDto, Long> {

    @Autowired
    SecUserService secUserService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<SecUserDto>> getAllByPagination(Pageable pageable) {
        TPage<SecUserDto> data = secUserService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<SecUserDto> getById(@PathVariable(value = "id", required = true) Long id) {
        SecUserDto hastaDto = secUserService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<SecUserDto> create(@Valid @RequestBody SecUserDto entity) {
        return ResponseEntity.ok(secUserService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<SecUserDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody SecUserDto entity) {
        return ResponseEntity.ok(secUserService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(secUserService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(secUserService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(secUserService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<SecUserDto>> findByParameters(
            Pageable pageable,
            String username,
            String name,
            String surname,
            String email,
            Long kurumKurulusId,
            Long[] profileList,
            Boolean durum
    ) {


        String pusername = username == null ?"%":"%"+username+"%";
        String pname = name == null ?"%":"%"+name+"%";
        String psurname = surname == null ?"%":"%"+surname+"%";
        String pemail= email == null ?"%":"%"+email+"%";





        TPage<SecUserDto> data = secUserService.findByParameters(pageable, pusername, pname, psurname, pemail,
                kurumKurulusId,
                profileList,
                durum);
        return ResponseEntity.ok(data);
    }
}
