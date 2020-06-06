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
@Table(name = "zamanli_mesaj_alici", schema = "mesaj")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class MsgZamanliMesajAlici extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hasta_id")
    Hasta hasta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesaj_kalip_id")
    MsgZamanliMesajKalip mesajKalip;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gerceklesme_zamani")
    private Date gerceklesmeZamani;

    @Column(name = "comm_status") 
    String commStatus;

}
