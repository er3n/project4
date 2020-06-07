/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tr.com.heartsapiens.tedis.dto.HastaDto;
import tr.com.heartsapiens.tedis.dto.NitGozlemDegerDto;
import tr.com.heartsapiens.tedis.dto.TipGozlemDto;
import tr.com.heartsapiens.tedis.service.NitGozlemDegerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NitGozlemDegerServiceTest {

    @Autowired
    NitGozlemDegerService nitGozlemDegerService;

    public static Long lastIndex = 0L;

    @Test
    public void main() throws ParseException {
        create();
        update();
        disable();
        enable();
        delete();
    }

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");

    public void create() throws ParseException {

        NitGozlemDegerDto nitGozlemDegerDto = new NitGozlemDegerDto();

        nitGozlemDegerDto.setBlnDeger(Boolean.TRUE);
        nitGozlemDegerDto.setDblDeger(1.2D);
        
        TipGozlemDto gozlemTipi = new TipGozlemDto();
        gozlemTipi.setId(1L);
        nitGozlemDegerDto.setGozlemTipi(gozlemTipi);
        nitGozlemDegerDto.setGozlemZamani(new Date());
        
        HastaDto hastaDto = new HastaDto();
        hastaDto.setId(1L);
        
        nitGozlemDegerDto.setHastaId(hastaDto.getId());
        nitGozlemDegerDto.setKayitZaman(new Date());
        nitGozlemDegerDto.setLngDeger(1L);
        nitGozlemDegerDto.setStrDeger("Hello World");
        nitGozlemDegerDto.setTimeDeger(new Date());

        NitGozlemDegerDto result = nitGozlemDegerService.save(nitGozlemDegerDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getLngDeger(), nitGozlemDegerDto.getLngDeger());

    }

    public void update() {

        NitGozlemDegerDto result = nitGozlemDegerService.getById(lastIndex);
        System.out.println(result);

        result.setLngDeger(2L);
        nitGozlemDegerService.update(lastIndex, result);

        NitGozlemDegerDto result2 = nitGozlemDegerService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getLngDeger(), result.getLngDeger());

    }

    private void disable() {
        NitGozlemDegerDto result = nitGozlemDegerService.getById(lastIndex);
        nitGozlemDegerService.disable(result.getId());
    }

    private void enable() {
        NitGozlemDegerDto result = nitGozlemDegerService.getById(lastIndex);
        nitGozlemDegerService.enable(result.getId());
    }

    public void delete() {
        NitGozlemDegerDto result = nitGozlemDegerService.getById(lastIndex);
        nitGozlemDegerService.delete(result.getId());

    }

}
