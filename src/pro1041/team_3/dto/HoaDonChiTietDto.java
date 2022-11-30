package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.UUID;
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
public class HoaDonChiTietDto {
    
    private String ma;

    private String mauSac;

    private String ten;

    private String hang;

    private Integer tinhTrang;

    private BigDecimal donGia;

    private BigDecimal giaBan;

    private String imei;

    private Integer ram;

    private Integer boNho;

    private String moTa;

}
