/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "hasta_komplikasyon", schema = "tedis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class NitHastaKomplikasyon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "hasta_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hasta hasta;

    @JoinColumn(name = "olay_tip_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipOlay olay;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "olay_zamani")
    private Date olayZaman;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tedavi_zamani")
    private Date tedaviZaman;
    
    @Column(name = "olay_aciklama", length = 1024)
    private String olayAciklama;
        

    @JoinColumn(name = "tedavi_sonuc_tip_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipTedaviSonuc tedaviSonuc;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sonuc_zamani")
    private Date sonucZaman;

}
