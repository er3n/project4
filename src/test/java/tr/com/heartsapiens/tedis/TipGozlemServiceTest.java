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
import tr.com.heartsapiens.tedis.dto.TipGozlemDto; 
import tr.com.heartsapiens.tedis.service.TipGozlemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipGozlemServiceTest {

    @Autowired
    TipGozlemService tipGozlemService;

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

        TipGozlemDto tipGozlemDto = new TipGozlemDto();

        tipGozlemDto.setAciklama("aciklama");
        tipGozlemDto.setAd("ad");
        tipGozlemDto.setVeriTip("BOOLENA");

        TipGozlemDto result = tipGozlemService.save(tipGozlemDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAd(), tipGozlemDto.getAd());

    }

    public void update() {

        TipGozlemDto result = tipGozlemService.getById(lastIndex);
        System.out.println(result);

        result.setAciklama("aciklama 1");
        tipGozlemService.update(lastIndex, result);

        TipGozlemDto result2 = tipGozlemService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getAciklama(), result.getAciklama());

    }

    private void disable() {
        TipGozlemDto result = tipGozlemService.getById(lastIndex);
        tipGozlemService.disable(result.getId());
    }

    private void enable() {
        TipGozlemDto result = tipGozlemService.getById(lastIndex);
        tipGozlemService.enable(result.getId());
    }

    public void delete() {
        TipGozlemDto result = tipGozlemService.getById(lastIndex);
        tipGozlemService.delete(result.getId());

    }

}
