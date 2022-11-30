package pro1041.team_3.domainModel;

import java.io.Serializable;
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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "chi_tiet_dien_thoai")
public class ChiTietDienThoai implements Serializable{
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
    private Integer tinhTrang;
    
    @Column(name = "don_gia")
    private BigDecimal donGia;
    
    @Column(name = "trang_thai")
    private Integer trangThai;
    
    @Column(name = "hinh_anh")
    private String hinhAnh;
    
    @Column(name = "imei")
    private String imei;
    
    @Column(name = "ram")
    private Integer ram;
    
    @Column(name = "bo_nho")
    private Integer boNho;
    
    @Column(name = "mo_ta")
    private String moTa;
    
}
