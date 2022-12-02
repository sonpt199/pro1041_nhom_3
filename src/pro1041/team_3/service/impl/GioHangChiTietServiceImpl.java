package pro1041.team_3.service.impl;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.GioHangChiTietDto;
import pro1041.team_3.repository.BanHangRepository;
import pro1041.team_3.repository.GioHangChiTietRepository;
import pro1041.team_3.service.GioHangChiTietService;

/**
 *
 * @author sonpt_ph19600
 */
public class GioHangChiTietServiceImpl implements GioHangChiTietService{
    
    private GioHangChiTietRepository gioHangChiTietRepository;
    private BanHangRepository banHangRepository;

    public GioHangChiTietServiceImpl() {
        gioHangChiTietRepository = new GioHangChiTietRepository();
        banHangRepository = new BanHangRepository();
    }
        
    @Override
    public List<BhChiTietDienThoaiDto> getAllByIdGioHang(UUID idGioHang) {
        List<BhChiTietDienThoaiDto> lstGioHangChiTiet = gioHangChiTietRepository.getAllByIdGioHang(idGioHang);
        for (BhChiTietDienThoaiDto x : lstGioHangChiTiet) {
            DienThoaiKhuyenMai dtkm = banHangRepository.getGiamGia(x.getId());
            if (dtkm == null) {
                x.setGiaBan(x.getDonGia());
            } else {                
                x.setGiaBan(dtkm.getGiaConLai());
            }
        }
        return lstGioHangChiTiet;
    }

    @Override
    public boolean deteleByIdGioHang(UUID idGioHang) {
        return gioHangChiTietRepository.deteleByIdGioHang(idGioHang);
    }
    
}
