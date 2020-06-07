/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tr.com.heartsapiens.tedis.dto.NitHastaKomplikasyonDto;
import tr.com.heartsapiens.tedis.dto.NitKompliksyonTedaviDto;
import tr.com.heartsapiens.tedis.dto.TipTedaviDto;
import tr.com.heartsapiens.tedis.service.NitKompliksyonTedaviService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NitKompliksyonTedaviServiceTest {

    @Autowired
    NitKompliksyonTedaviService nitKompliksyonTedaviService;

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

        NitKompliksyonTedaviDto nitKompliksyonTedaviDto = new NitKompliksyonTedaviDto();

        NitHastaKomplikasyonDto nitHastaKomplikasyon = new NitHastaKomplikasyonDto();
        nitHastaKomplikasyon.setId(1L);
        
        nitKompliksyonTedaviDto.setNitHastaKomplikasyon(nitHastaKomplikasyon);
        nitKompliksyonTedaviDto.setTedaviAciklama("TEdavi açıklama");
        TipTedaviDto tipTedaviDto = new TipTedaviDto();
        tipTedaviDto.setId(1L);
        nitKompliksyonTedaviDto.setTipTedavi(tipTedaviDto);

        NitKompliksyonTedaviDto result = nitKompliksyonTedaviService.save(nitKompliksyonTedaviDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getTedaviAciklama(), nitKompliksyonTedaviDto.getTedaviAciklama());

    }

    public void update() {

        NitKompliksyonTedaviDto result = nitKompliksyonTedaviService.getById(lastIndex);
        System.out.println(result);

        result.setTedaviAciklama("Güncelledik");
        nitKompliksyonTedaviService.update(lastIndex, result);

        NitKompliksyonTedaviDto result2 = nitKompliksyonTedaviService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getTedaviAciklama(), result.getTedaviAciklama());

    }

    private void disable() {
        NitKompliksyonTedaviDto result = nitKompliksyonTedaviService.getById(lastIndex);
        nitKompliksyonTedaviService.disable(result.getId());
    }

    private void enable() {
        NitKompliksyonTedaviDto result = nitKompliksyonTedaviService.getById(lastIndex);
        nitKompliksyonTedaviService.enable(result.getId());
    }

    public void delete() {
        NitKompliksyonTedaviDto result = nitKompliksyonTedaviService.getById(lastIndex);
        nitKompliksyonTedaviService.delete(result.getId());

    }

}
