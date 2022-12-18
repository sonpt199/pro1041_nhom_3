package pro1041.team_3.service.impl;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.dto.KhuyenMaiDto;
import pro1041.team_3.repository.KhuyenMaiRepository;
import pro1041.team_3.service.KhuyenMaiService;

/**
 *
 * @author trangdttph27721
 */
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    private KhuyenMaiRepository khuyenMaiRepository;
    private List<KhuyenMaiDto> _lstKM;

    public KhuyenMaiServiceImpl() {
        this.khuyenMaiRepository = new KhuyenMaiRepository();
        _lstKM = new ArrayList<>();
    }

    @Override
    public List<KhuyenMaiDto> getAllResponse() {
//        _lstKM = new ArrayList<>();
//        List<KhuyenMai> list = khuyenMaiRepository.getAll();
//        for (KhuyenMai x : list) {
//            _lstKM.add(new KhuyenMaiDto(x.getId(), x.getMa(), x.getTen(),
//                    new java.sql.Date(x.getNgayBatDau().getTime()), new java.sql.Date(x.getNgayKetThuc().getTime()),
//                    x.getGiaTriPhanTram(), x.getGiaTriTienMat()));
//        }
//        return _lstKM;
        return khuyenMaiRepository.getAllResponse();
    }

    @Override
    public String insert(KhuyenMai khuyenMai) {
        List<KhuyenMai> list = khuyenMaiRepository.getAll();
        Integer ma = 0;
        for (KhuyenMai x : list) {
            String maSoStr = x.getMa().substring(3);
            Integer maSo = Integer.parseInt(maSoStr);
            if (ma < maSo) {
                ma = maSo;
            }
        }

        khuyenMai.setId(null);
        Integer maMau = ma + 1;
        khuyenMai.setMa("KM0" + maMau);
        
        khuyenMai = khuyenMaiRepository.saveOrUpdate(khuyenMai);
        if (khuyenMai != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(KhuyenMai khuyenMai) {
        KhuyenMai kmByID = khuyenMaiRepository.findById(khuyenMai.getId());
        if (kmByID == null) {
            return "Không tìm thấy";
        }
        kmByID.setMa(khuyenMai.getMa());
        kmByID.setTen(khuyenMai.getTen());
        kmByID.setNgayBatDau(khuyenMai.getNgayBatDau());
        kmByID.setNgayKetThuc(khuyenMai.getNgayKetThuc());
        kmByID.setGiaTriPhanTram(khuyenMai.getGiaTriPhanTram());
        kmByID.setGiaTriTienMat(khuyenMai.getGiaTriTienMat());
        khuyenMai = khuyenMaiRepository.saveOrUpdate(kmByID);
        if (khuyenMai != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<KhuyenMaiDto> findKhuyenMai(String key) {
        _lstKM = new ArrayList<>();
        List<KhuyenMai> list = khuyenMaiRepository.findKhuyenMai(key);
        for (KhuyenMai x : list) {
            _lstKM.add(new KhuyenMaiDto(x.getId(), x.getMa(), x.getTen(),
                    new java.sql.Date(x.getNgayBatDau().getTime()), new java.sql.Date(x.getNgayKetThuc().getTime()),
                    x.getGiaTriPhanTram(), x.getGiaTriTienMat()));
        }
        return _lstKM;
    }

    @Override
    public List<KhuyenMaiDto> findNgayTuongLai() {
        return this.khuyenMaiRepository.findNgayTuongLai();
    }

    @Override
    public KhuyenMai findById(UUID id) {
        return this.khuyenMaiRepository.findById(id);
    }

    @Override
    public List<KhuyenMaiDto> findKMDangDienRa() {
        return this.khuyenMaiRepository.findKMDangDienRa();
    }

    @Override
    public List<KhuyenMaiDto> findKMKetThuc() {
        return this.khuyenMaiRepository.findKMKetThuc();
    }

    @Override
    public List<KhuyenMaiDto> findKMByLoai(int loai) {
        return this.khuyenMaiRepository.findKMByLoai(loai);
    }
    
}
