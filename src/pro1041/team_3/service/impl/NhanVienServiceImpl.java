package pro1041.team_3.service.impl;

import java.time.LocalDateTime;
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
public class NhanVienServiceImpl implements NhanVienService {

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
        user.setId(null);
        NhanVien findSDT = repos.findSDT(user.getSdt());
        NhanVien findEmail = repos.findEmail(user.getEmail());
        NhanVien findTenDangNhap = repos.findTenDangNhap(user.getTenDangNhap());
        LocalDateTime time = LocalDateTime.now();
        String maNV = "NV" + time.getSecond() + time.getMinute() + time.getHour();
        user.setMa(maNV);
        user.setTrangThaiLamViec(0);
        if (user.getTenDangNhap().trim().isEmpty()) {
            return "Tên đăng nhập không được trống";
        }
        if (user.getTenDangNhap().length() > 30) {
            return "Tên đăng nhập không quá 30 ký tự";
        }
        if (findTenDangNhap != null) {
            return "Tên đăng nhập không được trùng";
        }
        if (user.getHoTen().trim().isEmpty()) {
            return "Họ tên không được trống";
        }
        if (user.getHoTen().length() > 100) {
            return "Họ tên không quá 100 ký tự";
        }
        if (user.getSdt().trim().isEmpty()) {
            return "SĐT không được trống";
        }
        if (findSDT != null) {
            return "SĐT không được trùng";
        }
        if (!user.getSdt().matches("^\\d+$")) {
            return "SĐT phải là số";
        }
        if (user.getSdt().length() > 20) {
            return "Số điện thoại tối thiểu 20 số";
        }
        if (user.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }
        if (user.getEmail().length() > 100) {
            return "Email không quá 100 ký tự";
        }
        if (findEmail != null) {
            return "Email không được trùng";
        }
        if (!user.getEmail().matches("^\\S+@\\S+$")) {
            return "Email không đúng định dạng";
        }
        if (user.getMatKhau().trim().isEmpty()) {
            return "Mật khẩu không được trống";
        }
        if (user.getMatKhau().length() > 50 || user.getMatKhau().length() < 6) {
            return "Mật khẩu phải trên 6 ký tự và không quá 50 ký tự";
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
        NhanVien findSDT = repos.findSDT(user.getSdt());
        NhanVien findEmail = repos.findEmail(user.getEmail());
        NhanVien findTenDangNhap = repos.findTenDangNhap(user.getTenDangNhap());
        NhanVien userFindById = repos.findByMa(user.getMa());
        if (userFindById == null) {
            return "Không tìm thấy";
        }
        if (user.getTenDangNhap().trim().isEmpty()) {
            return "Tên đăng nhập không được trống";
        }
        if (user.getTenDangNhap().length() > 30) {
            return "Tên đăng nhập không quá 30 ký tự";
        }
        if (!user.getTenDangNhap().equals(userFindById.getTenDangNhap())) {
            if (findTenDangNhap != null) {
                return "Tên đăng nhập không được trùng";
            } else {
                userFindById.setTenDangNhap(user.getTenDangNhap());
            }
        }
        if (user.getHoTen().trim().isEmpty()) {
            return "Họ tên không được trống";
        }
        if (user.getHoTen().length() > 100) {
            return "Họ tên không quá 100 ký tự";
        }
        if (user.getSdt().trim().isEmpty()) {
            return "SĐT không được trống";
        }
        if (!user.getSdt().equals(userFindById.getSdt())) {
            if (findSDT != null) {
                return "SĐT không được trùng";
            } else {
                userFindById.setSdt(user.getSdt());
            }
        }
        if (!user.getSdt().matches("^\\d+$")) {
            return "SĐT phải là số";
        }
        if (user.getSdt().length() > 20) {
            return "Số điện thoại tối thiểu 20 số";
        }
        if (user.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }
        if (user.getEmail().length() > 100) {
            return "Email không quá 100 ký tự";
        }
        if (!user.getEmail().equals(userFindById.getEmail())) {
            if (findEmail != null) {
                return "Email không được trùng";
            } else {
                userFindById.setEmail(user.getEmail());
            }
        }
        if (!user.getEmail().matches("^\\S+@\\S+$")) {
            return "Email không đúng định dạng";
        }
        if (user.getMatKhau().trim().isEmpty()) {
            return "Mật khẩu không được trống";
        }
        if (user.getMatKhau().length() > 50 || user.getMatKhau().length() < 6) {
            return "Mật khẩu phải trên 6 ký tự và không quá 50 ký tự";
        }

        userFindById.setHoTen(user.getHoTen());
        userFindById.setMatKhau(user.getMatKhau());
        userFindById.setGioiTinh(user.getGioiTinh());
        userFindById.setVaiTro(user.getVaiTro());
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

    @Override
    public boolean updateTrangThai(String ma) {
        return repos.updateTrangThai(ma);
    }

    @Override
    public List<NhanVienDto> getAllByTrangThai0() {
        return repos.getAllByTrangThai0();
    }

    @Override
    public List<NhanVienDto> findNhanVien(String key) {
        return repos.findNhanVien(key);
    }

    @Override
    public boolean khoiPhucTrangThai(String ma) {
        return repos.khoiPhucTrangThai(ma);
    }

    @Override
    public List<NhanVienDto> getAllByTrangThai1() {
        return repos.getAllByTrangThai1();
    }

    @Override
    public List<NhanVienDto> findNhanVienNghiViec(String key) {
        return repos.findNhanVienNghiViec(key);
    }
}
