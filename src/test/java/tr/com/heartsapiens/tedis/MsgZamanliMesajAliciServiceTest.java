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
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajAliciDto;
import tr.com.heartsapiens.tedis.dto.MsgZamanliMesajKalipDto;
import tr.com.heartsapiens.tedis.service.MsgZamanliMesajAliciService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgZamanliMesajAliciServiceTest {

    @Autowired
    MsgZamanliMesajAliciService msgZamanliMesajAliciService;

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

        MsgZamanliMesajAliciDto msgZamanliMesajAliciDto = new MsgZamanliMesajAliciDto();

        HastaDto hastaDto = new HastaDto();
        hastaDto.setId(1L);

        MsgZamanliMesajKalipDto msgZamanliMesajKalipDto = new MsgZamanliMesajKalipDto();
        msgZamanliMesajKalipDto.setId(1L);

        msgZamanliMesajAliciDto.setHastaId(hastaDto.getId());
        msgZamanliMesajAliciDto.setCommStatus("BEKLIYOR");
        msgZamanliMesajAliciDto.setMesajKalip(msgZamanliMesajKalipDto);

        MsgZamanliMesajAliciDto result = msgZamanliMesajAliciService.save(msgZamanliMesajAliciDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getMesajKalip().getId(), msgZamanliMesajAliciDto.getMesajKalip().getId());
        Assert.assertEquals(result.getHastaId(), msgZamanliMesajAliciDto.getHastaId());

    }

    public void update() {

        MsgZamanliMesajAliciDto result = msgZamanliMesajAliciService.getById(lastIndex);
        System.out.println(result);

        HastaDto hastaDto = new HastaDto();
        hastaDto.setId(3L);
        result.setHastaId(hastaDto.getId());

        msgZamanliMesajAliciService.update(lastIndex, result);

        MsgZamanliMesajAliciDto result2 = msgZamanliMesajAliciService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getHastaId(), result.getHastaId());

    }

    private void disable() {
        MsgZamanliMesajAliciDto result = msgZamanliMesajAliciService.getById(lastIndex);
        msgZamanliMesajAliciService.disable(result.getId());
    }

    private void enable() {
        MsgZamanliMesajAliciDto result = msgZamanliMesajAliciService.getById(lastIndex);
        msgZamanliMesajAliciService.enable(result.getId());
    }

    public void delete() {
        MsgZamanliMesajAliciDto result = msgZamanliMesajAliciService.getById(lastIndex);
        msgZamanliMesajAliciService.delete(result.getId());

    }

}
