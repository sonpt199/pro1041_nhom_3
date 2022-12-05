package pro1041.team_3.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sonpt_ph19600
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonPdfRequest {
    
    private String hoTenKhachHang;
    
    private String ngayThanhToan;
    
    private String sdt;
    
    private String maHoaDon;
    
    private String nhanVien;
    
    private BigDecimal tongTien;
    
    private BigDecimal nganHang;
    
    private BigDecimal tienMat;
    
}
