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
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajAliciDto;
import tr.com.heartsapiens.tedis.service.MsgZamanliMesajAliciService;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.MsgZamanliMesajAliciRest.Path)

@CrossOrigin(origins = "http://localhost:4200")
public class MsgZamanliMesajAliciRest implements BaseRest<MsgZamanliMesajAliciDto, Long> {

    @Autowired
    MsgZamanliMesajAliciService msgZamanliMesajAliciService;

    
    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<MsgZamanliMesajAliciDto>> getAllByPagination(Pageable pageable) {
        TPage<MsgZamanliMesajAliciDto> data = msgZamanliMesajAliciService.getAll(pageable);
        return ResponseEntity.ok(data);
    }
    
   
    

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<MsgZamanliMesajAliciDto> getById(@PathVariable(value = "id", required = true) Long id) {
        MsgZamanliMesajAliciDto hastaDto = msgZamanliMesajAliciService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }

    
    @PostMapping
    @Override
    public ResponseEntity<MsgZamanliMesajAliciDto> create(@Valid @RequestBody MsgZamanliMesajAliciDto entity) {
        return ResponseEntity.ok(msgZamanliMesajAliciService.save(entity));
    }

    
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<MsgZamanliMesajAliciDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody MsgZamanliMesajAliciDto entity) {
        return ResponseEntity.ok(msgZamanliMesajAliciService.update(id, entity));
    }

    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgZamanliMesajAliciService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgZamanliMesajAliciService.enable(id));
    }

    
    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgZamanliMesajAliciService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<MsgZamanliMesajAliciDto>> findByParameters(
            Pageable pageable,
            Long hastaId,
            String msgBaslik,
            String msgIcerik,
            String pattern,
            Boolean durum
    ) {
        if ( hastaId == null){
            throw new RuntimeException("hastaID null olamaz!");

        }

        String pmsgBaslik = msgBaslik == null ?"%":"%"+msgBaslik+"%";
        String pmsgIcerik = msgIcerik == null ?"%":"%"+msgIcerik+"%";
        String ppattern = pattern == null ?"%":"%"+pattern+"%";





        TPage<MsgZamanliMesajAliciDto> data = msgZamanliMesajAliciService.findByParameters(
                pageable, hastaId,
                pmsgBaslik,
                pmsgIcerik,
                ppattern,
                durum);

        return ResponseEntity.ok(data);
    }


}
