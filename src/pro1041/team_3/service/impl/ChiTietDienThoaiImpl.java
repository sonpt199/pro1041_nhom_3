package pro1041.team_3.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.repository.ChiTietDienThoaiRepository;
import pro1041.team_3.service.ChiTietDienThoaiService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author trangdttph27721
 */
public class ChiTietDienThoaiImpl implements ChiTietDienThoaiService{

    private ChiTietDienThoaiRepository chiTietDienThoaiRepository;
    public ChiTietDienThoaiImpl() {
        this.chiTietDienThoaiRepository = new ChiTietDienThoaiRepository();
    }

    @Override
    public List<ChiTietDienThoaiResponse> getAllResponse() {
        return this.chiTietDienThoaiRepository.getAllResponse();
    }

    @Override
    public void insert(ChiTietDienThoai chiTietDT) {
        LocalDateTime time = LocalDateTime.now();
        String ma = "CTDT" + time.getSecond() + time.getMinute() + time.getHour();
        chiTietDT.setMa(ma);
        this.chiTietDienThoaiRepository.saveOrUpdate(chiTietDT);
    }

    @Override
    public void update(ChiTietDienThoai chiTietDT) {
        
        this.chiTietDienThoaiRepository.saveOrUpdate(chiTietDT);
    }

    @Override
    public String delete(String id) {
        ChiTietDienThoai chiTietDT = chiTietDienThoaiRepository.findByMa(id);
        if (chiTietDT == null) {
            return "Chi tiết sản phẩm không tồn tại";
        }
        if (chiTietDienThoaiRepository.detele(chiTietDT)) {
            return "Xóa thành công";
        } else {
            return "Lỗi. Xóa thất bại";
        }
    }

    @Override
    public ChiTietDienThoai getID(String ma) {
        ChiTietDienThoai chiTietDT = chiTietDienThoaiRepository.findByMa(ma);
        return chiTietDT;
    }

    @Override
    public List<ChiTietDienThoaiResponse> findBy(String keyWord) {
        return this.chiTietDienThoaiRepository.findBy(keyWord);
    }

    @Override
    public List<ChiTietDienThoaiResponse> sapXep(String chieu) {
        return this.chiTietDienThoaiRepository.sapXep(chieu);
    }
    
    @Override
    public List<ChiTietDienThoaiResponse> sapXep2(String loai, String chieu) {
        return this.chiTietDienThoaiRepository.sapXep2(loai, chieu);
    }

    @Override
    public List<ChiTietDienThoaiResponse> getAllTrangThai(int trangThai) {
        return this.chiTietDienThoaiRepository.getAllTrangThai(trangThai);
    }

    public boolean export(File file){
        List<ChiTietDienThoai> lst = chiTietDienThoaiRepository.getAll();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách chi tiết điện thoại");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell idTitle = firstRow.createCell(0);
            idTitle.setCellValue("ID");
            
            Cell ctDtMa = firstRow.createCell(1);
            ctDtMa.setCellValue("Mã");
            
            Cell idMauSacTitle = firstRow.createCell(2);
            idMauSacTitle.setCellValue("Màu sắc");
            
            Cell idDienThoaiTitle = firstRow.createCell(3);
            idDienThoaiTitle.setCellValue("Điện thoại");
            
            Cell idHangTitle = firstRow.createCell(4);
            idHangTitle.setCellValue("Hãng");
            
            Cell tinhTrangTitle = firstRow.createCell(5);
            tinhTrangTitle.setCellValue("Tình trạng");
            
            Cell giaBanTitle = firstRow.createCell(6);
            giaBanTitle.setCellValue("Giá bán");
            
            Cell trangThaiTitle = firstRow.createCell(7);
            trangThaiTitle.setCellValue("Trạng thái");
            
            Cell imeiTitle = firstRow.createCell(8);
            imeiTitle.setCellValue("Imei");
            
            Cell ramTitle = firstRow.createCell(9);
            ramTitle.setCellValue("Ram");
            
            Cell romTitle = firstRow.createCell(10);
            romTitle.setCellValue("Rom");
            
            Cell moTaTitle = firstRow.createCell(11);
            moTaTitle.setCellValue("Mô tả");
            
            Cell baoHanhTitle = firstRow.createCell(12);
            baoHanhTitle.setCellValue("Bảo hành");
            
            for (ChiTietDienThoai x : lst) {
                Row row = sheet.createRow(rowNum++);
                
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(x.getId().toString());
                
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(x.getMa());
                
                Cell cell3 = row.createCell(2);
                cell3.setCellValue(x.getMauSac().getTen());
                
                Cell cell4 = row.createCell(3);
                cell4.setCellValue(x.getDienThoai().getTen());
                
                Cell cell5 = row.createCell(4);
                cell5.setCellValue(x.getHang().getTen());
                
                Cell cell6 = row.createCell(5);
                cell6.setCellValue(x.getTinhTrang() == 1 ? "Mới" : "Cũ");
                
                Cell cell7 = row.createCell(6);
                cell7.setCellValue(x.getDonGia()+"");
                
                Cell cell8 = row.createCell(7);
                cell8.setCellValue(x.getTrangThai() ==0 ? "Đang bán" : x.getTrangThai() == 1 ? "Đã bán" : "Sản phẩm lỗi");
                
                Cell cell9 = row.createCell(8);
                cell9.setCellValue(x.getImei());
                
                Cell cell10 = row.createCell(9);
                cell10.setCellValue(x.getRam());
                
                Cell cell11 = row.createCell(10);
                cell11.setCellValue(x.getBoNho());
                
                Cell cell12 = row.createCell(11);
                cell12.setCellValue(x.getMoTa());
                
                Cell cell13 = row.createCell(12);
                cell13.setCellValue(x.getThoiGianBaoHanh()+"");
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
    public List<ChiTietDienThoaiResponse> getAllDienThoaiNotInKM(UUID id) {
        return chiTietDienThoaiRepository.getAllDienThoaiNotInKM(id);
    }
    
}
