package pro1041.team_3.domainmodel;

import java.io.Serializable;
import java.util.Date;
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
 * @author sonptph19600
 */
@Table(name = "tai_khoan")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaiKhoan implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;

    @Column(name = "ho_ten")
    private String hoten;

    @Column(name = "gioi_tinh")
    private Integer gioitinh;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "dia_chi")
    private String diachi;

    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String matkhau;

    @Column(name = "vai_tro")
    private Integer vaitro;

}
