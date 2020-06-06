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
import tr.com.heartsapiens.tedis.dto.SecUserDto; 
import tr.com.heartsapiens.tedis.entity.KurumKurulus;
import tr.com.heartsapiens.tedis.service.SecUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecUserServiceTest {

    @Autowired
    SecUserService secUserService;

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

        SecUserDto secUserDto = new SecUserDto();

        secUserDto.setEmail("email");
        secUserDto.setName("name");
        secUserDto.setPassword("password");
        secUserDto.setSurname("surname");
        secUserDto.setUsername("username_" + System.currentTimeMillis());
        
        KurumKurulusDto kurumKurulus = new KurumKurulusDto();
        kurumKurulus.setId(1L);
        secUserDto.setKurumKurulus(kurumKurulus);

        SecUserDto result = secUserService.save(secUserDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getId(), secUserDto.getId());

    }

    public void update() {

        SecUserDto result = secUserService.getById(lastIndex);
        System.out.println(result);

        result.setEmail("abcde");
        secUserService.update(lastIndex, result);

        SecUserDto result2 = secUserService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getEmail(), result.getEmail());

    }

    private void disable() {
        SecUserDto result = secUserService.getById(lastIndex);
        secUserService.disable(result.getId());
    }

    private void enable() {
        SecUserDto result = secUserService.getById(lastIndex);
        secUserService.enable(result.getId());
    }

    public void delete() {
        SecUserDto result = secUserService.getById(lastIndex);
        secUserService.delete(result.getId());

    }

}
