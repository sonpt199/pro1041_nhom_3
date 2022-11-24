package pro1041.team_3.dto;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hanhltph27725
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDto {

    private UUID id;
    private String ma;
    private String hoTen;
    private Date ngaySinh;
    private Integer gioiTinh;
    private String sdt;
    private String diaChi;
    private String email;
}
