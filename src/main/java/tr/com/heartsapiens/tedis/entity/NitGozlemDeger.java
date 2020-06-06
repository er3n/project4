/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ersin
 */
@Entity
@Table(name = "gozlem_deger", schema = "tedis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class NitGozlemDeger extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "gozlem_tip_id")
    @ManyToOne(fetch = FetchType.LAZY)
    TipGozlem gozlemTipi;

    @JoinColumn(name = "hasta_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Hasta hasta;

    @Column(name = "str_deger", length = 255, nullable = true)
    String strDeger;

    @Column(name = "bln_deger", nullable = true)
    Boolean blnDeger;

    @Column(name = "lng_deger", nullable = true)
    Long lngDeger;

    @Column(name = "dbl_deger", nullable = true)
    Double dblDeger;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tme_deger", nullable = true)
    Date tmeDeger;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gozlem_zamani")
    private Date gozlemZamani;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "kayit_zamani")
    private Date kayitZaman;

}
