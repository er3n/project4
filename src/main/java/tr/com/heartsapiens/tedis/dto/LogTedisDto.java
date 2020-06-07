/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.Date;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 *
 */
@Data
public class LogTedisDto  implements IBaseDto {

    private Long id;

    @Pattern(regexp = ".+")
    private String exceptionString;
    
    @Pattern(regexp = ".+")
    private String remoteIp;

    @Pattern(regexp = ".+")
    private String methodName;
    
    private Long opDuration;
    private Date opTime;
    
    private String parameters;
    private String result;

    @Pattern(regexp = ".+")
    private String user;


    private Boolean durum;

    private Boolean hastaId;

}
