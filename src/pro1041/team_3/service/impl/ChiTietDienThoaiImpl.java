package pro1041.team_3.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.repository.ChiTietDienThoaiRepository;
import pro1041.team_3.service.ChiTietDienThoaiService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.repository.DienThoaiKhuyenMaiRepository;

/**
 *
 * @author trangdttph27721
 */
public class ChiTietDienThoaiImpl implements ChiTietDienThoaiService {

    public static final int COLUMN_INDEX_MAUSAC = 0;
    public static final int COLUMN_INDEX_DIEN_THOAI = 1;
    public static final int COLUMN_INDEX_HANG = 2;
    public static final int COLUMN_INDEX_TINH_TRANG = 3;
    public static final int COLUMN_INDEX_DON_GIA = 4;
    public static final int COLUMN_INDEX_TRANG_THAI = 5;
    public static final int COLUMN_INDEX_IMEI = 6;
    public static final int COLUMN_INDEX_RAM = 7;
    public static final int COLUMN_INDEX_BO_NHO = 8;
    public static final int COLUMN_INDEX_MO_TA = 9;
    public static final int COLUMN_INDEX_TG_BAO_HANH = 10;
    public static final MauSacServiceImpl mauSacServiceImpl = new MauSacServiceImpl();
    public static final DienThoaiServiceImpl DIEN_THOAI_SERVICE_IMPL = new DienThoaiServiceImpl();
    public static final HangServiceImpl HANG_SERVICE_IMPL = new HangServiceImpl();
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
//        chiTietDT.setMa(ma);
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

    public boolean export(File file) {
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
                cell2.setCellValue(x.getDienThoai().getMa());

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(x.getMauSac().getTen());

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(x.getDienThoai().getTen());

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(x.getHang().getTen());

                Cell cell6 = row.createCell(5);
                cell6.setCellValue(x.getTinhTrang()+"");

                Cell cell7 = row.createCell(6);
                cell7.setCellValue(x.getDonGia() + "");

                Cell cell8 = row.createCell(7);
                cell8.setCellValue(x.getTrangThai() == 0 ? "Đang bán" : x.getTrangThai() == 1 ? "Đã bán" : "Sản phẩm lỗi");

                Cell cell9 = row.createCell(8);
                cell9.setCellValue(x.getImei());

                Cell cell10 = row.createCell(9);
                cell10.setCellValue(x.getRam());

                Cell cell11 = row.createCell(10);
                cell11.setCellValue(x.getBoNho());

                Cell cell12 = row.createCell(11);
                cell12.setCellValue(x.getMoTa());

                Cell cell13 = row.createCell(12);
                cell13.setCellValue(x.getThoiGianBaoHanh() + "");
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

//    @Override
//    public List<ChiTietDienThoaiResponse> getAllDienThoaiNotInKM(UUID id) {
//        return chiTietDienThoaiRepository.getAllDienThoaiNotInKM(id);
//    }
    @Override
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByDienThoai(String tenDienThoai, Date batDau, Date ketThuc) {
        return chiTietDienThoaiRepository.getAllCTDienThoaiByDienThoai(tenDienThoai, batDau, ketThuc);
    }

    @Override
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByHang(String tenHang, Date batDau, Date ketThuc) {
        return chiTietDienThoaiRepository.getAllCTDienThoaiByHang(tenHang, batDau, ketThuc);
    }

    @Override
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByMauSac(String tenMauSac, Date batDau, Date ketThuc) {
        return chiTietDienThoaiRepository.getAllCTDienThoaiByMauSac(tenMauSac, batDau, ketThuc);
    }

//    @Override
//    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByDienThoai(UUID id, String tenDienThoai) {
//        return chiTietDienThoaiRepository.getAllCTDTNotInKMByDienThoai(id, tenDienThoai);
//    }
//
//    @Override
//    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByHang(UUID id, String tenHang) {
//        return chiTietDienThoaiRepository.getAllCTDTNotInKMByHang(id, tenHang);
//    }
//
//    @Override
//    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByMauSac(UUID id, String tenMauSac) {
//        return chiTietDienThoaiRepository.getAllCTDTNotInKMByMauSac(id, tenMauSac);
//    }
//
//    @Override
//    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMByTinhTrang(UUID id, int tinhTrang) {
//        return chiTietDienThoaiRepository.getAllCTDTNotInKMByTinhTrang(id, tinhTrang);
//    }
    @Override
    public List<ChiTietDienThoaiResponse> getAllCTDienThoaiByTinhTrang(int tinhTrang, Date batDau, Date ketThuc) {
        return chiTietDienThoaiRepository.getAllCTDienThoaiByTinhTrang(tinhTrang, batDau, ketThuc);
    }

    @Override
    public String exportQr(String pathFolder, UUID idChiTietDienThoai) {
        ChiTietDienThoaiResponse ctdt = chiTietDienThoaiRepository.getResponseById(idChiTietDienThoai);
        if (ctdt == null) {
            return "Không tìm thấy điện thoại";
        }
        DecimalFormat moneyFormat = new DecimalFormat("#,###");
        String data = "Mã điện thoại: " + ctdt.getMaDienThoai()
                + "\nIMEI: " + ctdt.getImei()
                + "\nTên điện thoại: " + ctdt.getDienThoai()
                + "\nHãng: " + ctdt.getHang()
                + "\nMàu sắc: " + ctdt.getMauSac()
                + "\nTình trạng: " + (ctdt.getTinhTrang() == 100 ? "Mới" : "Cũ - " + ctdt.getTinhTrang() + "%")
                + "\nĐơn giá: " + moneyFormat.format(ctdt.getDonGia()) + "VNĐ"
                + "\nRam: " + ctdt.getRam()
                + "\nBộ nhớ: " + ctdt.getBoNho()
                + "\nThời gian bảo hành: " + ctdt.getThoiGianBaoHanh() + " tháng"
                + "\nTrạng thái: " + (ctdt.getTrangThai() == 0 ? "Đang bán" : ctdt.getTrangThai() == 1 ? "Đã bán" : ctdt.getTrangThai() == 2 ? "Sản phẩm lỗi" : "")
                + (ctdt.getMoTa() == null ? "" : "\nMô tả: " + ctdt.getMoTa());
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Write to file image
            Path path = FileSystems.getDefault().getPath(pathFolder + "\\" + ctdt.getMaDienThoai() + "-" + ctdt.getDienThoai() + ".png");
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        } catch (Exception ex) {
            Logger.getLogger(DienThoaiKhuyenMaiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "Lỗi hệ thống. Không thể export";
        }
        return "Tải thành công";
    }

    @Override
    public String importFile(File file) {
        List<ChiTietDienThoai> listCTDT = new ArrayList<>();
        String ketQua;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0 || nextRow.getRowNum() == 1) {
                    continue;
                }

                Iterator<Cell> cellIterator = nextRow.cellIterator();

                ChiTietDienThoai ctdt = new ChiTietDienThoai();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }

                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_MAUSAC:
                            String mauSacStr = ((String) getCellValue(cell)).trim();
                            System.out.println(mauSacStr);
                            if (mauSacStr == null) {
                                ketQua = "Màu sắc không được để trống";
                                return ketQua;
                            }
                            MauSac ms = mauSacServiceImpl.findMauSacByName(mauSacStr);
                            if (ms == null) {
                                MauSac mauSac = new MauSac();
                                mauSac.setTen(mauSacStr);
                                String imsertMau = mauSacServiceImpl.insert(mauSac);
                                ms = mauSacServiceImpl.findMauSacByName(mauSacStr);
                            }
                            ctdt.setMauSac(ms);
                            break;
                        case COLUMN_INDEX_DIEN_THOAI:
                            String dienThoaiStr = ((String) getCellValue(cell)).trim();
                            if (dienThoaiStr.length() == 0) {
                                ketQua = "Tên điện thoại không được để trống";
                                return ketQua;
                            }
                            DienThoai dt = DIEN_THOAI_SERVICE_IMPL.findDienThoaiByName(dienThoaiStr);
                            if (dt == null) {
                                DienThoai dienThoai = new DienThoai();
                                dienThoai.setTen(dienThoaiStr);
                                String imsertDienThoai = DIEN_THOAI_SERVICE_IMPL.insert(dienThoai);
                                dt = DIEN_THOAI_SERVICE_IMPL.findDienThoaiByName(dienThoaiStr);
                            }
                            ctdt.setDienThoai(dt);
                            break;
                        case COLUMN_INDEX_IMEI:
//                    ctdt.setImei(new BigDecimal((double) cellValue).intValue());
                            String imei = ((String) getCellValue(cell)).trim();
                            if (imei.length() == 0) {
                                ketQua = "Imei không được để trống";
                                return ketQua;
                            }
                            ChiTietDienThoai ctdtCheck = chiTietDienThoaiRepository.checkImei(imei);
                            if (ctdtCheck != null) {
                                ketQua = "Imei đã tồn tại";
                                return ketQua;
                            }
                            ctdt.setImei((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_HANG:
                            String hangStr = (String) getCellValue(cell);
                            if (hangStr.length() == 0) {
                                ketQua = "Tên hãng không được để trống";
                                return ketQua;
                            }
                            Hang hang = HANG_SERVICE_IMPL.findHangByName(hangStr);
                            if (hang == null) {
                                Hang h = new Hang();
                                h.setTen(hangStr);
                                String imsertHang = HANG_SERVICE_IMPL.insert(h);
                                hang = HANG_SERVICE_IMPL.findHangByName(hangStr);
                            }
//                    ctdt.setPrice((Double) getCellValue(cell));
                            ctdt.setHang(hang);
                            break;
                        case COLUMN_INDEX_RAM:
                            Integer ram = null;
                            try {
                                ram = ((Double) getCellValue(cell)).intValue();
                                if (ram <= 0) {
                                    ketQua = "Ram điện thoại phải lớn hơn 0";
                                    return ketQua;
                                }
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                                ketQua = "Ram điện thoại phải là số";
                                    return ketQua;
                            }
                            ctdt.setRam(ram);
                            break;
                        case COLUMN_INDEX_BO_NHO:
                            Integer boNho = null;
                            try {
                                boNho = ((Double) getCellValue(cell)).intValue();
                                if (boNho <= 0) {
                                    ketQua = "Bộ nhớ điện thoại phải lớn hơn 0";
                                    return ketQua;
                                }
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                                ketQua = "Bộ nhớ điện thoại phải là số";
                                    return ketQua;
                            }
                            ctdt.setBoNho(boNho);
                            break;
                        case COLUMN_INDEX_TINH_TRANG:
                            Integer tinhTrang = null;
                            try {
                                tinhTrang = ((Double) getCellValue(cell)).intValue();
                                if (tinhTrang <= 0) {
                                    ketQua = "Tình trạng điện thoại phải lớn hơn 0";
                                    return ketQua;
                                }
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                                ketQua = "Tình trạng điện thoại phải là số";
                                    return ketQua;
                            }
                            ctdt.setTinhTrang(tinhTrang);
                            break;
                        case COLUMN_INDEX_TRANG_THAI:
                            String trangThaiStr = ((String) getCellValue(cell)).trim();
                            if (trangThaiStr.length() == 0) {
                                ketQua = "Trạng thái không được để trống";
                                return ketQua;
                            }
                            Integer trangThai = -1;
                            if (trangThaiStr.equals("Đang bán")) {
                                trangThai = 0;
                            } else if (trangThaiStr.equals("Đã bán")) {
                                trangThai = 1;
                            } else {
                                trangThai = 2;
                            }
                            ctdt.setTrangThai(trangThai);
                            break;
                        case COLUMN_INDEX_DON_GIA:
                            BigDecimal giaBan = null;
                            try {
                                giaBan = new BigDecimal((double) cellValue);
                                if (giaBan.compareTo(BigDecimal.ZERO) == -1) {
                                    ketQua = "Giá bán không được nhỏ hơn 0";
                                    return ketQua;
                                }
                            } catch (Exception e) {
                                ketQua = "Giá bán phải là số";
                                e.printStackTrace();
                                return ketQua;
                            }
                            ctdt.setDonGia(giaBan);
                            break;
                        case COLUMN_INDEX_MO_TA:
                            ctdt.setMoTa((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_TG_BAO_HANH:
                            Integer baoHanh = null;
                            try {
                                baoHanh = ((Double) getCellValue(cell)).intValue();
                                if (baoHanh <= 0) {
                                    ketQua = "Thời gian bảo hành điện thoại phải lớn hơn 0";
                                    return ketQua;
                                }
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                                ketQua = "Thời gian bảo hành điện thoại phải là số";
                                return ketQua;
                            }
                            ctdt.setThoiGianBaoHanh(((Double) getCellValue(cell)).intValue());
                            break;
                        default:
                            break;
                    }

                }
                listCTDT.add(ctdt);
                
                boolean save = chiTietDienThoaiRepository.saveAll(listCTDT);
                if (!save) {
                    ketQua = "Import thất bại";
                    return ketQua;
                }
            }

            workbook.close();
            inputStream.close();
            return "Import thành công";
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public List<ChiTietDienThoaiResponse> getAllCTDTNotInKMTrung(Date batDau, Date ketThuc) {
        return chiTietDienThoaiRepository.getAllCTDTNotInKMTrung(batDau, ketThuc);
    }

    @Override
    public ChiTietDienThoai checkImei(String imei) {
        return chiTietDienThoaiRepository.checkImei(imei);
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
//        CellType cellType = cell.getCellTypeEnum();
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

    @Override
    public boolean saveAll(List<ChiTietDienThoai> list) {
        return chiTietDienThoaiRepository.saveAll(list);
    }

}
