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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "hasta", schema = "tedis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Hasta extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tcno")
    private Long tcno;

    @Column(name = "ad", length = 30)
    private String ad;

    @Column(name = "soyad", length = 30)
    private String soyad;

    @Temporal(TemporalType.DATE)
    @Column(name = "dogtar")
    private Date dogtar;

    @Column(name = "tel")
    private String tel;


    @Column(name = "email")
    private String email;

    @Column(name = "uyruk")
    private String uyruk;


    @Column(name = "aciklama", length = 1000)
    private String aciklama;

    
    @JoinColumn(name = "kurum_kurulus_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private KurumKurulus kurumKurulus;

}
