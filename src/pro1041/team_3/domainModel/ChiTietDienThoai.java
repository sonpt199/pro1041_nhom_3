/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.domainModel;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "chi_tiet_dien_thoai")
public class ChiTietDienThoai {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ma")
    private String ma;
    
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;
    
    @ManyToOne
    @JoinColumn(name = "id_dien_thoai")
    private DienThoai dienThoai;
    
    @ManyToOne
    @JoinColumn(name = "id_hang")
    private Hang hang;
    
    @Column(name = "tinh_trang")
    private int tinhTrang;
    
    @Column(name = "gia_ban")
    private BigDecimal giaBan;
    
    @Column(name = "trang_thai")
    private int trangThai;
    
    @Column(name = "hinh_anh")
    private String hinhAnh;
    
    @Column(name = "imei")
    private String imei;
    
    @Column(name = "ram")
    private float ram;
    
    @Column(name = "bo_nho")
    private float boNho;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    
=======
/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chi_tiet_dien_thoai")
public class ChiTietDienThoai {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @ManyToOne
    @JoinColumn(name = "id_hang")
    private Hang hang;

    @ManyToOne
    @JoinColumn(name = "id_dien_thoai")
    private DienThoai dienThoai;

    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;

    @Column(name = "tinh_trang")
    private Integer tinhTrang;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "imei")
    private String imei;

    @Column(name = "ram")
    private Integer ram;

    @Column(name = "bo_nho")
    private Integer bo_nho;

    @Column(name = "mo_ta")
    private String moTa;
>>>>>>> hanhlt
}
