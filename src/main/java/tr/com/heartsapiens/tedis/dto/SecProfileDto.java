/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import lombok.Data;

/**
 *
 *
 */
@Data
public class SecProfileDto {

    private Long id;
    private String name;
    private  String aciklama;
    private Boolean durum;
}
