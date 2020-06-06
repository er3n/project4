/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data; 

/**
 *
 * 
 */
@Data
public class MsgZamanliMesajAliciDto {
 
    private Long id;
    
    @NotNull
    private Long hastaId;
    
    @NotNull
    private MsgZamanliMesajKalipDto mesajKalip;
    
    
    private Date gerceklesmeZamani;
    
    @NotNull
    private String commStatus;


    private Boolean durum;
}
