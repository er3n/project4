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
@Table(name = "hasta_cihaz", schema = "tedis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class NitHastaCihaz extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "cihaz_tip_id")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    TipCihaz cihazTipi;

    @JoinColumn(name = "hasta_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Hasta hasta;
    
    
    @JoinColumn(name = "kurumkurulus_id")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    KurumKurulus kurumKurulus;
    

    @Column(name = "seri_no", length = 255, nullable = false)
    String seriNo;

    @Column(name = "aciklama", length = 1024)
    String aciklama;

    @Column(name = "sonlandirma_gerekce", length = 255)
    String sonlandirmaGerekcesi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "baslangic_zamani")
    private Date baslangicZamani;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sonlanma_zamani")
    private Date sonlanmaZamani;

}
