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
import tr.com.heartsapiens.tedis.dto.KurumKurulusDto; 
import tr.com.heartsapiens.tedis.service.KurumKurulusService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KurumKurulusTest {

    @Autowired
    KurumKurulusService kurumKurulusService;

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

        KurumKurulusDto kurumKurulusDto = new KurumKurulusDto();

        kurumKurulusDto.setAdres("Yeni Kurum");
        kurumKurulusDto.setAd("Test Kurum 1");
        kurumKurulusDto.setKod("Kod 1");
        kurumKurulusDto.setTel("0 (321) 111 2233");

        KurumKurulusDto result = kurumKurulusService.save(kurumKurulusDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAd(), kurumKurulusDto.getAd());

    }

    public void update() {

        KurumKurulusDto result = kurumKurulusService.getById(lastIndex);
        System.out.println(result);

        result.setAdres("Güncelledik");
        kurumKurulusService.update(lastIndex, result);

        KurumKurulusDto result2 = kurumKurulusService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getAdres(), result.getAdres());

    }

    private void disable() {
        KurumKurulusDto result = kurumKurulusService.getById(lastIndex);
        kurumKurulusService.disable(result.getId());
    }

    private void enable() {
        KurumKurulusDto result = kurumKurulusService.getById(lastIndex);
        kurumKurulusService.enable(result.getId());
    }

    public void delete() {
        KurumKurulusDto result = kurumKurulusService.getById(lastIndex);
        kurumKurulusService.delete(result.getId());

    }

}
