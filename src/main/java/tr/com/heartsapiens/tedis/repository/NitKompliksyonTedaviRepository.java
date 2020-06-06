/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.heartsapiens.tedis.entity.NitKomplikasyonTedavi;

/**
 *
 * @author ersin
 */
public interface NitKompliksyonTedaviRepository  extends JpaRepository<NitKomplikasyonTedavi,Long>{
    
}
