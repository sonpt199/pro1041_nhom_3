package pro1041.team_3.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.NhanVienDto;
import pro1041.team_3.repository.NhanVienRepository;
import pro1041.team_3.service.NhanVienService;
import pro1041.team_3.util.EmailUtil;

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
//        user.setMatKhau(String.valueOf((int) (Math.random() * 10000000)));

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
//        if (user.getMatKhau().trim().isEmpty()) {
//            return "Mật khẩu không được trống";
//        }
//        if (user.getMatKhau().length() > 50 || user.getMatKhau().length() < 6) {
//            return "Mật khẩu phải trên 6 ký tự và không quá 50 ký tự";
//        }
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
//        if (user.getMatKhau().trim().isEmpty()) {
//            return "Mật khẩu không được trống";
//        }
//        if (user.getMatKhau().length() > 50 || user.getMatKhau().length() < 6) {
//            return "Mật khẩu phải trên 6 ký tự và không quá 50 ký tự";
//        }

        userFindById.setHoTen(user.getHoTen());
//        userFindById.setEmail(user.getMatKhau());
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

    @Override
    public boolean exportNhanVien(File file) {
        List<NhanVien> lst = repos.getAll();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách nhân viên");
            int rowNum = 0;
            int stt = 1;
            Row firstRow = sheet.createRow(rowNum++);

            Cell soTT = firstRow.createCell(0);
            soTT.setCellValue("STT");

            Cell maNhanVien = firstRow.createCell(1);
            maNhanVien.setCellValue("Mã nhân viên");

            Cell tenDangNhap = firstRow.createCell(2);
            tenDangNhap.setCellValue("Tên đăng nhập");

            Cell hoTen = firstRow.createCell(3);
            hoTen.setCellValue("Họ tên");

            Cell gioiTinh = firstRow.createCell(4);
            gioiTinh.setCellValue("Giới tính");

            Cell sdt = firstRow.createCell(5);
            sdt.setCellValue("SĐT");

            Cell diaChi = firstRow.createCell(6);
            diaChi.setCellValue("Địa chỉ");

            Cell email = firstRow.createCell(7);
            email.setCellValue("Email");

            Cell matKhau = firstRow.createCell(8);
            matKhau.setCellValue("Mật khẩu");

            Cell vaiTro = firstRow.createCell(9);
            vaiTro.setCellValue("Vai trò");

            for (NhanVien x : lst) {
                Row row = sheet.createRow(rowNum++);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue(stt++);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(x.getMa());

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(x.getTenDangNhap());

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(x.getHoTen());

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(x.getGioiTinh() == 0 ? "Nam" : "Nữ");

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(x.getSdt());

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(x.getDiaChi());

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(x.getEmail());

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(x.getMatKhau());

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(x.getVaiTro() == 2 ? "Quản lý" : "Nhân viên");
            }
            workbook.write(fos);
            workbook.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean exportNhanVienDangLam(File file) {
        List<NhanVienDto> lst = repos.getAllByTrangThai0();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách nhân viên đang làm việc");
            int rowNum = 0;
            int stt = 1;
            Row firstRow = sheet.createRow(rowNum++);

            Cell soTT = firstRow.createCell(0);
            soTT.setCellValue("STT");

            Cell maNhanVien = firstRow.createCell(1);
            maNhanVien.setCellValue("Mã nhân viên");

            Cell tenDangNhap = firstRow.createCell(2);
            tenDangNhap.setCellValue("Tên đăng nhập");

            Cell hoTen = firstRow.createCell(3);
            hoTen.setCellValue("Họ tên");

            Cell gioiTinh = firstRow.createCell(4);
            gioiTinh.setCellValue("Giới tính");

            Cell sdt = firstRow.createCell(5);
            sdt.setCellValue("SĐT");

            Cell diaChi = firstRow.createCell(6);
            diaChi.setCellValue("Địa chỉ");

            Cell email = firstRow.createCell(7);
            email.setCellValue("Email");

            Cell matKhau = firstRow.createCell(8);
            matKhau.setCellValue("Mật khẩu");

            Cell vaiTro = firstRow.createCell(9);
            vaiTro.setCellValue("Vai trò");

            for (NhanVienDto x : lst) {
                Row row = sheet.createRow(rowNum++);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue(stt++);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(x.getMa());

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(x.getTenDangNhap());

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(x.getHoTen());

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(x.getGioiTinh() == 0 ? "Nam" : "Nữ");

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(x.getSdt());

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(x.getDiaChi());

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(x.getEmail());

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(x.getMatKhau());

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(x.getVaiTro() == 2 ? "Quản lý" : "Nhân viên");
            }
            workbook.write(fos);
            workbook.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean exportNhanVienNghiLam(File file) {
        List<NhanVienDto> lst = repos.getAllByTrangThai1();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách nhân viên đã nghỉ việc");
            int rowNum = 0;
            int stt = 1;
            Row firstRow = sheet.createRow(rowNum++);

            Cell soTT = firstRow.createCell(0);
            soTT.setCellValue("STT");

            Cell maNhanVien = firstRow.createCell(1);
            maNhanVien.setCellValue("Mã nhân viên");

            Cell tenDangNhap = firstRow.createCell(2);
            tenDangNhap.setCellValue("Tên đăng nhập");

            Cell hoTen = firstRow.createCell(3);
            hoTen.setCellValue("Họ tên");

            Cell gioiTinh = firstRow.createCell(4);
            gioiTinh.setCellValue("Giới tính");

            Cell sdt = firstRow.createCell(5);
            sdt.setCellValue("SĐT");

            Cell diaChi = firstRow.createCell(6);
            diaChi.setCellValue("Địa chỉ");

            Cell email = firstRow.createCell(7);
            email.setCellValue("Email");

            Cell matKhau = firstRow.createCell(8);
            matKhau.setCellValue("Mật khẩu");

            Cell vaiTro = firstRow.createCell(9);
            vaiTro.setCellValue("Vai trò");

            for (NhanVienDto x : lst) {
                Row row = sheet.createRow(rowNum++);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue(stt++);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(x.getMa());

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(x.getTenDangNhap());

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(x.getHoTen());

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(x.getGioiTinh() == 0 ? "Nam" : "Nữ");

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(x.getSdt());

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(x.getDiaChi());

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(x.getEmail());

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(x.getMatKhau());

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(x.getVaiTro() == 2 ? "Quản lý" : "Nhân viên");
            }
            workbook.write(fos);
            workbook.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateMatKhau(String tenDangNhap, String matKhau) {
        return repos.updateMatKhau(tenDangNhap, matKhau);
    }

    @Override
    public NhanVien findNVByUserNameAndMatKhau(String userName, String matKhau) {
        return repos.findNVByUserNameAndMatKhau(userName, matKhau);

    }

}
