package tr.com.heartsapiens.tedis.service;

import org.springframework.data.domain.Pageable;
import tr.com.heartsapiens.tedis.dto.HastaDto;

import java.util.Date;

/**
 *
 * @author ersin
 */
public interface HastaService extends BaseService<HastaDto, Long>{

    public TPage<HastaDto> getAllByKurumKurulusId(Long kurumKurulusId, Pageable pageable);

    TPage<HastaDto> findByParameters(Pageable pageable, Long ptcno, String pad,
                                     String psoyad, String pemail, String ptel, Date pdogtar,
                                     String aciklama, String uyruk, Boolean durum, Long kurumId);
}