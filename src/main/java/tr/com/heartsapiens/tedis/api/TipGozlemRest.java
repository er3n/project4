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
import tr.com.heartsapiens.tedis.dto.TipGozlemDto;
import tr.com.heartsapiens.tedis.service.TipGozlemService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.TipGozlemRest.Path)

//@CrossOrigin(origins = "http://localhost:4200")
public class TipGozlemRest implements BaseRest<TipGozlemDto, Long> {

    @Autowired
    TipGozlemService tipGozlemService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<TipGozlemDto>> getAllByPagination(Pageable pageable) {
        TPage<TipGozlemDto> data = tipGozlemService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TipGozlemDto> getById(@PathVariable(value = "id", required = true) Long id) {
        TipGozlemDto hastaDto = tipGozlemService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<TipGozlemDto> create(@Valid @RequestBody TipGozlemDto entity) {
        return ResponseEntity.ok(tipGozlemService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<TipGozlemDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody TipGozlemDto entity) {
        return ResponseEntity.ok(tipGozlemService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipGozlemService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipGozlemService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipGozlemService.disable(id));
    }

    @GetMapping("/query")
    public ResponseEntity<TPage<TipGozlemDto>> findByParameters(
            Pageable pageable,
            String ad,
            String veritip,
            String aciklama,
            Boolean durum
    ) {

        String pad = ad == null ?"%":"%"+ad+"%";
        String pveritip = veritip == null ?"%":"%"+veritip+"%";
        String paciklama= aciklama == null ?"%":"%"+aciklama+"%";
        TPage<TipGozlemDto> data = tipGozlemService.findByParameters(pageable, pad, pveritip, paciklama,durum);
        return ResponseEntity.ok(data);
    }


}
