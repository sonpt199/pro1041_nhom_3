/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro1041.team_3.domainModel.ChiTietDienThoai;

/**
 *
 * @author trangdttph27721
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMaiReQuestDto {

    private String maKhuyenMai;
    private String tenKhuyenMai;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Float giaTriPhanTram;
    private BigDecimal giaTriTienMat;
    private ChiTietDienThoai chiTietDienThoai;   
    private BigDecimal giaBan;
    private BigDecimal giaConLai;

}
