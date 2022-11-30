package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.GioHangChiTietDto;

/**
 *
 * @author sonpt_ph19600
 */
public interface GioHangChiTietService {
    
    List<BhChiTietDienThoaiDto> getAllByIdGioHang(UUID idGioHang);
    
    public boolean deteleByIdGioHang(UUID idGioHang);
    
}
