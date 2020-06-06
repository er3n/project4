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
import tr.com.heartsapiens.tedis.dto.SecProfileDto; 
import tr.com.heartsapiens.tedis.service.SecProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecProfileServiceTest {

    @Autowired
    SecProfileService secProfileService;

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

        SecProfileDto secProfileDto = new SecProfileDto();

        secProfileDto.setName("PROFILE 1");

        SecProfileDto result = secProfileService.save(secProfileDto);

        System.out.println(result);

        lastIndex = result.getId();

        Assert.assertEquals(result.getName(), secProfileDto.getName());

    }

    public void update() {

        SecProfileDto result = secProfileService.getById(lastIndex);
        System.out.println(result);

        result.setName("Güncelledik");
        secProfileService.update(lastIndex, result);

        SecProfileDto result2 = secProfileService.getById(lastIndex);

        System.out.println(result2);
        Assert.assertEquals(result2.getName(), result.getName());

    }

    private void disable() {
        SecProfileDto result = secProfileService.getById(lastIndex);
        secProfileService.disable(result.getId());
    }

    private void enable() {
        SecProfileDto result = secProfileService.getById(lastIndex);
        secProfileService.enable(result.getId());
    }

    public void delete() {
        SecProfileDto result = secProfileService.getById(lastIndex);
        secProfileService.delete(result.getId());

    }

}
