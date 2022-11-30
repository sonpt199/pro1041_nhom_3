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
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet implements Serializable{

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "id_chi_tiet_dien_thoai")
    private ChiTietDienThoai chiTietDienThoai;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

}
