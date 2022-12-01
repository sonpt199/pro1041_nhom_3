/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.domainModel;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TrangNT
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nhan_vien")
public class NhanVien implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ma")
    private String ma;
    
    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;
    
    @Column(name = "ho_ten")
    private String hoTen;
    
    @Column(name = "gioi_tinh")
    private Integer gioiTinh;
    
    @Column(name = "sdt")
    private String sdt;
    
    @Column(name = "dia_chi")
    private String diaChi;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "mat_khau")
    private String matKhau;
    
    @Column(name = "trang_thai_lam_viec")
    private Integer trangThaiLamViec;
    
    @Column(name = "vai_tro")
    private Integer vaiTro;
    
}
