package pro1041.team_3.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.domainModel.NhanVien;

/**
 *
 * @author sonpt_ph19600
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GioHangRequest {

    private NhanVien nhanVien;
    
    private KhachHang khachHang;
    
    private UUID idChiTietDienThoai;
    
    private String lyDo;
    
}
