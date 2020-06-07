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
import tr.com.heartsapiens.tedis.dto.HastaDto;
import tr.com.heartsapiens.tedis.service.HastaService;
import tr.com.heartsapiens.tedis.service.TPage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.HastaRest.Path)
//@CrossOrigin(origins = "http://localhost:4200")
public class HastaRest implements BaseRest<HastaDto, Long> {




    @Autowired
    @Qualifier("sdfDate")
    SimpleDateFormat sdfDate;

    @Autowired
    HastaService hastaService;

    //http://localhost:8000/rest/v1/hasta/pagination?page=1&size=10&sort=ad
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<HastaDto>> getAllByPagination(Pageable pageable) {
        TPage<HastaDto> data = hastaService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/filter_by_kurumkurulus/{kurum_kurulus_id}") 
    public ResponseEntity<TPage<HastaDto>> getAllByKurumKurulusId(@PathVariable(value = "kurum_kurulus_id", required = true) Long kurumKurulusId,Pageable pageable) {
        TPage<HastaDto> data = hastaService.getAllByKurumKurulusId(kurumKurulusId,pageable);
        return ResponseEntity.ok(data);
    }
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<HastaDto> getById(@PathVariable(value = "id", required = true) Long id) {
        HastaDto hastaDto = hastaService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<HastaDto> create( @RequestBody HastaDto entity) {

        //entity için valid dediğimizde, kurum kurulus bilgileri de tam olmak durumunda
        // valid i kaldırdık {id:1} gönderebildik

        return ResponseEntity.ok(hastaService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<HastaDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody HastaDto entity) {
        return ResponseEntity.ok(hastaService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(hastaService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(hastaService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(hastaService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<HastaDto>> findByParameters(
            Pageable pageable,
            Long tcno,
            String ad,
            String soyad,
            String email,
            String tel,
            String dogtar,
            String aciklama,
            String uyruk,
            Boolean durum,
            Long kurumId
    ) {
        String pad = ad == null ?"%":"%"+ad+"%";
        String psoyad = soyad == null ?"%":"%"+soyad+"%";
        String pemail= email == null ?"%":"%"+email+"%";
        String ptel = tel == null ?"%":"%"+tel+"%";
        String paciklama = aciklama == null ?"%":"%"+aciklama+"%";
        String puyruk = uyruk == null ?"%":"%"+uyruk+"%";


        Date pdogtar = null;
        try{

            pdogtar = dogtar != null? sdfDate.parse(dogtar):sdfDate.parse(sdfDate.format(new Date()));
        }catch (Exception e){

            throw new RuntimeException(e);
        }

        TPage<HastaDto> data = hastaService.findByParameters(pageable,
                tcno,
                pad,
                psoyad,
                pemail    ,
                ptel,
                pdogtar,
                paciklama,
                puyruk,
                durum,
                kurumId);
        return ResponseEntity.ok(data);
    }


}
