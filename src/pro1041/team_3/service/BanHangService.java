package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.GioHangDto;
import pro1041.team_3.dto.GioHangRequest;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.dto.HoaDonRequest;

/**
 *
 * @author sonpt_ph19600
 */
public interface BanHangService {
    
    String thanhToan(List<HoaDonRequest> lstSp);
    
    BhChiTietDienThoaiDto bhFindByImei(String keyWord);
    
    DienThoaiKhuyenMai getGiamGia(UUID idChiTietSanPham);
    
    String treoHoaDon(List<GioHangRequest> lstSp);
    
    List<GioHangDto> getGioHangByIdSp(UUID idChiTietDienThoai);
    
    HoaDonDto getHoaDonByIdSp(UUID idChiTietDienThoai);
    
}
