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
import tr.com.heartsapiens.tedis.service.KurumKurulusService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.KurumKurulusRest.Path)

@CrossOrigin(origins = "http://localhost:4200")
public class KurumKurulusRest implements BaseRest<KurumKurulusDto, Long> {

    @Autowired
    KurumKurulusService kurumKurulusService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<KurumKurulusDto>> getAllByPagination(Pageable pageable) {
        TPage<KurumKurulusDto> data = kurumKurulusService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<KurumKurulusDto> getById(@PathVariable(value = "id", required = true) Long id) {
        KurumKurulusDto hastaDto = kurumKurulusService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<KurumKurulusDto> create(@Valid @RequestBody KurumKurulusDto entity) {
        return ResponseEntity.ok(kurumKurulusService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<KurumKurulusDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody KurumKurulusDto entity) {
        return ResponseEntity.ok(kurumKurulusService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(kurumKurulusService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(kurumKurulusService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(kurumKurulusService.disable(id));
    }

    @GetMapping("/query")
    public ResponseEntity<TPage<KurumKurulusDto>> findByParameters(
            Pageable pageable,
            String kod,
            String ad,
            String adres,
            String eposta,
            String tel,
            Boolean durum
    ) {

        String pkod = kod == null ?"%":"%"+kod+"%";
        String pad = ad == null ?"%":"%"+ad+"%";
        String padres = adres == null ?"%":"%"+adres+"%";
        String peposta = eposta == null ?"%":"%"+eposta+"%";
        String ptel = tel == null ?"%":"%"+tel+"%";

        TPage<KurumKurulusDto> data = kurumKurulusService.findByParameters(pageable,
                pkod,pad,padres,peposta,ptel,durum
                );
        return ResponseEntity.ok(data);
    }





}
