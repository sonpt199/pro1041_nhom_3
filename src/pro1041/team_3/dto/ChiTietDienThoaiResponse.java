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
    
    private String maDienThoai;
    
    private String mauSac;
    
    private String dienThoai;
    
    private String hang;
    
    private Integer tinhTrang;
    
    private BigDecimal donGia;
    
    private Integer trangThai;
    
    private String imei;
    
    private Integer ram;
    
    private Integer boNho;
    
    private String moTa;
    
    private Integer thoiGianBaoHanh;
    
    public Object[] toDataRow(int index) {
        return new Object[]{index, maDienThoai, dienThoai, hang, donGia, mauSac, imei, ram, boNho, tinhTrang + "%", trangThai == 0 ? "Đang bán" : trangThai == 1 ? "Đã bán" : "Sản phẩm lỗi", moTa == null ? "_" : moTa, thoiGianBaoHanh};
    }
    
    public Object[] toDataRowKM(int index) {
        return new Object[]{index, maDienThoai, dienThoai, hang, mauSac, tinhTrang + "%", boNho, imei};
    }
}
