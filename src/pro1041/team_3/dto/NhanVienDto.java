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
    private String tendangnhap;
    private String hoten;
    private Integer gioitinh;
    private String sdt;
    private String diachi;
    private String email;
    private String matkhau;
    private Integer trangThaiLamViec;
    private Integer vaitro;
    
    public Object[] toDataRow() {
        return new Object[]{ma, tendangnhap, hoten, gioitinh, sdt, diachi, email, matkhau,trangThaiLamViec, vaitro};
    }
}
