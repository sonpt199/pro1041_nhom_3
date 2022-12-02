/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author trangdttph27721
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DienThoaiKhuyenMaiDto {
    private UUID idKhuyenMai;
    private UUID idChiTietDienThoai;
    private String maChiTietDienThoai;
    private String tenDienThoai;
    private String hangDienThoai;
    private String mauSac;
    private String imei;
    private BigDecimal giaBan;
    private BigDecimal giaConLai;

    
    
    public Object[] toDataRow(int index) {
        return new Object[]{index, maChiTietDienThoai, tenDienThoai, hangDienThoai, mauSac, imei, giaBan, giaConLai};
    }
}
