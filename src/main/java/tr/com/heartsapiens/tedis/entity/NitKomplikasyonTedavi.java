/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.entity;

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
@Table(name = "komplikasyon_tedavi", schema = "tedis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class NitKomplikasyonTedavi  extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "komplikasyon_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private NitHastaKomplikasyon nitHastaKomplikasyon;

    @JoinColumn(name = "tedavi_tip_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipTedavi tipTedavi;

    @Column(name = "tedavi_aciklama", length = 1024)
    private String tedaviAciklama;

}
