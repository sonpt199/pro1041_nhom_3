package pro1041.team_3.service.impl;

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

        MauSac mauSacFindByMa = repos.findByMa(mausac.getMa());
        if (mauSacFindByMa != null) {
            return "Mã không được trùng";
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
        MauSac mauSacFindById = repos.findById(mausac.getId());
        if (mauSacFindById == null) {
            return "Không tìm thấy";
        }
        MauSac mauSacFindByMa = repos.findByMa(mausac.getMa());
        if (mauSacFindByMa != null
                && mauSacFindById.equals(mauSacFindByMa.getId())) {
            return "Mã không được trùng";
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
            return "Mau sac da ton tai trong chi tiet dien thoai";
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

}
