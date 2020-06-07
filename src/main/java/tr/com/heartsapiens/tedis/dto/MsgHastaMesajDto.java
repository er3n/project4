/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data; 

/**
 *
 * 
 */
@Data
public class MsgHastaMesajDto  implements IBaseDto {
    
    private Long id;
    
    @Pattern(regexp = ".+")
    private String baslik;
    
    @Pattern(regexp = ".+")
    private String icerik;
    
    @NotNull
    private Date basGonderiZamani;
    
    @NotNull
    private Date sonGonderiZamani;
    
    private Date gerceklesmeZamani;
    
    @NotNull
    private HastaDto hasta;
    
    @NotNull
    private String commStatus;


    private Boolean durum;
}
