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
import tr.com.heartsapiens.tedis.dto.SecProfileDto;
import tr.com.heartsapiens.tedis.service.SecProfileService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.SecProfileRest.Path)
@CrossOrigin(origins = "http://localhost:4200")
public class SecProfileRest implements BaseRest<SecProfileDto, Long> {

    @Autowired
    SecProfileService secProfileService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<SecProfileDto>> getAllByPagination(Pageable pageable) {
        TPage<SecProfileDto> data = secProfileService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<SecProfileDto> getById(@PathVariable(value = "id", required = true) Long id) {
        SecProfileDto hastaDto = secProfileService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<SecProfileDto> create(@Valid @RequestBody SecProfileDto entity) {
        return ResponseEntity.ok(secProfileService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<SecProfileDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody SecProfileDto entity) {
        return ResponseEntity.ok(secProfileService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(secProfileService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(secProfileService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(secProfileService.disable(id));
    }



    @GetMapping("/query")
    public ResponseEntity<TPage<SecProfileDto>> getAllByPagination(
            Pageable pageable,
            String name,
            String aciklama,
            Boolean durum
    ) {


         String pname = name == null ?"%":"%"+name+"%";
        String paciklama= aciklama == null ?"%":"%"+aciklama+"%";
        TPage<SecProfileDto> data = secProfileService.findByParameters(pageable, pname, paciklama,durum);
        return ResponseEntity.ok(data);
    }

}
