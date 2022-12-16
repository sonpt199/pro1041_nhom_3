package pro1041.team_3.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.NhanVienDto;

/**
 *
 * @author trangntph19494
 */
public interface NhanVienService {

    List<NhanVienDto> getAllReponse();

    String insert(NhanVien user);

    String update(NhanVien user);

    String delete(UUID id);

    List<NhanVien> getAll();

    List<NhanVienDto> findByTenDangNhap(String keyWord);

    boolean updateTrangThai(String ma);

    boolean khoiPhucTrangThai(String ma);

    List<NhanVienDto> getAllByTrangThai0();

    List<NhanVienDto> getAllByTrangThai1();

    List<NhanVienDto> findNhanVien(String key);

    List<NhanVienDto> findNhanVienNghiViec(String key);

    boolean exportNhanVien(File file);

    boolean exportNhanVienDangLam(File file);

    boolean exportNhanVienNghiLam(File file);

    NhanVien findNVByUserNameAndMatKhau(String userName, String matKhau);

    boolean updateMatKhau(UUID id, String matKhau);
}
