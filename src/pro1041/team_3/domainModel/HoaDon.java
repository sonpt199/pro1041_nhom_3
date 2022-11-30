package pro1041.team_3.domainModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Entity
@Table(name = "hoa_don")
public class HoaDon implements Serializable{
    
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ma")
    private String ma;
    
    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;
    
    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;
    
    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;
    
    @Column(name = "hinh_thuc_thanh_toan")
    private Integer hinhThucThanhToan;
    
    @Column(name = "tong_tien")
    private BigDecimal tongTien;
    
    @Column(name = "tien_mat")
    private BigDecimal tienMat;
    
    @Column(name = "ngan_hang")
    private BigDecimal nganHang;
    
    @Column(name = "ma_giao_dich")
      private String maGiaoDich;
    
}
