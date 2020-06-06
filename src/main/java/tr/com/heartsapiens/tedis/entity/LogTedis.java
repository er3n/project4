/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ersin
 */
@Entity
@Table(name = "tedis_log", schema = "app_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogTedis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "exception_string")
    private String exceptionString;

    @Column(name = "remote_ip")
    private String remoteIp;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "op_duration")
    private Long opDuration;

    @Column(name = "op_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date opTime;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "result",columnDefinition = "text")
    private String result;

    @Column(name = "ui_user")
    private String user;

    @Column(name = "hasta_id")
    private Long hastaId;





}
