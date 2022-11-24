
package pro1041.team_3.service.impl;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.dto.DienThoaiDto;
import pro1041.team_3.repository.DienThoaiRepository;
import pro1041.team_3.service.DienThoaiService;

/**
 *
 * @author van15
 */
public class DienThoaiServiceImpl implements DienThoaiService{
    
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
        DienThoai dienThoaiFindByMa = repos.findByMa(dienthoai.getMa());
        if (dienThoaiFindByMa != null) {
            return "Ma khong duoc trung";
        }
        dienthoai = repos.saveOrUpdate(dienthoai);
        if (dienthoai != null) {
            return "Them thanh cong";
        } else {
            return "Them that bai";
        }
    }

    @Override
    public String update(DienThoai dienthoai) {
        DienThoai dienThoaiFindById = repos.findById(dienthoai.getId());
        if (dienThoaiFindById == null) {
            return "Khong tim thay";
        }
        DienThoai dienThoaiFindByMa = repos.findByMa(dienthoai.getMa());
        if (dienThoaiFindByMa != null
                && dienThoaiFindById.equals(dienThoaiFindByMa.getId())) {
            return "Ma khong duoc trung";
        }
        dienThoaiFindById.setMa(dienthoai.getMa());
        dienThoaiFindById.setTen(dienthoai.getTen());
        dienThoaiFindById.setThoiGianBaoHanh(dienthoai.getThoiGianBaoHanh());
        dienthoai = repos.saveOrUpdate(dienThoaiFindById);
        if (dienthoai != null) {
            return "Sua thanh cong";
        } else {
            return "Sua that bai";
        }
    }

    @Override
    public String delete(UUID id) {
        DienThoai dienThoaiFind = repos.findById(id);
        if (dienThoaiFind == null) {
            return "Khong tim thay";
        }
        boolean delete = repos.detele(dienThoaiFind);
        if (delete) {
            return "Xoa thanh cong";
        } else {
            return "Xoa that bai";
        }
    }

    @Override
    public List<DienThoai> getAll() {
        return repos.getAll();
    }
    
    public static void main(String[] args) {
        DienThoaiService dt = new DienThoaiServiceImpl();
        System.out.println(dt.getAllResponse().size());
    }
    
}
