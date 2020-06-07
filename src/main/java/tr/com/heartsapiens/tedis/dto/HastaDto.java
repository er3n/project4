/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data; 

/**
 * @author ersince
 *
 */
@Data
public class HastaDto implements IBaseDto {
    

    private Long id;

    @NotNull
    private Long tcno;

    @NotNull
    @NotEmpty
    private String ad;

    @NotNull
    @Size(min = 2)
    private String soyad;

    @NotNull
    private Date dogtar;

    @NotNull
    private String uyruk;

    @NotNull
    private String aciklama;

    @NotNull
    private String tel;

    @NotNull
    private String email;

    
    @NotNull
    private KurumKurulusDto kurumKurulus;

    @NotNull
    private Boolean durum;
}
