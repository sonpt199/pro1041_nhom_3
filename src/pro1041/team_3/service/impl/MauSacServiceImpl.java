package pro1041.team_3.service.impl;

import java.time.LocalDateTime;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.MauSacDto;
import pro1041.team_3.repository.MauSacRepository;
import pro1041.team_3.service.MauSacService;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.dto.ChiTietDienThoaiDto;

/**
 *
 * @author vanntph19604
 */
public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository repos;

    public MauSacServiceImpl() {
        repos = new MauSacRepository();
    }

    @Override
    public List<MauSacDto> getAllResponse() {
        return repos.getAllResponse();
    }

    @Override
    public String insert(MauSac mausac) {
        List<MauSac> list = repos.getAll();
        Integer ma = 0;
        for (MauSac x : list) {
            String maSoStr = x.getMa().substring(2);
            Integer maSo = Integer.parseInt(maSoStr);
            if (ma < maSo) {
                ma = maSo;
            }
        }

        mausac.setId(null);
        Integer maMau = ma + 1;
        mausac.setMa("M0" + maMau);
        if (mausac.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        for (MauSac x : list) {
            if (mausac.getTen().equalsIgnoreCase(x.getTen())) {
                return "Trùng tên màu sắc";
            }
        }
        mausac = repos.saveOrUpdate(mausac);
        if (mausac != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(MauSac mausac) {
        MauSac mauSacFindById = repos.findByMa(mausac.getMa());
        if (mauSacFindById == null) {
            return "Không tìm thấy";
        }
        if (mausac.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        List<MauSac> list = repos.getAll();
        for (MauSac x : list) {
            if (mausac.getTen().equalsIgnoreCase(x.getTen())) {
                return "Trùng tên màu sắc";
            }
        }
        mauSacFindById.setMa(mausac.getMa());
        mauSacFindById.setTen(mausac.getTen());
        mausac = repos.saveOrUpdate(mauSacFindById);
        if (mausac != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(String ma) {
        MauSac mausacFind = repos.findByMa(ma);
        ChiTietDienThoaiDto ctdtdto = repos.checkMSTrongCtdt(mausacFind.getId());
        if (mausacFind == null) {
            return "Không tìm thấy";
        }
        if (ctdtdto != null) {
            return "Màu sắc đã tồn tại trong bảng chi tiết điện thoại";
        }
        boolean delete = repos.detele(mausacFind);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }

    }

    @Override
    public List<MauSac> getAll() {
        return repos.getAll();
    }

    public static void main(String[] args) {
        MauSacService a = new MauSacServiceImpl();
        System.out.println(a.getAllResponse().size());
    }

    @Override
    public List<MauSacDto> findByName(String keyWord) {
        return repos.findByName(keyWord);
    }

    @Override
    public MauSac findMauSacByName(String ten) {
        return repos.findMauSacByName(ten);
    }
}
