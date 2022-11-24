<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
>>>>>>> trangdtt
package pro1041.team_3.repository;

import java.util.UUID;
import pro1041.team_3.domainModel.DienThoai;
<<<<<<< HEAD
import pro1041.team_3.dto.DienThoaiDto;

/**
 *
 * @author van15
 */
public class DienThoaiRepository extends Repository<DienThoai, UUID, DienThoaiDto> {

    public DienThoaiRepository() {
        className = DienThoai.class.getName();
        resCon = "new pro1041.team_3.dto.DienThoaiDto(a.id, a.ma, a.ten, a.thoi_gian_bao_hanh)";
    }

=======

/**
 *
 * @author ADMIN
 */
public class DienThoaiRepository extends Repository<DienThoai, UUID, DienThoai>{

    public DienThoaiRepository() {
        this.className = DienThoai.class.getName();
        this.resCon = "new fpoly.pro1041.nhom_3.domainmodel.DienThoai(a.id, a.ma, a.ten, a.thoiGianBaoHanh)";
    }
    
>>>>>>> trangdtt
}
