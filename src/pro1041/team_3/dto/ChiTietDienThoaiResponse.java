package pro1041.team_3.dto;


import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author trangdttph27721
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDienThoaiResponse {
    private UUID id;
    
    private String ma;
    
    private String mauSac;
    
    private String dienThoai;
    
    private String hang;
    
    private int tinhTrang;
    
    private BigDecimal giaBan;
    
    private int trangThai;
    
    private String hinhAnh;
    
    private String imei;
    
    private float ram;
    
    private float boNho;
    
    private String moTa;
    
    private Integer thoiGianBaoHanh;
    
    public Object[] toDataRow() {
        return new Object[]{ma, dienThoai, hang, giaBan, mauSac, imei, ram, boNho, tinhTrang == 1 ? "Mới" : "Cũ", trangThai == 0 ? "Đang bán" : trangThai == 1 ? "Đã bán" : "Sản phẩm lỗi", moTa == null ? " " : moTa, thoiGianBaoHanh};
    }
    
    public Object[] toDataRowKM() {
        return new Object[]{ma, dienThoai, tinhTrang == 1 ? "Mới" : "Cũ", imei, false};
    }
}
