package pro1041.team_3.service.impl;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.GioHang;
import pro1041.team_3.dto.GioHangDto;
import pro1041.team_3.repository.GioHangRepository;
import pro1041.team_3.service.GioHangService;

/**
 *
 * @author sonpt_ph19600
 */
public class GioHangServiceImpl implements GioHangService{
    
    private GioHangRepository gioHangRepository;

    public GioHangServiceImpl() {
        gioHangRepository = new GioHangRepository();
    }
        
    @Override
    public List<GioHangDto> getAllResponse() {
        return gioHangRepository.getAllResponse();
    }

    @Override
    public String delete(UUID idGioHang) {
        GioHang gioHang = gioHangRepository.findById(idGioHang);
        if (gioHang == null) {
            return "Giỏ hàng treo không tồn tại";
        }
        if (gioHangRepository.detele(gioHang)) {
            return "Xóa giỏ hàng treo thành công";
        } else {
            return "Lỗi hệ thống. Xóa thất bại";
        }
        
    }
    
}
