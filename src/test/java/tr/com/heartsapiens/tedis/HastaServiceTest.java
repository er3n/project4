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
import tr.com.heartsapiens.tedis.dto.KurumKurulusDto; 
import tr.com.heartsapiens.tedis.service.HastaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HastaServiceTest {

    @Autowired
    HastaService hastaService;

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

        HastaDto hastaDto = new HastaDto();
      
        
        hastaDto.setAciklama("Yeni Kişi");
        hastaDto.setAd("Ersin");
        hastaDto.setDogtar(sdf.parse("1976-06-27 00:00:00"));
        hastaDto.setSoyad("Aksoy");
        hastaDto.setTcno(1234567L);
        hastaDto.setUyruk("T.C");

        KurumKurulusDto kkd = new KurumKurulusDto();
        kkd.setId(1L);
        hastaDto.setKurumKurulus(kkd);

        HastaDto result = hastaService.save(hastaDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getAd(), hastaDto.getAd());
        Assert.assertEquals(result.getKurumKurulus().getId(), hastaDto.getKurumKurulus().getId());

    }

    public void update() {

        HastaDto result = hastaService.getById(lastIndex);
        System.out.println(result);

       result.setAciklama("Güncelledik");
        hastaService.update(lastIndex, result);

        HastaDto result2 = hastaService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getAciklama(), result.getAciklama());

    }

    private void disable() {
        HastaDto result = hastaService.getById(lastIndex);
        hastaService.disable(result.getId());
    }

    private void enable() {
        HastaDto result = hastaService.getById(lastIndex);
        hastaService.enable(result.getId());
    }

    public void delete() {
        HastaDto result = hastaService.getById(lastIndex);
        hastaService.delete(result.getId());

    }

}
