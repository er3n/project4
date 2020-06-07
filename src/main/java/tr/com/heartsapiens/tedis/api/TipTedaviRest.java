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
import tr.com.heartsapiens.tedis.dto.TipTedaviDto;
import tr.com.heartsapiens.tedis.service.TipTedaviService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.TipTedaviRest.Path)

//@CrossOrigin(origins = "http://localhost:4200")
public class TipTedaviRest implements BaseRest<TipTedaviDto, Long> {

    @Autowired
    TipTedaviService tipTedaviService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<TipTedaviDto>> getAllByPagination(Pageable pageable) {
        TPage<TipTedaviDto> data = tipTedaviService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TipTedaviDto> getById(@PathVariable(value = "id", required = true) Long id) {
        TipTedaviDto hastaDto = tipTedaviService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<TipTedaviDto> create(@Valid @RequestBody TipTedaviDto entity) {
        return ResponseEntity.ok(tipTedaviService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<TipTedaviDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody TipTedaviDto entity) {
        return ResponseEntity.ok(tipTedaviService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipTedaviService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipTedaviService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(tipTedaviService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<TipTedaviDto>> findByParameters(
            Pageable pageable,
            String kod,
            String ad,
            Boolean durum
    ) {
        String pad = ad == null ?"%":"%"+ad+"%";
        String pkod = kod == null ?"%":"%"+kod+"%";
        TPage<TipTedaviDto> data = tipTedaviService.findByParameters(pageable, pkod, pad, durum);
        return ResponseEntity.ok(data);
    }

}
