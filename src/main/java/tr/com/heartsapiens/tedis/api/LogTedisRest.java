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
import tr.com.heartsapiens.tedis.dto.LogTedisDto;
import tr.com.heartsapiens.tedis.service.LogTedisService;
import tr.com.heartsapiens.tedis.service.TPage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ersin
 */
@RestController
@RequestMapping(value = RestPaths.LogTedisRest.Path)
//@CrossOrigin(origins = "http://localhost:4200")
public class LogTedisRest implements BaseRest<LogTedisDto, Long> {

    @Autowired
    LogTedisService logTedisService;


    @GetMapping("/pagination")
    @Override
    public ResponseEntity<TPage<LogTedisDto>> getAllByPagination(Pageable pageable) {
        TPage<LogTedisDto> data = logTedisService.getAll(pageable);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/query")
    public ResponseEntity<TPage<LogTedisDto>> getAllByPagination(
            Pageable pageable,
            Long hastaId,
            String methodName,
            String basZaman,
            String bitZaman,
            String tip,
            Long minSure,
            Long maxSure,
            String ip,
            String kullanici,
            String detay
    ) {


        Date dtBasZaman = null;
        Date dtBitZaman = null;
        String sDetay = detay == null ?"%":"%"+detay+"%";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
             dtBasZaman = basZaman == null? sdf.parse("0000-01-01T00:00:00")  : sdf.parse(basZaman);
             dtBitZaman = bitZaman == null? sdf.parse("9999-01-01T00:00:00")  : sdf.parse(bitZaman);
        }catch (Exception e){
            // hata Response donebiliriz
            throw new RuntimeException(e);
        }




        TPage<LogTedisDto> data = logTedisService.findByParameters(pageable,hastaId, methodName, dtBasZaman, dtBitZaman, tip, minSure, maxSure, ip, kullanici, sDetay);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<LogTedisDto> getById(@PathVariable(value = "id", required = true) Long id) {
        LogTedisDto hastaDto = logTedisService.getById(id);
        return ResponseEntity.ok(hastaDto);
    }


    @Override
    public ResponseEntity<LogTedisDto> create(@Valid @RequestBody LogTedisDto entity) {
        throw new IllegalAccessError("geçersiz işlem");
    }


    @Override
    public ResponseEntity<LogTedisDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody LogTedisDto entity) {
        throw new IllegalAccessError("geçersiz işlem");
    }


    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        throw new IllegalAccessError("geçersiz işlem");
    }


    @Override
    public ResponseEntity<Boolean> enable(@PathVariable(value = "id", required = true) Long id) {
        throw new IllegalAccessError("geçersiz işlem");
    }


    @Override
    public ResponseEntity<Boolean> disable(@PathVariable(value = "id", required = true) Long id) {
        throw new IllegalAccessError("geçersiz işlem");
    }

}
