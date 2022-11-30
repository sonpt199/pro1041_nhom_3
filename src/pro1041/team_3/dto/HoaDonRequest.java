package pro1041.team_3.dto;

import java.math.BigDecimal;
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
public class HoaDonRequest {
    
    private KhachHang khachHang;
    
    private NhanVien nhanVien;
    
    private Integer hinhThucThanhToan;
    
    private UUID idChiTietDienThoai;
    
    private BigDecimal tongTien;
    
    private BigDecimal donGia;
    
    private BigDecimal giaBan;
    
    private BigDecimal tienMat;
    
    private BigDecimal nganHang;
    
    private String maGiaoDich;
    
}
