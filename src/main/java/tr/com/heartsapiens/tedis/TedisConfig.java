/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tr.com.heartsapiens.tedis.util.Validator;

import java.text.SimpleDateFormat;

/**
 *
 * @author ersin
 */
@Configuration
public class TedisConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator getValidator() {
        return new Validator();
    }


    @Bean(value = "sdfTimeStamp")
    public SimpleDateFormat sdfTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }


    @Bean(value = "sdfDate")
    public SimpleDateFormat getSdfDate() {
        return new SimpleDateFormat("yyyy-MM-dd");

    }

}
