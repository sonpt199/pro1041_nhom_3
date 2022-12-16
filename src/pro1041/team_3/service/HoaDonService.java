package pro1041.team_3.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.dto.HoaDonDto;

/**
 *
 * @author sonpt_ph19600
 */
public interface HoaDonService {

    List<HoaDonDto> getAllResponse();

    List<HoaDonDto> findHoaDonByKhachHang(String maKH);
    
    List<HoaDonDto> findHoaDon(String key);
    
    List<HoaDonDto> locHinhThucThanhToan(Integer httt);
    
    List<HoaDonDto> locHoaDonTheoNgay(Date ngay1, Date ngay2);
    
    List<HoaDonDto> locHoaDonTheoTongTien(BigDecimal tien1, BigDecimal tien2);
    
    Boolean exportPdf(String path, UUID idHoaDon);
    
    HoaDonDto findHoaDonForNhanVien(String key);
    
}
