/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.dto;


import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    public Object[] toDataRow() {
        return new Object[]{ma, dienThoai, hang, giaBan, mauSac, imei, ram, boNho, tinhTrang == 1 ? "Còn hàng" : "Hết hàng", trangThai == 1 ? "Mới" : "Cũ", moTa, hinhAnh == null ? "Image" : hinhAnh};
    }
}
