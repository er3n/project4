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
import tr.com.heartsapiens.tedis.dto.NitKompliksyonTedaviDto;
import tr.com.heartsapiens.tedis.service.NitKompliksyonTedaviService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.NitKompliksyonTedaviRest.Path)

//@CrossOrigin(origins = "http://localhost:4200")
public class NitKompliksyonTedaviRest implements BaseRest<NitKompliksyonTedaviDto, Long> {

    @Autowired
    NitKompliksyonTedaviService nitKompliksyonTedaviService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<NitKompliksyonTedaviDto>> getAllByPagination(Pageable pageable) {
        TPage<NitKompliksyonTedaviDto> data = nitKompliksyonTedaviService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<NitKompliksyonTedaviDto> getById(@PathVariable(value = "id", required = true) Long id) {
        NitKompliksyonTedaviDto hastaDto = nitKompliksyonTedaviService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<NitKompliksyonTedaviDto> create(@Valid @RequestBody NitKompliksyonTedaviDto entity) {
        return ResponseEntity.ok(nitKompliksyonTedaviService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<NitKompliksyonTedaviDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody NitKompliksyonTedaviDto entity) {
        return ResponseEntity.ok(nitKompliksyonTedaviService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitKompliksyonTedaviService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitKompliksyonTedaviService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitKompliksyonTedaviService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<NitKompliksyonTedaviDto>> findByParameters(
            Pageable pageable,
            String ad,
            String model,
            String aciklama,
            Boolean durum
    ) {
        throw new RuntimeException("bir komplikasyona karşı birden çok tedavi mi?");
    }


}
