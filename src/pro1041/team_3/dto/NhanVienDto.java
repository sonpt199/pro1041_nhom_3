package pro1041.team_3.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author trangntph19494
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienDto {
    private UUID id;
    private String ma;
    private String tenDangNhap;
    private String hoTen;
    private Integer gioiTinh;
    private String sdt;
    private String diaChi;
    private String email;
    private String matKhau;
    private Integer trangThaiLamViec;
    private Integer vaiTro;
    
}
