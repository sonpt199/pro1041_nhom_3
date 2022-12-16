/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro1041.team_3.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KhuyenMaiDto {

    private UUID idKhuyenMai;
    private String maKhuyenMai;
    private String tenKhuyenMai;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Float giaTriPhanTram;
    private BigDecimal giaTriTienMat;

    public Object[] toDataRow(int index) {
        Date dateNow = new Date();
        String trangThai = "Đang diễn ra";
        BigDecimal mucKhuyenMai = new BigDecimal(0);
        String mucKhuyenMaiStr;
        if (ngayBatDau.getTime() <= dateNow.getTime() && ngayKetThuc.getTime() >= dateNow.getTime()) {
            trangThai = "Đang diễn ra";
        } else if (ngayBatDau.getTime() > dateNow.getTime()) {
            trangThai = "Sắp diễn ra";
        } else {
            trangThai = "Dừng";
        }

        if (giaTriPhanTram == null) {
            mucKhuyenMai = giaTriTienMat;
            mucKhuyenMaiStr = mucKhuyenMai + " VND";
        } else {
            mucKhuyenMai = new BigDecimal(giaTriPhanTram);
            mucKhuyenMaiStr = mucKhuyenMai + " %";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm dd/MM/yyyy");
        return new Object[]{index, maKhuyenMai, tenKhuyenMai, sdf.format(ngayBatDau), sdf.format(ngayKetThuc), mucKhuyenMaiStr, trangThai};
    }
}
