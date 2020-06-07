/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 *
 */
@Data
public class TipTedaviDto  implements IBaseDto {
    private Long id;
    
     @Pattern(regexp = ".+")
    private String ad;
      @Pattern(regexp = ".+")
    private String kod;


    private Boolean durum;
}
