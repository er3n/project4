/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.entity;

import java.util.Date;
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
@Table(name = "hasta_mesaj", schema = "mesaj")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class MsgHastaMesaj extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "baslik", length = 100)
    private String baslik;

    @Column(name = "icerik", length = 255)
    private String icerik;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bas_gonderi_zamani")
    private Date basGonderiZamani;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "son_gonderi_zamani")
    private Date sonGonderiZamani;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gerceklesme_zamani")
    private Date gerceklesmeZamani;

    @JoinColumn(name = "hasta_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hasta hasta;

    @Column(name = "comm_status")
    private String commStatus;

}
