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
import tr.com.heartsapiens.tedis.dto.HastaDto;
import tr.com.heartsapiens.tedis.dto.MsgHastaMesajDto;
import tr.com.heartsapiens.tedis.service.MsgHastaMesajService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgHastaMesajServiceTest {

    @Autowired
    MsgHastaMesajService msgHastaMesajService;

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

        MsgHastaMesajDto msgHastaMesajDto = new MsgHastaMesajDto();

        msgHastaMesajDto.setBaslik("Baslik");

        HastaDto hastaDto = new HastaDto();
        hastaDto.setId(1L);
        msgHastaMesajDto.setHasta(hastaDto);
        msgHastaMesajDto.setIcerik("Yeni içerik");
        msgHastaMesajDto.setCommStatus("BEKLIYOR");
        msgHastaMesajDto.setBasGonderiZamani(sdf.parse("2019-08-01 00:00:00"));
        msgHastaMesajDto.setSonGonderiZamani(sdf.parse("2019-08-01 11:00:00"));

        MsgHastaMesajDto result = msgHastaMesajService.save(msgHastaMesajDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getBaslik(), msgHastaMesajDto.getBaslik());
         Assert.assertEquals(result.getHasta().getId(), msgHastaMesajDto.getHasta().getId());

    }

    public void update() {

        MsgHastaMesajDto result = msgHastaMesajService.getById(lastIndex);
        System.out.println(result);

        result.setIcerik("günceledik içerik");
        msgHastaMesajService.update(lastIndex,result);

        MsgHastaMesajDto result2 = msgHastaMesajService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getIcerik(), result.getIcerik());

    }

    private void disable() {
        MsgHastaMesajDto result = msgHastaMesajService.getById(lastIndex);
        msgHastaMesajService.disable(result.getId());
    }

    private void enable() {
        MsgHastaMesajDto result = msgHastaMesajService.getById(lastIndex);
        msgHastaMesajService.enable(result.getId());
    }

    public void delete() {
        MsgHastaMesajDto result = msgHastaMesajService.getById(lastIndex);
        msgHastaMesajService.delete(result.getId());

    }

}
