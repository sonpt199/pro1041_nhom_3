
package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.Date;
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
public class DoanhThuTheoThangDto {
    
    private Integer ngay;
    
    private BigDecimal doanhThu;
    
    private Long soHoaDon;
    
}
