/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author ersin
 */
public interface BaseService<T, D> {

    T getById(D id);

    T save(T entity);

    Boolean delete(Long id);

    T update(Long id, T entity);

    TPage<T> getAll(Pageable pageable);
    
    Boolean enable(Long id);
    
    Boolean disable(Long id);
    
}
