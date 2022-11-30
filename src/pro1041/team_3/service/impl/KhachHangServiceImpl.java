package pro1041.team_3.service.impl;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import lombok.var;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.dto.KhachHangDto;
import pro1041.team_3.repository.KhachHangRepository;
import pro1041.team_3.service.KhachHangService;

/**
 *
 * @author hanhltph27725
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository khachHangRepository;
    private ArrayList<KhachHangDto> _lstKhachHang;

    public KhachHangServiceImpl() {
        khachHangRepository = new KhachHangRepository();
        _lstKhachHang = new ArrayList<>();
    }

    @Override
    public ArrayList<KhachHangDto> getAll() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        _lstKhachHang = new ArrayList<>();
        List<KhachHang> listKh = khachHangRepository.getAll();
        for (KhachHang x : listKh) {
            _lstKhachHang.add(new KhachHangDto(x.getId(), x.getMa(), x.getHoTen(), new java.sql.Date(x.getNgaySinh().getTime()),
                    x.getGioiTinh(), x.getSdt(), x.getDiaChi(), x.getEmail()));
        }
        return _lstKhachHang;
    }

    @Override
    public String insert(KhachHang khachHang) {
        khachHang.setId(null);
        KhachHang findSDT = khachHangRepository.findSDT(khachHang.getSdt());
        KhachHang findEmail = khachHangRepository.findEmail(khachHang.getEmail());
        LocalDateTime time = LocalDateTime.now();
        String maKH = "KH" + time.getSecond() + time.getMinute() + time.getHour();
        khachHang.setMa(maKH);
        if (khachHang.getHoTen().trim().isEmpty()) {
            return "Họ tên không được trống";
        }
        if (khachHang.getHoTen().length() > 100) {
            return "Họ tên không quá 100 ký tự";
        }
        if (khachHang.getSdt().trim().isEmpty()) {
            return "SĐT không được trống";
        }
        if (findSDT != null) {
            return "SĐT không được trùng";
        }
        if (!khachHang.getSdt().matches("^\\d+$")) {
            return "SĐT phải là số";
        }
        if (khachHang.getSdt().length() > 20) {
            return "Số điện thoại tối thiểu 20 số";
        }
        if (khachHang.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }
        if (khachHang.getEmail().length() > 100) {
            return "Email không quá 100 ký tự";
        }
        if (findEmail != null) {
            return "Email không được trùng";
        }
        if (!khachHang.getEmail().matches("^\\S+@\\S+$")) {
            return "Email không đúng định dạng";
        }
        khachHang = khachHangRepository.saveOrUpdate(khachHang);
        if (khachHang != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String delete(UUID id) {
        KhachHang khachHangFind = khachHangRepository.findById(id);
        if (khachHangFind == null) {
            return "Không tìm thấy";
        }
        boolean delete = khachHangRepository.detele(khachHangFind);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public String update(KhachHang khachHang) {
        KhachHang khachHangFindById = khachHangRepository.findByMa(khachHang.getMa());
        KhachHang findSDT = khachHangRepository.findSDT(khachHang.getSdt());
        KhachHang findEmail = khachHangRepository.findEmail(khachHang.getEmail());
        if (khachHangFindById == null) {
            return "Không tìm thấy";
        }
        if (khachHang.getHoTen().trim().isEmpty()) {
            return "Họ tên không được trống";
        }
        if (khachHang.getHoTen().length() > 100) {
            return "Họ tên không quá 100 ký tự";
        }
        if (khachHang.getSdt().trim().isEmpty()) {
            return "SĐT không được trống";
        }
        if (!khachHang.getSdt().equals(khachHangFindById.getSdt())) {
            if (findSDT != null) {
                return "SĐT không được trùng";
            } else {
                khachHangFindById.setSdt(khachHang.getSdt());
            }
        }
        if (!khachHang.getSdt().matches("^\\d+$")) {
            return "SĐT phải là số";
        }
        if (khachHang.getSdt().length() > 20) {
            return "Số điện thoại tối thiểu 20 số";
        }
        if (khachHang.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }
        if (khachHang.getEmail().length() > 100) {
            return "Email không quá 100 ký tự";
        }
        if (!khachHang.getEmail().equals(khachHangFindById.getEmail())) {
            if (findEmail != null) {
                return "Email không được trùng";
            } else {
                khachHangFindById.setEmail(khachHang.getEmail());
            }
        }
        if (!khachHang.getEmail().matches("^\\S+@\\S+$")) {
            return "Email không đúng định dạng";
        }
        khachHangFindById.setHoTen(khachHang.getHoTen());
        khachHangFindById.setNgaySinh(khachHang.getNgaySinh());
        khachHangFindById.setDiaChi(khachHang.getDiaChi());
        khachHangFindById.setGioiTinh(khachHang.getGioiTinh());
        khachHang = khachHangRepository.saveOrUpdate(khachHangFindById);
        if (khachHang != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<KhachHangDto> findKhachHang(String key) {
        _lstKhachHang = new ArrayList<>();
        List<KhachHang> listKh = khachHangRepository.findKhachHang(key);
        for (KhachHang x : listKh) {
            _lstKhachHang.add(new KhachHangDto(x.getId(), x.getMa(), x.getHoTen(), new java.sql.Date(x.getNgaySinh().getTime()),
                    x.getGioiTinh(), x.getSdt(), x.getDiaChi(), x.getEmail()));
        }
        return _lstKhachHang;
    }

}
