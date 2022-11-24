package pro1041.team_3.service.impl;

import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.NhanVienDto;
import pro1041.team_3.repository.NhanVienRepository;
import pro1041.team_3.service.NhanVienService;

/**
 *
 * @author trangntph19494
 */
public class NhanVienServiceImpl implements NhanVienService{
    
    private NhanVienRepository repos;

    public NhanVienServiceImpl() {
        repos = new NhanVienRepository();
    }
    
    @Override
    public List<NhanVienDto> getAllReponse() {
        return repos.getAllResponse();
    }

    @Override
    public String insert(NhanVien user) {
        if (user.getTenDangNhap().trim().isEmpty()) {
            return "Tên đăng nhập không được để trống";
        }
        else if (user.getHoTen().trim().isEmpty()) {
            return "Họ và tên không được để trống";
        }
        else if (user.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        else if (user.getEmail().trim().isEmpty()) {
            return "Email không được để trống";
        }
        else if (user.getSdt().trim().isEmpty()) {
            return "SĐT không được để trống";
        }
        user = repos.saveOrUpdate(user);
        if (user != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String delete(UUID id) {
        NhanVien userFind = repos.findById(id);
        if (userFind == null) {
            return "Không tìm thấy";
        }
        boolean delete = repos.detele(userFind);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public List<NhanVien> getAll() {
        return repos.getAll();
    }

    @Override
    public String update(NhanVien user) {
       NhanVien userFindById = repos.findById(user.getId());
        if (userFindById == null) {
            return "Không tìm thấy";
        }
        if (user.getHoTen().trim().isEmpty()) {
            return "Họ và tên không được trống";
        }
        if (user.getMatKhau().trim().isEmpty()) {
            return "Mật khẩu không được trống";
        }
        if (user.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (user.getTenDangNhap().trim().isEmpty()) {
            return "Tên đăng nhập không được trống";
        }
        
        if (user.getSdt().trim().isEmpty()) {
            return "Số điện thoại không được trống";
        }
        
        
        
        userFindById.setTenDangNhap(user.getTenDangNhap());
        userFindById.setHoTen(user.getHoTen());
        userFindById.setEmail(user.getEmail());
        userFindById.setSdt(user.getSdt());
        userFindById.setMatKhau(user.getMatKhau());
        userFindById.setGioiTinh(user.getGioiTinh());
        userFindById.setVaiTro(user.getVaiTro());
        userFindById.setId(user.getId());
        user = repos.saveOrUpdate(userFindById);
        if (user != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<NhanVienDto> findByTenDangNhap(String keyWord) {
        return repos.findByTenDangNhap(keyWord);
    }
}
