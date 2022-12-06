package pro1041.team_3.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.repository.HoaDonRepository;
import pro1041.team_3.service.HoaDonService;

/**
 *
 * @author sonpt_ph19600
 */
public class HoaDonServiceImpl implements HoaDonService{
    
    private HoaDonRepository hoaDonRepository;

    public HoaDonServiceImpl() {
        hoaDonRepository = new HoaDonRepository();
    }
        
    @Override
    public List<HoaDonDto> getAllResponse() {
        return hoaDonRepository.getAllResponse();
    }

    @Override
    public List<HoaDonDto> findHoaDonByKhachHang(String maKH) {
        return hoaDonRepository.findHoaDonByKhachHang(maKH);
    }

    @Override
    public List<HoaDonDto> findHoaDon(String key) {
        return hoaDonRepository.findHoaDon(key);
    }

    @Override
    public List<HoaDonDto> locHinhThucThanhToan(Integer httt) {
        return hoaDonRepository.locHinhThucThanhToan(httt);
    }

    @Override
    public List<HoaDonDto> locHoaDonTheoNgay(Date ngay1, Date ngay2) {
        return hoaDonRepository.locHoaDonTheoNgay(ngay1, ngay2);
    }

    @Override
    public List<HoaDonDto> locHoaDonTheoTongTien(BigDecimal tien1, BigDecimal tien2) {
        return hoaDonRepository.locHoaDonTheoTongTien(tien1, tien2);
    }
    
}
