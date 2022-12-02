package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
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
public class HoaDonDto {
    
    private UUID id;
    
    private String maHoaDon;
    
    private String maKhachHang;
    
    private String tenKhachHang;
    
    private String sdtKhachHang;
    
    private String maNhanVien;
    
    private String tenNhanVien;
    
    private Date ngayThanhToan;
    
    private Integer hinhThucThanhToan;
    
    private BigDecimal tienMat;
    
    private BigDecimal nganHang;
    
    private String maGiaoDich;
    
    private BigDecimal tongTien;
    
}
