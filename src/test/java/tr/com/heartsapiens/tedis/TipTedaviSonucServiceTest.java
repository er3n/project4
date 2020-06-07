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
import tr.com.heartsapiens.tedis.dto.TipTedaviSonucDto;
import tr.com.heartsapiens.tedis.service.TipTedaviSonucService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipTedaviSonucServiceTest {

    @Autowired
    TipTedaviSonucService tipTedaviSonucService;

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

        TipTedaviSonucDto tipTedaviSonucDto = new TipTedaviSonucDto();

        tipTedaviSonucDto.setAd("ad");
        tipTedaviSonucDto.setCihazSonlandirir(true);
        tipTedaviSonucDto.setHastaPasifEder(true);
        tipTedaviSonucDto.setKod("kod1");

        TipTedaviSonucDto result = tipTedaviSonucService.save(tipTedaviSonucDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAd(), tipTedaviSonucDto.getAd());

    }

    public void update() {

        TipTedaviSonucDto result = tipTedaviSonucService.getById(lastIndex);
        System.out.println(result);

           result.setAd("ad 2");
        result.setCihazSonlandirir(false);
        result.setHastaPasifEder(false);
        result.setKod("kod 2");
        tipTedaviSonucService.update(lastIndex, result);

        TipTedaviSonucDto result2 = tipTedaviSonucService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getAd(), result.getAd());

    }

    private void disable() {
        TipTedaviSonucDto result = tipTedaviSonucService.getById(lastIndex);
        tipTedaviSonucService.disable(result.getId());
    }

    private void enable() {
        TipTedaviSonucDto result = tipTedaviSonucService.getById(lastIndex);
        tipTedaviSonucService.enable(result.getId());
    }

    public void delete() {
        TipTedaviSonucDto result = tipTedaviSonucService.getById(lastIndex);
        tipTedaviSonucService.delete(result.getId());

    }

}
