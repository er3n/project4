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
import tr.com.heartsapiens.tedis.dto.NitHastaKomplikasyonDto; 
import tr.com.heartsapiens.tedis.dto.TipOlayDto;
import tr.com.heartsapiens.tedis.dto.TipTedaviSonucDto;
import tr.com.heartsapiens.tedis.service.NitHastaKomplikasyonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NitHastaKomplikasyonServiceTest {

    @Autowired
    NitHastaKomplikasyonService nitHastaKomplikasyonService;

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

        NitHastaKomplikasyonDto nitHastaKomplikasyonDto = new NitHastaKomplikasyonDto();

        HastaDto hasta = new HastaDto();
        hasta.setId(1L);
        
        nitHastaKomplikasyonDto.setHastaId(hasta.getId());
        
        TipOlayDto tipOlayDto = new TipOlayDto();
        tipOlayDto.setId(1L);
        
        nitHastaKomplikasyonDto.setOlay(tipOlayDto);
        
        nitHastaKomplikasyonDto.setOlayAciklama("Olay Açıklama");
        nitHastaKomplikasyonDto.setOlayZaman(new Date());
        nitHastaKomplikasyonDto.setTedaviAciklama("Tedavi aciklama");
        
        TipTedaviSonucDto tipTedaviSonucDto = new TipTedaviSonucDto();
        tipTedaviSonucDto.setId(1L);
                
        nitHastaKomplikasyonDto.setTedaviSonuc(tipTedaviSonucDto);

        NitHastaKomplikasyonDto result = nitHastaKomplikasyonService.save(nitHastaKomplikasyonDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getTedaviSonuc().getId(), nitHastaKomplikasyonDto.getTedaviSonuc().getId());

    }

    public void update() {

        NitHastaKomplikasyonDto result = nitHastaKomplikasyonService.getById(lastIndex);
        System.out.println(result);

        result.setOlayAciklama("Güncelledik");
        nitHastaKomplikasyonService.update(lastIndex, result);

        NitHastaKomplikasyonDto result2 = nitHastaKomplikasyonService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getOlayAciklama(), result.getOlayAciklama());

    }

    private void disable() {
        NitHastaKomplikasyonDto result = nitHastaKomplikasyonService.getById(lastIndex);
        nitHastaKomplikasyonService.disable(result.getId());
    }

    private void enable() {
        NitHastaKomplikasyonDto result = nitHastaKomplikasyonService.getById(lastIndex);
        nitHastaKomplikasyonService.enable(result.getId());
    }

    public void delete() {
        NitHastaKomplikasyonDto result = nitHastaKomplikasyonService.getById(lastIndex);
        nitHastaKomplikasyonService.delete(result.getId());

    }

}
