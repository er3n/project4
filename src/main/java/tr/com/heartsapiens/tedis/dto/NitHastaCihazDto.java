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
public class NitHastaCihazDto {
    private Long id;
    
    @NotNull
    TipCihazDto cihazTipi;
    
    //@NotNull
    //HastaDto hasta;
    @NotNull
    Long hastaId;
    
    @NotNull
    KurumKurulusDto kurumKurulus;
            
    
    @Pattern(regexp = ".+")
    String seriNo;
    
    String aciklama;
    
    String sonlandirmaGerekcesi;
    
    @NotNull
    private Date baslangicZamani;
    private Date sonlanmaZamani;


    private Boolean durum;
}
