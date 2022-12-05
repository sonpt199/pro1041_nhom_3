package pro1041.team_3.service.impl;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.repository.HoaDonChiTietRepository;
import pro1041.team_3.service.HoaDonChiTietService;

/**
 *
 * @author sonpt_ph19600
 */
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService{
    
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public HoaDonChiTietServiceImpl() {
        hoaDonChiTietRepository = new HoaDonChiTietRepository();
    }
        
    @Override
    public List<HoaDonChiTietDto> getAllByIdHoaDon(UUID idHoaDon) {
        return hoaDonChiTietRepository.getAllByIdHoaDon(idHoaDon);
    }

    @Override
    public List<HoaDonChiTietDto> getAllByMaHoaDon(String maHoaDon) {
        return hoaDonChiTietRepository.getAllByMaHoaDon(maHoaDon);
    }
    
}
