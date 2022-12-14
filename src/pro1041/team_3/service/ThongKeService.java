package pro1041.team_3.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.HoaDonChiTiet;
import pro1041.team_3.dto.DoanhThuCaNhanDto;
import pro1041.team_3.dto.DoanhThuTheoNgayDto;
import pro1041.team_3.dto.ThongKeTheoHang;

/**
 *
 * @author sonhn
 */
public interface ThongKeService {
    
    List<DoanhThuTheoNgayDto> theoKhoangThoiGian(Date start, Date end);
    
    DoanhThuTheoNgayDto theoNgay(int day, int month, int year);
    
    List<DoanhThuCaNhanDto> getDoanhThuCaNhanTheoNgay(int day, int month, int year);
    
    DoanhThuTheoNgayDto theoThang(int month, int year);
    
    List<DoanhThuCaNhanDto> getDoanhThuCaNhanTheoThang(int month, int year);
    
    List<ThongKeTheoHang> thongKeTheohang(UUID idHang, Date start, Date end);
    
}
