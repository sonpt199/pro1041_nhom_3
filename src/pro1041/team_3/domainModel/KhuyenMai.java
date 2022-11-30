package pro1041.team_3.domainModel;

import java.math.BigDecimal;
import java.util.Date;
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

/**
 *
 * @author sonpt_ph19600
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "khuyen_mai")
public class KhuyenMai {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;

    @Column(name = "gia_tri_phan_tram")
    private Float giaTriPhanTram;

    @Column(name = "gia_tri_tien_mat")
    private BigDecimal giaTriTienMat;

}
