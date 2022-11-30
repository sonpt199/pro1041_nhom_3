package pro1041.team_3.dto;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sonpt_ph19600
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangDto {

    private UUID idGioHang;

    private String maGioHang;

    private UUID idKhachHang;

    private String maKhachHang;
    
    private String tenKhachHang;
    
    private String sdtKhachHang;

    private String maNhanVien;

    private Date ngayTao;

    private String lyDo;
    
}
