/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import lombok.var;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.dto.KhachHangDto;
import pro1041.team_3.repository.KhachHangRepository;
import pro1041.team_3.service.IKhachHangService;

/**
 *
 * @author Admin
 */
public class KhachHangServiceImpl implements IKhachHangService {

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
        if (khachHang.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (!khachHang.getMa().matches("^(KH)\\d{5}$")) {
            return "Mã không đúng định dạng";
        }
        if (khachHang.getHoTen().trim().isEmpty()) {
            return "Họ tên không được trống";
        }
        if (khachHang.getSdt().trim().isEmpty()) {
            return "SĐT không được trống";
        }
        if (!khachHang.getSdt().matches("^(0|\\+84)[1-9][0-9]{8,9}$")) {
            return "SĐT không đúng định dạng";
        }
        if (khachHang.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (khachHang.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }
        if (!khachHang.getEmail().matches("^\\S+@\\S+\\.\\S+$")) {
            return "Email không đúng định dạng";
        }
        KhachHang khachHangFind = khachHangRepository.findByMa(khachHang.getMa());
        if (khachHangFind != null) {
            return "Mã không được trùng";
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
        KhachHang khachHangFindById = khachHangRepository.findById(khachHang.getId());
        if (khachHangFindById == null) {
            return "Không tìm thấy";
        }
        if (khachHang.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (!khachHang.getMa().matches("^(KH)\\d{5}$")) {
            return "Mã không đúng định dạng";
        }
        if (khachHang.getHoTen().trim().isEmpty()) {
            return "Họ tên không được trống";
        }
        if (khachHang.getSdt().trim().isEmpty()) {
            return "SĐT không được trống";
        }
        if (!khachHang.getSdt().matches("^(0|\\+84)[1-9][0-9]{8,9}$")) {
            return "SĐT không đúng định dạng";
        }
        if (khachHang.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (khachHang.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }
        if (!khachHang.getEmail().matches("^\\S+@\\S+\\.\\S+$")) {
            return "Email không đúng định dạng";
        }
        if (!khachHang.getMa().equals(khachHangFindById.getMa())) {
            KhachHang khachHangFindByMa = khachHangRepository.findByMa(khachHang.getMa());
            if (khachHangFindByMa != null) {
                return "Mã không được trùng";
            } else {
                khachHangFindById.setMa(khachHang.getMa());
            }
        }
        khachHangFindById.setHoTen(khachHang.getHoTen());
        khachHangFindById.setSdt(khachHang.getSdt());
        khachHangFindById.setNgaySinh(khachHang.getNgaySinh());
        khachHangFindById.setDiaChi(khachHang.getDiaChi());
        khachHangFindById.setEmail(khachHang.getEmail());
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

    public boolean export(File file) {
        List<KhachHang> lst = khachHangRepository.getAll();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách khách hàng");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell idTitle = firstRow.createCell(0);
            idTitle.setCellValue("ID");
            Cell maKHTitle = firstRow.createCell(1);
            maKHTitle.setCellValue("Mã khách hàng");
            Cell hoTenTitle = firstRow.createCell(2);
            hoTenTitle.setCellValue("Họ tên");
            Cell gioiTinhTitle = firstRow.createCell(3);
            gioiTinhTitle.setCellValue("Giới tính");
            Cell ngayTitle = firstRow.createCell(4);
            ngayTitle.setCellValue("Ngày sinh");
            Cell diaChiTitle = firstRow.createCell(5);
            diaChiTitle.setCellValue("Địa chỉ");
            Cell sdtTitle = firstRow.createCell(6);
            sdtTitle.setCellValue("SĐT");
            Cell emailTitle = firstRow.createCell(7);
            emailTitle.setCellValue("Email");

            for (KhachHang x : lst) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(x.getId().toString());
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(x.getMa());
                Cell cell3 = row.createCell(2);
                cell3.setCellValue(x.getHoTen());
                Cell cell4 = row.createCell(3);
                cell4.setCellValue(x.getGioiTinh() == 0 ? "Nam" : "Nữ");
                Cell cell5 = row.createCell(4);
                cell5.setCellValue(x.getNgaySinh().toString());
                Cell cell6 = row.createCell(5);
                cell6.setCellValue(x.getDiaChi());
                Cell cell7 = row.createCell(6);
                cell7.setCellValue(x.getSdt());
                Cell cell8 = row.createCell(7);
                cell8.setCellValue(x.getEmail());
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
}
