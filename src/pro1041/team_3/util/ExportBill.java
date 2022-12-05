package pro1041.team_3.util;

import com.itextpdf.barcodes.Barcode1D;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.PdfContentByte;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import com.itextpdf.barcodes.BarcodeEAN;
import com.itextpdf.barcodes.Barcode1D;
import com.itextpdf.barcodes.Barcode39;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.barcodes.qrcode.EncodeHintType;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.dto.HoaDonPdfRequest;
import pro1041.team_3.dto.HoaDonRequest;

/**
 *
 * @author sonpt_ph19600
 */
public class ExportBill {

    public Document docPDF(HoaDonDto hoaDon, List<HoaDonChiTietDto> lstHdct, String path) {
        Document document;
        try {
            try {
                PdfFont fontTitle = PdfFontFactory.createFont("unicode.ttf", com.itextpdf.text.pdf.BaseFont.IDENTITY_H);
                Date date = new Date();
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(date);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String timeNow = hour + ":" + min + ":" + second + "\t" + day + "/" + month + "/" + year;

                PdfWriter pdfWriter = new PdfWriter(path + "\\" + hoaDon.getMaHoaDon() + ".pdf");
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                pdfDocument.addNewPage();

                ImageData imageData = ImageDataFactory.create(getClass().getResource("/pro1041/team_3/icon/logoCircleWhite.png"));
                Image imageLogo = new Image(imageData);
                imageLogo.setHeight(50f).setWidth(65f);

                document = new Document(pdfDocument);
                float columnWith[] = {80, 1000};
                Table tableHeader = new Table(columnWith).setBorder(Border.NO_BORDER).setHeight(60f).setAutoLayout();

                tableHeader.setBackgroundColor(new DeviceRgb(1, 181, 204));
                tableHeader.addCell(new Cell().add(imageLogo).setBorder(Border.NO_BORDER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setMarginTop(5f));
                tableHeader.addCell(new Cell().add("Hóa đơn WAKIKI PHONE")
                        .setFontColor(new DeviceRgb(255, 255, 255)).setFontSize(16f)
                        .setBold()
                        .setMarginLeft(15f)
                        .setFont(fontTitle)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBorder(Border.NO_BORDER));

//                tableHeader.addCell(new Cell().add("Code Bill: \t" + data[0])
//                        .setFontColor(new DeviceRgb(255, 255, 255)).setFontSize(10f)
//                        .setTextAlignment(TextAlignment.RIGHT)
//                        .setMarginRight(15f)
//                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                        .setBorder(Border.NO_BORDER).add("Code costumer: \t" + data[1]));
                SimpleDateFormat sdf = new SimpleDateFormat("kk:mm dd/MM/yyyy");
                Paragraph infoCostumer = new Paragraph("Thông tin khách hàng");
                infoCostumer.setFont(fontTitle).setBold().setMarginTop(15f);
                Paragraph nameCos = null;
                Paragraph purchaseTime = null;
                Paragraph phoneNumber = null;
                Paragraph khachLe = null;
                if (hoaDon.getMaKhachHang() == null) {
                    khachLe = new Paragraph("Khách lẻ");
                    khachLe.setFont(fontTitle).setBold().setMarginTop(10f);
                } else {
                    nameCos = new Paragraph("Họ tên:\t" + hoaDon.getTenKhachHang());
                    nameCos.setFont(fontTitle).setFontSize(9f);

                    purchaseTime = new Paragraph("Thời gian:\t" + sdf.format(hoaDon.getNgayThanhToan()));
                    purchaseTime.setFont(fontTitle).setFontSize(9f);

                    phoneNumber = new Paragraph("Số điện thoại:\t" + hoaDon.getSdtKhachHang());
                    phoneNumber.setFont(fontTitle).setFontSize(9f);
                }

                Paragraph nhanVienThanhToan = new Paragraph("Nhân viên thanh toán:\t" + hoaDon.getMaNhanVien() + "-" + hoaDon.getTenNhanVien());
                nhanVienThanhToan.setFont(fontTitle).setBold().setMarginTop(12f);

                document.add(tableHeader);
                document.add(infoCostumer);
                if (nameCos != null) {
                    document.add(nameCos);
                    document.add(purchaseTime);
                    document.add(phoneNumber);
                } else {
                    document.add(khachLe);
                }
                document.add(nhanVienThanhToan);

                Paragraph listProducts = new Paragraph("Sản phẩm");
                listProducts.setFont(fontTitle).setBold().setMarginTop(25f).setMarginBottom(-10);

                document.add(listProducts);

                float columnWithTableContent[] = {100, 350, 400, 250, 200, 300, 150,
                    350, 250, 250};
                Table tableContent = new Table(columnWithTableContent)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBorder(Border.NO_BORDER).setMarginTop(15f);

                tableContent.addCell(new Cell()
                        .add("STT").setBackgroundColor(new DeviceRgb(1, 181, 204))
                        .setFont(fontTitle).setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Mã sản phẩm")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204))
                        .setFont(fontTitle).setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Tên sản phẩm")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204))
                        .setFont(fontTitle).setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("IMEI")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204))
                        .setFont(fontTitle).setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Hãng")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Màu sắc")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Bộ nhớ")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Tình trạng")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Đơn giá(VNĐ)")
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBorder(Border.NO_BORDER));
                tableContent.addCell(new Cell().add("Giá bán(VNĐ)")
                        .setFontSize(9)
                        .setBackgroundColor(new DeviceRgb(1, 181, 204)).setFont(fontTitle)
                        .setBold().setFontColor(Color.WHITE)
                        .setBorder(Border.NO_BORDER));

                DecimalFormat df = new DecimalFormat("#,###");

                int totalMoney = 0;
                int count = 1;
                for (int i = 0; i < lstHdct.size(); i++) {
                    tableContent.addCell(new Cell().add(String.valueOf(count)).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(lstHdct.get(i).getMa()).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(lstHdct.get(i).getTen()).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(lstHdct.get(i).getImei()).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(lstHdct.get(i).getHang()).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(lstHdct.get(i).getMauSac()).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(lstHdct.get(i).getBoNho() + "GB").setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(lstHdct.get(i).getTinhTrang() == 100 ? "New" : lstHdct.get(i).getTinhTrang() + "%").setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(df.format(lstHdct.get(i).getDonGia())).setBorder(Border.NO_BORDER).setFontSize(9));
                    tableContent.addCell(new Cell().add(df.format(lstHdct.get(i).getGiaBan())).setBorder(Border.NO_BORDER).setFontSize(9));
                }

                document.add(tableContent);
//                PdfPTable table = new PdfPTable(3);
//                table.addCell(new PdfPCell().setBackgroundColor(BaseColor.DARK_GRAY))
                float coulumnWithFotter[] = {100, 300, 600, 350, 350};
                Table tableFotter = new Table(coulumnWithFotter)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBorder(Border.NO_BORDER);
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                tableFotter.addCell(new Cell().setBackgroundColor(new DeviceRgb(1, 181, 204)).setBorder(Border.NO_BORDER));
                tableFotter.addCell(new Cell().add("Tổng tiền:"
                        + (hoaDon.getNganHang() == null ? "" : "\nChuyển khoản:")
                        + (hoaDon.getMaGiaoDich() == null ? "" : "\nMã giao dịch:")
                        + (hoaDon.getTienMat() == null ? "" : "\nTiền mặt:")
                        + "\nTổng tiền khách đưa:"
                        + "\nTrả lại:")
                        .setFont(fontTitle)
                        .setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBold()
                        .setBackgroundColor(new DeviceRgb(1, 181, 204))
                        .setBorder(Border.NO_BORDER)
                );
                BigDecimal tongTienKhachDua = new BigDecimal(BigInteger.ZERO);
                if (hoaDon.getNganHang() == null) {
                    tongTienKhachDua = hoaDon.getTienMat();
                } else if (hoaDon.getTienMat() == null) {
                    tongTienKhachDua = hoaDon.getNganHang();
                } else {
                    tongTienKhachDua = hoaDon.getNganHang().add(hoaDon.getTienMat());
                }
                tableFotter.addCell(new Cell().add("" + df.format(hoaDon.getTongTien()) + "VNĐ"
                        + (hoaDon.getNganHang() == null ? "" : "\n" + df.format(hoaDon.getNganHang()) + "VNĐ")
                        + (hoaDon.getMaGiaoDich() == null ? "" : "\n" + hoaDon.getMaGiaoDich())
                        + (hoaDon.getTienMat() == null ? "" : "\n" + df.format(hoaDon.getTienMat()) + "VNĐ")
                        + "\n" + df.format(tongTienKhachDua) + "VNĐ"
                        + "\n" + df.format(tongTienKhachDua.subtract(hoaDon.getTongTien())) + "VNĐ"
                )
                        .setFont(fontTitle)
                        .setFontColor(Color.WHITE)
                        .setFontSize(9)
                        .setBold()
                        .setBackgroundColor(new DeviceRgb(1, 181, 204))
                        .setBorder(Border.NO_BORDER)
                );

                document.add(tableFotter);

                float columnWithBarcode[] = {1000f};
                Table tableBarcode = new Table(columnWithBarcode)
                        .setMarginTop(20f)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER);

                tableBarcode.addCell(new Cell().add(createBarCode(pdfDocument, hoaDon.getMaHoaDon(), Barcode39.class))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorder(Border.NO_BORDER))
                        .setMarginLeft(180f);

                document.add(tableBarcode);

                document.close();
                System.out.println("Create a PDF file sussecess");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                e.getMessage();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return null;
        }
        return document;
    }

    private <BarcodeEAN extends Barcode1D> Image createBarCode(PdfDocument pdfDocument, String code, Class<BarcodeEAN> barcodeClass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        BarcodeEAN barCodeObject = barcodeClass.getConstructor(PdfDocument.class).newInstance(pdfDocument);
        barCodeObject.setCode(code);
        return new Image(barCodeObject.createFormXObject(pdfDocument));
    }
}
