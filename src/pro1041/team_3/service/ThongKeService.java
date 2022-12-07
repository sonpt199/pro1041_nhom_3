package pro1041.team_3.service;

import java.util.Date;
import java.util.List;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.DoanhThuTheoThangDto;

/**
 *
 * @author sonhn
 */
public interface ThongKeService {
    
    List<DoanhThuTheoThangDto> theoThang(int month, int year);
    
    DoanhThuTheoThangDto theoNgay(int day, int month, int year);
    
}
