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
public class NitGozlemDegerDto  implements IBaseDto {

    private Long id;
    
    
    TipGozlemDto gozlemTipi;
    
    
    //HastaDto hasta;
    Long hastaId;

    public Object getVal(){
        if ( strDeger != null){
            return strDeger;
        }

        if ( blnDeger != null){
            return blnDeger;
        }
        if ( lngDeger != null){
            return lngDeger;
        }

        if ( dblDeger != null){
            return dblDeger;
        }

        if ( timeDeger != null){
            return timeDeger;
        }


        return  null;

    }
    
    String strDeger;
    Boolean blnDeger;
    Long lngDeger;
    Double dblDeger;
    Date timeDeger;
    
  
    private Date gozlemZamani;
    private Date kayitZaman;


    private Boolean durum;

}
