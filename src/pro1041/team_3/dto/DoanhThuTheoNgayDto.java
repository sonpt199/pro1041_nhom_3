
package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sonpt_ph19600
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoanhThuTheoNgayDto {
    
    private Integer ngay;
    
    private Integer thang;
    
    private Integer nam;
    
    private BigDecimal doanhThu;
    
    private Long soHoaDon;
    
    private Long soDienThoai;
    
    private Long soKhachHang;

    public DoanhThuTheoNgayDto(BigDecimal doanhThu, Long soHoaDon, Long soDienThoai, Long soKhachHang) {
        this.doanhThu = doanhThu;
        this.soHoaDon = soHoaDon;
        this.soDienThoai = soDienThoai;
        this.soKhachHang = soKhachHang;
    }
            
}
