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
import tr.com.heartsapiens.tedis.dto.TipTedaviSonucDto;
import tr.com.heartsapiens.tedis.service.TPage;
import tr.com.heartsapiens.tedis.service.TipTedaviSonucService;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.TipTedaviSonucRest.Path)

@CrossOrigin(origins = "http://localhost:4200")
public class TipTedaviSonucRest implements BaseRest<TipTedaviSonucDto, Long> {

    @Autowired
    TipTedaviSonucService tipTedaviSonucService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<TipTedaviSonucDto>> getAllByPagination(Pageable pageable) {
        TPage<TipTedaviSonucDto> data = tipTedaviSonucService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TipTedaviSonucDto> getById(@PathVariable(value = "id", required = true) Long id) {
        TipTedaviSonucDto hastaDto = tipTedaviSonucService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<TipTedaviSonucDto> create(@Valid @RequestBody TipTedaviSonucDto entity) {
        return ResponseEntity.ok(tipTedaviSonucService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<TipTedaviSonucDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody TipTedaviSonucDto entity) {
        return ResponseEntity.ok(tipTedaviSonucService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipTedaviSonucService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipTedaviSonucService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipTedaviSonucService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<TipTedaviSonucDto>> findByParameters(
            Pageable pageable,
            String kod,
            String ad,
            Boolean durum
    ) {
        String pad = ad == null ?"%":"%"+ad+"%";
        String pkod = kod == null ?"%":"%"+kod+"%";
        TPage<TipTedaviSonucDto> data = tipTedaviSonucService.findByParameters(pageable, pkod, pad, durum);
        return ResponseEntity.ok(data);
    }
}
