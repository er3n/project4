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
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajKalipDto;
import tr.com.heartsapiens.tedis.service.MsgZamanliMesajKalipService;
import tr.com.heartsapiens.tedis.service.TPage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.MsgZamanliMesajKalipRest.Path)

@CrossOrigin(origins = "http://localhost:4200")
public class MsgZamanliMesajKalipRest implements BaseRest<MsgZamanliMesajKalipDto, Long> {

    @Autowired
    MsgZamanliMesajKalipService msgZamanliMesajKalipService;

    @Autowired
    @Qualifier("sdfTimeStamp")
    SimpleDateFormat sdfTimeStamp;

    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<MsgZamanliMesajKalipDto>> getAllByPagination(Pageable pageable) {
        TPage<MsgZamanliMesajKalipDto> data = msgZamanliMesajKalipService.getAll(pageable);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<MsgZamanliMesajKalipDto> getById(@PathVariable(value = "id", required = true) Long id) {
        MsgZamanliMesajKalipDto hastaDto = msgZamanliMesajKalipService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }


    @PostMapping
    @Override
    public ResponseEntity<MsgZamanliMesajKalipDto> create(@Valid @RequestBody MsgZamanliMesajKalipDto entity) {
        return ResponseEntity.ok(msgZamanliMesajKalipService.save(entity));
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<MsgZamanliMesajKalipDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody MsgZamanliMesajKalipDto entity) {
        return ResponseEntity.ok(msgZamanliMesajKalipService.update(id, entity));
    }


    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgZamanliMesajKalipService.delete(id));
    }

    @PutMapping("/{id}/enable")
    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgZamanliMesajKalipService.enable(id));
    }


    @PutMapping("/{id}/disable")
    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(msgZamanliMesajKalipService.disable(id));
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<MsgZamanliMesajKalipDto>> findByParameters(
            Pageable pageable,
            String baslik,
            String icerik,
            String basGonderiZamani,
            String sonGonderiZamani,
            String ayDowGunSaatDakikaPattern,
            Boolean durum
    ) {


        String pbaslik = baslik == null ? "%" : "%" + baslik + "%";
        String picerik = icerik == null ? "%" : "%" + icerik + "%";
        String payDowGunSaatDakikaPattern = ayDowGunSaatDakikaPattern == null ? "" : ayDowGunSaatDakikaPattern;

        Date dtBasGonZaman = null;
        Date dtSonGonZaman = null;

        try {

            // verilen tarihler arasında bu desene uyanlar vaarsa işleteceğiz ...
            dtBasGonZaman = basGonderiZamani == null ? sdfTimeStamp.parse("1900-01-01T00:00:00") : sdfTimeStamp.parse(basGonderiZamani);
            dtSonGonZaman = sonGonderiZamani == null ? sdfTimeStamp.parse("2100-01-01T00:00:00") : sdfTimeStamp.parse(sonGonderiZamani);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TPage<MsgZamanliMesajKalipDto> data = msgZamanliMesajKalipService.findByParameters(pageable,
                pbaslik, picerik, dtBasGonZaman, dtSonGonZaman, payDowGunSaatDakikaPattern, durum
        );
        return ResponseEntity.ok(data);

    }
}


