package pro1041.team_3.domainModel;

import java.io.Serializable;
import java.util.UUID;
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
@Table(name = "gio_hang_chi_tiet")
@IdClass(GioHangChiTietId.class)
public class GioHangChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_gio_hang")
    private GioHang gioHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_chi_tiet_dien_thoai")
    private ChiTietDienThoai chiTietDienThoai;

}
