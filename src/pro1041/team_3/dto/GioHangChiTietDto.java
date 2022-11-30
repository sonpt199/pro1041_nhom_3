package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro1041.team_3.domainModel.GioHang;

/**
 *
 * @author sonpt_ph19600
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietDto {
    
    private GioHang gioHang;
    
    private UUID idChiTietDienThoai;
            
}
