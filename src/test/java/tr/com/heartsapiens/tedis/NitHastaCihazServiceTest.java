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
import tr.com.heartsapiens.tedis.dto.KurumKurulusDto;
import tr.com.heartsapiens.tedis.dto.NitHastaCihazDto;
import tr.com.heartsapiens.tedis.dto.TipCihazDto;
import tr.com.heartsapiens.tedis.service.NitHastaCihazService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NitHastaCihazServiceTest {

    @Autowired
    NitHastaCihazService nitHastaCihazService;

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

        NitHastaCihazDto nitHastaCihazDto = new NitHastaCihazDto();

        nitHastaCihazDto.setAciklama("AÇIKLAMA");
        nitHastaCihazDto.setBaslangicZamani(new Date());
        TipCihazDto cihazTipi = new TipCihazDto();
        cihazTipi.setId(1L);
        nitHastaCihazDto.setCihazTipi(cihazTipi);
        
        HastaDto hasta = new HastaDto();
        hasta.setId(1L);
        
        nitHastaCihazDto.setHastaId(hasta.getId());

        KurumKurulusDto kurumKurulus = new KurumKurulusDto();
        kurumKurulus.setId(1L);
        
        nitHastaCihazDto.setKurumKurulus(kurumKurulus);
        nitHastaCihazDto.setSeriNo("12345"); 
        
        
        
        NitHastaCihazDto result = nitHastaCihazService.save(nitHastaCihazDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getHastaId(), nitHastaCihazDto.getHastaId());

    }

    public void update() {

        NitHastaCihazDto result = nitHastaCihazService.getById(lastIndex);
        System.out.println(result);

        result.setAciklama("AÇIKLAMA");
        nitHastaCihazService.update(lastIndex, result);

        NitHastaCihazDto result2 = nitHastaCihazService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getAciklama(), result.getAciklama());

    }

    private void disable() {
        NitHastaCihazDto result = nitHastaCihazService.getById(lastIndex);
        nitHastaCihazService.disable(result.getId());
    }

    private void enable() {
        NitHastaCihazDto result = nitHastaCihazService.getById(lastIndex);
        nitHastaCihazService.enable(result.getId());
    }

    public void delete() {
        NitHastaCihazDto result = nitHastaCihazService.getById(lastIndex);
        nitHastaCihazService.delete(result.getId());

    }

}
