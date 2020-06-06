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
import tr.com.heartsapiens.tedis.dto.TipCihazDto; 
import tr.com.heartsapiens.tedis.service.TipCihazService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipCihazServiceTest {

    @Autowired
    TipCihazService tipCihazService;

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

        TipCihazDto tipCihazDto = new TipCihazDto();

        tipCihazDto.setAciklama("aciklama");
        tipCihazDto.setAd("ad");
        tipCihazDto.setModel("model");
        

        TipCihazDto result = tipCihazService.save(tipCihazDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAd(), tipCihazDto.getAd());

    }

    public void update() {

        TipCihazDto result = tipCihazService.getById(lastIndex);
        System.out.println(result);

        result.setModel("guncelledik");
        tipCihazService.update(lastIndex, result);

        TipCihazDto result2 = tipCihazService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getModel(), result.getModel());

    }

    private void disable() {
        TipCihazDto result = tipCihazService.getById(lastIndex);
        tipCihazService.disable(result.getId());
    }

    private void enable() {
        TipCihazDto result = tipCihazService.getById(lastIndex);
        tipCihazService.enable(result.getId());
    }

    public void delete() {
        TipCihazDto result = tipCihazService.getById(lastIndex);
        tipCihazService.delete(result.getId());

    }

}
