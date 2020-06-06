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
import tr.com.heartsapiens.tedis.dto.TipOlayDto;
import tr.com.heartsapiens.tedis.service.TipOlayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipOlayServiceTest {

    @Autowired
    TipOlayService tipOlayService;

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

        TipOlayDto tipOlayDto = new TipOlayDto();

        tipOlayDto.setAd("ad 1");
        tipOlayDto.setKod("KOD 1");

        TipOlayDto result = tipOlayService.save(tipOlayDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAd(), tipOlayDto.getAd());

    }

    public void update() {

        TipOlayDto result = tipOlayService.getById(lastIndex);
        System.out.println(result);

        result.setAd("ad 2");
        result.setKod("KOD 2");

        tipOlayService.update(lastIndex, result);

        TipOlayDto result2 = tipOlayService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getKod(), result.getKod());

    }

    private void disable() {
        TipOlayDto result = tipOlayService.getById(lastIndex);
        tipOlayService.disable(result.getId());
    }

    private void enable() {
        TipOlayDto result = tipOlayService.getById(lastIndex);
        tipOlayService.enable(result.getId());
    }

    public void delete() {
        TipOlayDto result = tipOlayService.getById(lastIndex);
        tipOlayService.delete(result.getId());

    }

}
