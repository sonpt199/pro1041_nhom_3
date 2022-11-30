package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.dto.HoaDonChiTietDto;

/**
 *
 * @author sonpt_ph19600
 */
public interface HoaDonChiTietService {
    
    List<HoaDonChiTietDto> getAllByIdHoaDon(UUID idHoaDon);
    
}
