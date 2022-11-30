/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pro1041.team_3.service;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.dto.KhuyenMaiReQuestDto;

/**
 *
 * @author trangdttph27721
 */
public interface DienThoaiKhuyenMaiService {

    List<DienThoaiKhuyenMaiDto> findDienThoaiKhuyenMaiByIdKM(UUID idKM);

    List<DienThoaiKhuyenMaiDto> findDienThoaiKhuyenMai(UUID idKM, String key);

    String insert(DienThoaiKhuyenMai khuyenMai);

    String update(DienThoaiKhuyenMai khuyenMai);

    String insertKMDT(List<KhuyenMaiReQuestDto> list);

    String insertSanPhamKM(KhuyenMai khuyenMai, List<KhuyenMaiReQuestDto> list);

    String deleteDTKM(DienThoaiKhuyenMai dienThoaiKhuyenMai);

    List<DienThoaiKhuyenMaiDto> findDTKhuyenMaiDienRavaTgLai(UUID id);

}
