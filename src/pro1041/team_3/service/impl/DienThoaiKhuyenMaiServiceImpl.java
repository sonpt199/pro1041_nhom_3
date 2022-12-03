package pro1041.team_3.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.dto.KhuyenMaiReQuestDto;
import pro1041.team_3.repository.DienThoaiKhuyenMaiRepository;
import pro1041.team_3.repository.KhuyenMaiRepository;
import pro1041.team_3.service.DienThoaiKhuyenMaiService;

/**
 *
 * @author trangdttph27721
 */
public class DienThoaiKhuyenMaiServiceImpl implements DienThoaiKhuyenMaiService {

    private DienThoaiKhuyenMaiRepository dienThoaiKhuyenMaiRepository;
    private KhuyenMaiRepository khuyenMaiRepository;

    public DienThoaiKhuyenMaiServiceImpl() {
        this.dienThoaiKhuyenMaiRepository = new DienThoaiKhuyenMaiRepository();
        this.khuyenMaiRepository = new KhuyenMaiRepository();
    }

    @Override
    public List<DienThoaiKhuyenMaiDto> findDienThoaiKhuyenMaiByIdKM(UUID idKM) {
        return this.dienThoaiKhuyenMaiRepository.findDienThoaiKhuyenMaiByIdKM(idKM);
    }

    @Override
    public String insert(DienThoaiKhuyenMai khuyenMai) {
        return null;
    }

    @Override
    public String update(DienThoaiKhuyenMai khuyenMai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String insertKMDT(List<KhuyenMaiReQuestDto> list) {
        List<DienThoaiKhuyenMai> lstDtKM = new ArrayList<>();
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setMa(list.get(0).getMaKhuyenMai());
        khuyenMai.setTen(list.get(0).getTenKhuyenMai());
        khuyenMai.setNgayBatDau(list.get(0).getNgayBatDau());
        khuyenMai.setNgayKetThuc(list.get(0).getNgayKetThuc());
        khuyenMai.setGiaTriPhanTram(list.get(0).getGiaTriPhanTram());
        khuyenMai.setGiaTriTienMat(list.get(0).getGiaTriTienMat());
        Date dateNow = new Date();
        if (khuyenMai.getNgayKetThuc().getTime() < dateNow.getTime()) {
            return "Ngày kết thúc phải lớn hơn ngày hiện tại";
        }
        if (khuyenMai.getNgayBatDau().getTime() > khuyenMai.getNgayKetThuc().getTime()) {
            return "Ngày bắt đầu phải nhỏ hơn  ngày kết thúc";
        }
        if (khuyenMaiRepository.saveOrUpdate(khuyenMai) == null) {
            return "Lỗi ";
        }

        for (KhuyenMaiReQuestDto x : list) {
            DienThoaiKhuyenMai dtkm = new DienThoaiKhuyenMai();
            dtkm.setChiTietDienThoai(x.getChiTietDienThoai());
            dtkm.setKhuyenMai(khuyenMai);
            dtkm.setDonGia(x.getGiaBan());
            dtkm.setGiaConLai(x.getGiaConLai());            
            lstDtKM.add(dtkm);
        }
        if (!dienThoaiKhuyenMaiRepository.saveAll(lstDtKM)) {
            return "Thêm thất bại";
        }
        return "Thêm thành công";
    }

    @Override
    public List<DienThoaiKhuyenMaiDto> findDienThoaiKhuyenMai(UUID idKM, String key) {
        return dienThoaiKhuyenMaiRepository.findDienThoaiKhuyenMai(idKM, key);
    }

    @Override
    public String insertSanPhamKM(KhuyenMai khuyenMai, List<KhuyenMaiReQuestDto> list) {
        List<DienThoaiKhuyenMai> lstDtKM = new ArrayList<>();
        for (KhuyenMaiReQuestDto x : list) {
            DienThoaiKhuyenMai dtkm = new DienThoaiKhuyenMai();
            dtkm.setChiTietDienThoai(x.getChiTietDienThoai());
            dtkm.setKhuyenMai(khuyenMai);
            dtkm.setDonGia(x.getGiaBan());
            dtkm.setGiaConLai(x.getGiaConLai());
            lstDtKM.add(dtkm);
        }
        if (!dienThoaiKhuyenMaiRepository.saveAll(lstDtKM)) {
            return "Thêm thất bại";
        }
        return "Thêm thành công";
    }

    @Override
    public String deleteDTKM(DienThoaiKhuyenMai dienThoaiKhuyenMai) {
        boolean delete = dienThoaiKhuyenMaiRepository.detele(dienThoaiKhuyenMai);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public List<DienThoaiKhuyenMaiDto> findDTKhuyenMaiDienRavaTgLai(UUID id) {
        return dienThoaiKhuyenMaiRepository.findDTKhuyenMaiDienRavaTgLai(id);
    }

    @Override
    public List<DienThoaiKhuyenMaiDto> findDTKhuyenMaiKetThuc() {
        return dienThoaiKhuyenMaiRepository.findDTKhuyenMaiKetThuc();
    }   

}
