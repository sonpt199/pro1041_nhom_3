package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sonhn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoanhThuCaNhanDto {
    
    private String maNhanVien;
    
    private BigDecimal doanhThu;
    
//    private Date ngay;
}
