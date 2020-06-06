/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.api;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import tr.com.heartsapiens.tedis.service.TPage;

/**
 *
 * ersin
 */
public interface BaseRest<DTO, ITYPE> {

    public ResponseEntity<TPage<DTO>> getAllByPagination(Pageable pageable);

    public ResponseEntity<DTO> getById(Long id);

    public ResponseEntity<DTO> create(DTO entity);

    public ResponseEntity<DTO> update(Long id, DTO entity);

    public ResponseEntity<Boolean> delete(Long id);

    public ResponseEntity<Boolean> enable(Long id);

    public ResponseEntity<Boolean> disable(Long id);

}
