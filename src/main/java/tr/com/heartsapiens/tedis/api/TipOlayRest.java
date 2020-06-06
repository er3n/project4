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
import tr.com.heartsapiens.tedis.dto.TipOlayDto;
import tr.com.heartsapiens.tedis.entity.TipOlay;
import tr.com.heartsapiens.tedis.service.TipOlayService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.TipOlayRest.Path)

@CrossOrigin(origins = "http://localhost:4200")
public class TipOlayRest implements BaseRest<TipOlayDto, Long> {

    @Autowired
    TipOlayService tipOlayService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<TipOlayDto>> getAllByPagination(Pageable pageable) {
        TPage<TipOlayDto> data = tipOlayService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TipOlayDto> getById(@PathVariable(value = "id", required = true) Long id) {
        TipOlayDto hastaDto = tipOlayService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<TipOlayDto> create(@Valid @RequestBody TipOlayDto entity) {
        return ResponseEntity.ok(tipOlayService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<TipOlayDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody TipOlayDto entity) {
        return ResponseEntity.ok(tipOlayService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipOlayService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipOlayService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipOlayService.disable(id));
    }

    @GetMapping("/query")
    public ResponseEntity<TPage<TipOlayDto>> findByParameters(
            Pageable pageable,
            String kod,
            String ad, 
            Boolean durum
    ) {
        String pad = ad == null ?"%":"%"+ad+"%";
        String pkod = kod == null ?"%":"%"+kod+"%";
        TPage<TipOlayDto> data = tipOlayService.findByParameters(pageable, pkod, pad,durum);
        return ResponseEntity.ok(data);
    }

}
