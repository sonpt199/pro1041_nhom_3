package pro1041.team_3.service;

import java.util.List;
import pro1041.team_3.dto.HoaDonDto;

/**
 *
 * @author sonpt_ph19600
 */
public interface HoaDonService {

    List<HoaDonDto> getAllResponse();

    List<HoaDonDto> findHoaDonByKhachHang(String maKH);
    
}
