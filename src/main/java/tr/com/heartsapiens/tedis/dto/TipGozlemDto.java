/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 *
 */
@Data
public class TipGozlemDto {

    private Long id;
 
    private String ad;
    private String aciklama;
 
    private String veriTip; // BOOLEAN, LONG, DOUBLE, STRING
    
    private String checkJs;


    private Boolean durum;
}