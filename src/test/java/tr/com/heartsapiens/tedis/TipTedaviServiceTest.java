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
import tr.com.heartsapiens.tedis.dto.TipTedaviDto; 
import tr.com.heartsapiens.tedis.service.TipTedaviService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TipTedaviServiceTest {

    @Autowired
    TipTedaviService tipTedaviService;

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

        TipTedaviDto tipTedaviDto = new TipTedaviDto();

        tipTedaviDto.setAd("ad1");
        tipTedaviDto.setKod("kod1");
        
        TipTedaviDto result = tipTedaviService.save(tipTedaviDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAd(), tipTedaviDto.getAd());

    }

    public void update() {

        TipTedaviDto result = tipTedaviService.getById(lastIndex);
        System.out.println(result);

        result.setAd("ad1");
        result.setKod("kod1");
        tipTedaviService.update(lastIndex, result);

        TipTedaviDto result2 = tipTedaviService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getAd(), result.getAd());

    }

    private void disable() {
        TipTedaviDto result = tipTedaviService.getById(lastIndex);
        tipTedaviService.disable(result.getId());
    }

    private void enable() {
        TipTedaviDto result = tipTedaviService.getById(lastIndex);
        tipTedaviService.enable(result.getId());
    }

    public void delete() {
        TipTedaviDto result = tipTedaviService.getById(lastIndex);
        tipTedaviService.delete(result.getId());

    }

}
