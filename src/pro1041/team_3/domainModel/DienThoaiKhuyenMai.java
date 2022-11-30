package pro1041.team_3.domainModel;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sonpt_ph19600
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "dien_thoai_khuyen_mai")
@IdClass(DienThoaiKhuyenMaiId.class)
public class DienThoaiKhuyenMai implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "id_khuyen_mai")
    private KhuyenMai khuyenMai;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_chi_tiet_dien_thoai")
    private ChiTietDienThoai chiTietDienThoai;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "gia_con_lai")
    private BigDecimal giaConLai;

    @Column(name = "trang_thai")
    private Integer trangThai;

}
