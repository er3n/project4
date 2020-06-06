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
import tr.com.heartsapiens.tedis.dto.MsgHastaMesajDto;
import tr.com.heartsapiens.tedis.service.MsgHastaMesajService;
import tr.com.heartsapiens.tedis.service.TPage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ersin
 *
 Hastaya özel mesajların listelenmesi
 */
@RestController
@RequestMapping(value = RestPaths.MsgHastaMesajRest.Path)
@CrossOrigin(origins = "http://localhost:4200")
public class MsgHastaMesajRest implements BaseRest<MsgHastaMesajDto, Long> {

    @Autowired
    MsgHastaMesajService msgHastaMesajService;

    @Autowired
    @Qualifier("sdfTimeStamp")
    SimpleDateFormat sdfTimeStamp;


    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<MsgHastaMesajDto>> getAllByPagination(Pageable pageable) {
        TPage<MsgHastaMesajDto> data = msgHastaMesajService.getAll(pageable);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<MsgHastaMesajDto> getById(@PathVariable(value = "id", required = true) Long id) {
        MsgHastaMesajDto hastaDto = msgHastaMesajService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }


    @PostMapping
    @Override
    public ResponseEntity<MsgHastaMesajDto> create(@Valid @RequestBody MsgHastaMesajDto entity) {
        return ResponseEntity.ok(msgHastaMesajService.save(entity));
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<MsgHastaMesajDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody MsgHastaMesajDto entity) {
        return ResponseEntity.ok(msgHastaMesajService.update(id, entity));
    }


    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgHastaMesajService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgHastaMesajService.enable(id));
    }


    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgHastaMesajService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<MsgHastaMesajDto>> findByParameters(
            Pageable pageable,
            Long hastaId,
            String baslik,
            String icerik,
            String basGonderiZamani,
            String sonGonderiZamani,
            String commStatus,
            Boolean durum
    ) {


            String pbaslik = baslik == null ? "%" : "%" + baslik + "%";
        String picerik = icerik == null ? "%" : "%" + icerik + "%";
        String pcommStatus = commStatus == null ? "%" : "%" + commStatus + "%";

        Date dtBasGonZaman = null;
        Date dtSonGonZaman = null;

        try {
            dtBasGonZaman =basGonderiZamani == null?sdfTimeStamp.parse("1900-01-01T00:00:00"): sdfTimeStamp.parse(basGonderiZamani);
            dtSonGonZaman = sonGonderiZamani == null?sdfTimeStamp.parse("2100-01-01T00:00:00"):  sdfTimeStamp.parse(sonGonderiZamani);
        }catch (Exception e){
            throw new RuntimeException(e);
        }


        TPage<MsgHastaMesajDto> data = msgHastaMesajService.findByParameters(pageable,hastaId,
                pbaslik,picerik,dtBasGonZaman,dtSonGonZaman, pcommStatus, durum
        );
        return ResponseEntity.ok(data);

    }

}
