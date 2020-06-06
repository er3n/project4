/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 *
 */
@Data
public class SecUserDto {

    private Long id;

     private String username;
    
     private String password;
    
     private String name;
    
    private String surname;
    
    private String email;

    private List<SecProfileDto> profileList;

    private KurumKurulusDto kurumKurulus;

    private Boolean durum;
}
