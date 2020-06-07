/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 *
 */
@Data
public class NitHastaKomplikasyonDto implements IBaseDto {

    private Long id;

    @NotNull
    private Long hastaId;

    @NotNull
    private TipOlayDto olay;
    private String olayAciklama;
    private Date olayZaman;

    private String tedaviAciklama;
    private TipTedaviSonucDto tedaviSonuc;

    private Date tedaviZaman;
    private Date sonucZaman;


    private Boolean durum;
}
