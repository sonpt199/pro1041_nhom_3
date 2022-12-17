package pro1041.team_3.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.dto.ChiTietDienThoaiDto;
import pro1041.team_3.dto.DienThoaiDto;
import pro1041.team_3.repository.DienThoaiRepository;
import pro1041.team_3.service.DienThoaiService;

/**
 *
 * @author vanntph19604
 */
public class DienThoaiServiceImpl implements DienThoaiService {

    private DienThoaiRepository repos;

    public DienThoaiServiceImpl() {
        repos = new DienThoaiRepository();
    }

    @Override
    public List<DienThoaiDto> getAllResponse() {
        return repos.getAllResponse();
    }

    @Override
    public String insert(DienThoai dienthoai) {
        List<DienThoai> list = repos.getAll();
        Integer ma = 0;
        for (DienThoai x : list) {
            String maSoStr = x.getMa().substring(3);
            Integer maSo = Integer.parseInt(maSoStr);
            if (ma < maSo) {
                ma = maSo;
            }
        }

        dienthoai.setId(null);
        Integer maMau = ma + 1;
        dienthoai.setMa("DT0" + maMau);
        if (dienthoai.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        DienThoai dienThoaiFindByMa = repos.findByMa(dienthoai.getMa());
        if (dienThoaiFindByMa != null) {
            return "Mã không được trùng";
        }
        dienthoai = repos.saveOrUpdate(dienthoai);
        if (dienthoai != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(DienThoai dienthoai) {
        DienThoai dienThoaiFindByMa = repos.findByMa(dienthoai.getMa());
        if (dienThoaiFindByMa == null) {
            return "Không tìm thấy";
        }
        if (dienthoai.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        dienThoaiFindByMa.setMa(dienthoai.getMa());
        dienThoaiFindByMa.setTen(dienthoai.getTen());
        dienthoai = repos.saveOrUpdate(dienThoaiFindByMa);
        if (dienthoai != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(String ma) {
        DienThoai dienThoaiFind = repos.findByMa(ma);
        ChiTietDienThoaiDto ctdtdto = repos.checkDTTrongCtdt(dienThoaiFind.getId());
        if (dienThoaiFind == null) {
            return "Không tìm thấy";
        }
        if (ctdtdto != null) {
            return "Điện thoại đã tồn tại trong bảng chi tiết điện thoại";
        }
        boolean delete = repos.detele(dienThoaiFind);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public List<DienThoai> getAll() {
        return repos.getAll();
    }

    @Override
    public List<DienThoaiDto> findByName(String keyWord) {
        return repos.findByName(keyWord);
    }

    @Override
    public DienThoai findDienThoaiByName(String ten) {
        return repos.findDienThoaiByName(ten);
    }
}
