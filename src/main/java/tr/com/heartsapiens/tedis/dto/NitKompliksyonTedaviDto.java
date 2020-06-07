/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import lombok.Data;


@Data
public class NitKompliksyonTedaviDto  implements IBaseDto {
    private Long id;    
    private NitHastaKomplikasyonDto nitHastaKomplikasyon;
    private TipTedaviDto tipTedavi;
    private String tedaviAciklama;

    private Boolean durum;
}
