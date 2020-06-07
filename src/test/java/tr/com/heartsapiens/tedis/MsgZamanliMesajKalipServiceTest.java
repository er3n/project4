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
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajKalipDto;
import tr.com.heartsapiens.tedis.service.MsgZamanliMesajKalipService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgZamanliMesajKalipServiceTest {

    @Autowired
    MsgZamanliMesajKalipService msgZamanliMesajKalipService;

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

        MsgZamanliMesajKalipDto msgZamanliMesajKalipDto = new MsgZamanliMesajKalipDto();

        msgZamanliMesajKalipDto.setBaslik("Bir Başlık");
        msgZamanliMesajKalipDto.setBasGonderiZamani(new Date());
        msgZamanliMesajKalipDto.setIcerik("İÇERİK");
        msgZamanliMesajKalipDto.setNotIn("NOT IN");
        msgZamanliMesajKalipDto.setQueryhql("HQL");
        msgZamanliMesajKalipDto.setSonGonderiZamani(new Date());
        msgZamanliMesajKalipDto.setAyDowGunSaatDakikaPattern("* * * * *");
      

        MsgZamanliMesajKalipDto result = msgZamanliMesajKalipService.save(msgZamanliMesajKalipDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAyDowGunSaatDakikaPattern(), msgZamanliMesajKalipDto.getAyDowGunSaatDakikaPattern());

    }

    public void update() {

        MsgZamanliMesajKalipDto msgZamanliMesajKalipDto = msgZamanliMesajKalipService.getById(lastIndex);
        
           msgZamanliMesajKalipDto.setBaslik("Bir Başlık");
        msgZamanliMesajKalipDto.setBasGonderiZamani(new Date());
        msgZamanliMesajKalipDto.setIcerik("İÇERİK");
        msgZamanliMesajKalipDto.setNotIn("NOT IN");
        msgZamanliMesajKalipDto.setQueryhql("HQL 2");
        msgZamanliMesajKalipDto.setSonGonderiZamani(new Date());
        msgZamanliMesajKalipDto.setAyDowGunSaatDakikaPattern("* * * * *");
        
        msgZamanliMesajKalipService.update(lastIndex, msgZamanliMesajKalipDto);

        MsgZamanliMesajKalipDto result2 = msgZamanliMesajKalipService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getQueryhql(), msgZamanliMesajKalipDto.getQueryhql());

    }

    private void disable() {
        MsgZamanliMesajKalipDto result = msgZamanliMesajKalipService.getById(lastIndex);
        msgZamanliMesajKalipService.disable(result.getId());
    }

    private void enable() {
        MsgZamanliMesajKalipDto result = msgZamanliMesajKalipService.getById(lastIndex);
        msgZamanliMesajKalipService.enable(result.getId());
    }

    public void delete() {
        MsgZamanliMesajKalipDto result = msgZamanliMesajKalipService.getById(lastIndex);
        msgZamanliMesajKalipService.delete(result.getId());

    }

}
