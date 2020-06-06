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
import tr.com.heartsapiens.tedis.dto.NitHastaKomplikasyonDto;
import tr.com.heartsapiens.tedis.service.NitHastaKomplikasyonService;
import tr.com.heartsapiens.tedis.service.TPage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.NitHastaKomplikasyonRest.Path)

@CrossOrigin(origins = "http://localhost:4200")
public class NitHastaKomplikasyonRest implements BaseRest<NitHastaKomplikasyonDto, Long> {

    @Autowired
    NitHastaKomplikasyonService nitHastaKomplikasyonService;

    @Autowired
    @Qualifier("sdfDate")
    SimpleDateFormat sdfDate;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<NitHastaKomplikasyonDto>> getAllByPagination(Pageable pageable) {
        TPage<NitHastaKomplikasyonDto> data = nitHastaKomplikasyonService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<NitHastaKomplikasyonDto> getById(@PathVariable(value = "id", required = true) Long id) {
        NitHastaKomplikasyonDto hastaDto = nitHastaKomplikasyonService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<NitHastaKomplikasyonDto> create(@Valid @RequestBody NitHastaKomplikasyonDto entity) {
        return ResponseEntity.ok(nitHastaKomplikasyonService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<NitHastaKomplikasyonDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody NitHastaKomplikasyonDto entity) {
        return ResponseEntity.ok(nitHastaKomplikasyonService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitHastaKomplikasyonService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitHastaKomplikasyonService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(nitHastaKomplikasyonService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<NitHastaKomplikasyonDto>> findByParameters(
            Pageable pageable,
            Long hastaId,
            String olayAd,
            String olayAciklama,
            String tedaviSonucAd,
            String olayMinZaman,
            String olayMaxZaman,
            Boolean durum
    ) throws Exception{

        if ( hastaId == null){
            throw new RuntimeException("hastaID null olamaz!");

        }

        String polayAd = olayAd == null ?"%":"%"+olayAd+"%";
        String polayAciklama = olayAciklama == null ?"%":"%"+olayAciklama+"%";
        String ptedaviSonucAd = tedaviSonucAd == null ?"%":"%"+tedaviSonucAd+"%";


        Date dtMinZaman = sdfDate.parse("1900-01-01");
        Date dtMaxZaman = sdfDate.parse("2200-01-01");

        if ( olayMinZaman != null){
            dtMinZaman = sdfDate.parse(olayMinZaman);
        }

        if ( olayMaxZaman != null){
            dtMaxZaman = sdfDate.parse(olayMaxZaman);
        }



        TPage<NitHastaKomplikasyonDto> data = nitHastaKomplikasyonService.findByParameters(
                pageable, hastaId,
                polayAd,
                polayAciklama,
                ptedaviSonucAd,
                dtMinZaman,
                dtMaxZaman,durum);

        return ResponseEntity.ok(data);
    }

}