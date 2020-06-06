/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.Date;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * 
 */
@Data
public class MsgZamanliMesajKalipDto {
     
    
    private Long id;
    
    @Pattern(regexp = ".+")
    private String baslik;
    
    @Pattern(regexp = ".+")
    private String icerik;
    
    private Date basGonderiZamani;
    private Date sonGonderiZamani;
    
    
    @Pattern(regexp = ".+")
    private String ayDowGunSaatDakikaPattern;
    private String notIn;
    
    
    @Pattern(regexp = ".+")
    private String queryhql;


    private Boolean durum;
    
}
