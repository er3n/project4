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
import tr.com.heartsapiens.tedis.dto.NitHastaCihazDto;
import tr.com.heartsapiens.tedis.service.NitHastaCihazService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.NitHastaCihazRest.Path)

//@CrossOrigin(origins = "http://localhost:4200")
public class NitHastaCihazRest implements BaseRest<NitHastaCihazDto, Long> {

    @Autowired
    NitHastaCihazService nitHastaCihazService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<NitHastaCihazDto>> getAllByPagination(Pageable pageable) {
        TPage<NitHastaCihazDto> data = nitHastaCihazService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<NitHastaCihazDto> getById(@PathVariable(value = "id", required = true) Long id) {
        NitHastaCihazDto hastaDto = nitHastaCihazService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<NitHastaCihazDto> create(@Valid @RequestBody NitHastaCihazDto entity) {
        return ResponseEntity.ok(nitHastaCihazService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<NitHastaCihazDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody NitHastaCihazDto entity) {
        return ResponseEntity.ok(nitHastaCihazService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitHastaCihazService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitHastaCihazService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitHastaCihazService.disable(id));
    }

    @GetMapping("/query")
    public ResponseEntity<TPage<NitHastaCihazDto>> findByParameters(
            Pageable pageable,
            Long hastaId,
            String ad,
            String model,
            String aciklama,
            Boolean durum
    ) {
        String pad = ad == null ?"%":"%"+ad+"%";
        String pmodel = model == null ?"%":"%"+model+"%";
        String paciklama= aciklama == null ?"%":"%"+aciklama+"%";
        TPage<NitHastaCihazDto> data = nitHastaCihazService.findByParameters(pageable, hastaId, pad, pmodel, paciklama,durum);
        return ResponseEntity.ok(data);
    }


}
