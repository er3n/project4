/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.api;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.heartsapiens.tedis.dto.NitGozlemDegerDto;
import tr.com.heartsapiens.tedis.service.NitGozlemDegerService;
import tr.com.heartsapiens.tedis.service.TPage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.NitGozlemDegerRest.Path)

//@CrossOrigin(origins = "http://localhost:4200")
public class NitGozlemDegerRest implements BaseRest<NitGozlemDegerDto, Long> {

    @Autowired
    NitGozlemDegerService nitGozlemDegerService;

    @Autowired
    @Qualifier("sdfDate")
    SimpleDateFormat sdfDate;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<NitGozlemDegerDto>> getAllByPagination(Pageable pageable) {
        TPage<NitGozlemDegerDto> data = nitGozlemDegerService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<NitGozlemDegerDto> getById(@PathVariable(value = "id", required = true) Long id) {
        NitGozlemDegerDto data = nitGozlemDegerService.getById(id);
        return ResponseEntity.ok(data);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<NitGozlemDegerDto> create(@Valid @RequestBody NitGozlemDegerDto entity) {
        return ResponseEntity.ok(nitGozlemDegerService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<NitGozlemDegerDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody NitGozlemDegerDto entity) {
        return ResponseEntity.ok(nitGozlemDegerService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitGozlemDegerService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitGozlemDegerService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitGozlemDegerService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<NitGozlemDegerDto>> findByParameters(
            Pageable pageable,
            Long hastaId,
            String ad,
            String minZaman,
            String maxZaman,
            Boolean durum
    ) throws Exception{
        String pad = ad == null ?"%":"%"+ad+"%";

        Date dtMinZaman = sdfDate.parse("1900-01-01");
        Date dtMaxZaman = sdfDate.parse("2200-01-01");

        if ( minZaman != null){
            dtMinZaman = sdfDate.parse(minZaman);
        }

        if ( maxZaman != null){
            dtMaxZaman = sdfDate.parse(maxZaman);
        }



        TPage<NitGozlemDegerDto> data = nitGozlemDegerService.findByParameters(pageable, hastaId, pad, dtMinZaman, dtMaxZaman,durum);
        return ResponseEntity.ok(data);
    }


}
