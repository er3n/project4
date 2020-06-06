/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ersin
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable{
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "olusturma_zaman")
    private Date olusturmaZaman=new Date();

    @Column(name = "olusturan", length = 50)
    private String olusturan;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "guncelleme_zaman")
    private Date guncellemeZaman;

    @Column(name = "guncelleyen", length = 50)
    private String guncelleyen;

    @Column(name = "durum")
    private Boolean durum;
}
