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
@NoArgsConstructor
//@AllArgsConstructor
public class BhChiTietDienThoaiDto {

    private UUID id;

    private String ma;

    private String mauSac;

    private String ten;

    private String hang;

    private Integer tinhTrang;

    private BigDecimal donGia;
    
    private BigDecimal giaBan;

    private Integer trangThai;

    private String imei;

    private Integer ram;

    private Integer boNho;

    private String moTa;

    public BhChiTietDienThoaiDto(UUID id, String ma, String mauSac, String ten, String hang, Integer tinhTrang, BigDecimal donGia, Integer trangThai, String imei, Integer ram, Integer boNho, String moTa) {
        this.id = id;
        this.ma = ma;
        this.mauSac = mauSac;
        this.ten = ten;
        this.hang = hang;
        this.tinhTrang = tinhTrang;
        this.donGia = donGia;
        this.trangThai = trangThai;
        this.imei = imei;
        this.ram = ram;
        this.boNho = boNho;
        this.moTa = moTa;
    }  
    
}
