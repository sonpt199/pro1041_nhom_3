package pro1041.team_3.form;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.GioHang;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.ChiTietDienThoaiDto;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.dto.GioHangChiTietDto;
import pro1041.team_3.dto.GioHangDto;
import pro1041.team_3.dto.GioHangRequest;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.dto.HoaDonRequest;
import pro1041.team_3.dto.KhachHangDto;
import pro1041.team_3.service.BanHangService;
import pro1041.team_3.service.ChiTietDienThoaiService;
import pro1041.team_3.service.GioHangChiTietService;
import pro1041.team_3.service.GioHangService;
import pro1041.team_3.service.KhachHangService;
import pro1041.team_3.service.impl.BanHangServiceImpl;
import pro1041.team_3.service.impl.ChiTietDienThoaiImpl;
import pro1041.team_3.service.impl.GioHangChiTietServiceImpl;
import pro1041.team_3.service.impl.GioHangServiceImpl;
import pro1041.team_3.service.impl.KhachHangServiceImpl;
import pro1041.team_3.swing.MessageAlert;
import pro1041.team_3.swing.Notification;
import pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author sonptph19600
 */
public class ViewBanHang extends javax.swing.JPanel {

    private KhachHangService khachHangService;
    private BanHangService banHangService;
    private ChiTietDienThoaiService chiTietDienThoaiService;
    private GioHangChiTietService gioHangChiTietService;
    private GioHangService gioHangService;
    private Map<UUID, BhChiTietDienThoaiDto> mapGioHang;
    private BhChiTietDienThoaiDto ctdtFind;
    private GioHang gioHangHienTai;
    private KhachHang khachHang;
    private NhanVien nhanVienHienTai;
    private DecimalFormat moneyFormat;
    private List<GioHangDto> lstGioHangTreo;
    private List<BhChiTietDienThoaiDto> lstGioHangChiTietTreo;
    //Scan QR
    private WebcamPanel webcamPanel;
    private Webcam webcam;
    private Thread capture;

    public ViewBanHang(NhanVien user) {
        initComponents();
        cbbHtThanhToan.setSelectedIndex(0);
        nhanVienHienTai = user; //Set nhân viên đăng nhập hiện tại
        System.out.println(nhanVienHienTai.toString());
        //Khởi tạo service
        khachHangService = new KhachHangServiceImpl();
        chiTietDienThoaiService = new ChiTietDienThoaiImpl();
        banHangService = new BanHangServiceImpl();
        gioHangChiTietService = new GioHangChiTietServiceImpl();
        gioHangService = new GioHangServiceImpl();
        mapGioHang = new HashMap<>();
        //Biến toàn cục
        moneyFormat = new DecimalFormat("#,###");
        gioHangHienTai = null;
        khachHang = null;
        //Setting Jpanel, dialog
        ImageIcon iconDialog = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircle.png"));
        ImageIcon iconDialogThemKh = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/addUserBlack.png"));
        dlDetailKhachHang.setIconImage(iconDialog.getImage());
        dlDetailKhachHang.setTitle("Chi tiết khách hàng");
        dlGioHangTreo.setIconImage(iconDialog.getImage());
        dlGioHangTreo.setTitle("Danh sách giỏ hàng treo");
        dlLyDo.setIconImage(iconDialog.getImage());
        dlLyDo.setTitle("Lý do treo giỏ hàng");
        dlThemKhachHang.setTitle("Thêm khách hàng");
        dlThemKhachHang.setIconImage(iconDialogThemKh.getImage());
        jpnGiamGia.setVisible(false);
        dlScanQr.setTitle("Scan QR");
        dlScanQr.setIconImage(iconDialog.getImage());

//        Setting Scrollbar cho Table
        tbGioHang.fixTable(jspTbGioHang);
        tbGioHangTreo.fixTable(jspTbGioHangtreo);
        tbGioHangChiTietTreo.fixTable(jspTbGioHangChiTietTreo);
        initComboBoxSearch();
    }

    private void initWebcam() {
//        Dimension size = WebcamResolution.VGA.getSize();
//        webcam = Webcam.getWebcams().get(0);
//        webcam.setViewSize(size);
        Dimension d = new Dimension(1920, 1080);
        webcam = Webcam.getWebcams().get(0);
        webcam.setCustomViewSizes(new Dimension[]{d});
        webcam.setViewSize(d);

        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setPreferredSize(d);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setVisible(true);
        webcamPanel.setDisplayDebugInfo(true);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);

        jpnWebcam.add(webcamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        jpnWebcam.getParent().revalidate();
    }

    public void captureThread() {
        capture = new Thread() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    Result result = null;
                    BufferedImage image = null;

                    if (webcam.isOpen()) {
                        if ((image = webcam.getImage()) == null) {
                            continue;
                        }
                    }
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    try {
                        result = new MultiFormatReader().decode(bitmap);
                    } catch (NotFoundException ex) {
//                        ex.printStackTrace();
//                        continue;
                    }
                    if (result != null) {
                        String resultText = result.getText();
                        String[] arrResult = resultText.split("\\n");
                        System.out.println(arrResult[1]);
                        txtTimKiemSanPham.setText(arrResult[1].substring(6));
                        dlScanQr.setVisible(false);
                        searchSanPham();
                        webcam.close();
                        capture.stop();
                    }

                } while (true);
            }
        };
        capture.setDaemon(true);
        capture.start();
    }

    private void initComboBoxSearch() {
        txtTimKiemKhachHang.clearItemSuggestion();
        List<KhachHangDto> lst = khachHangService.getAll();
        if (!lst.isEmpty()) {
            for (KhachHangDto x : lst) {
                txtTimKiemKhachHang.addItemSuggestion(x.getSdt());
            }
        }
        txtTimKiemSanPham.clearItemSuggestion();
        List<ChiTietDienThoaiResponse> lstDienThoai = chiTietDienThoaiService.getAllResponse();
        if (!lstDienThoai.isEmpty()) {
            for (ChiTietDienThoaiResponse x : lstDienThoai) {
                txtTimKiemSanPham.addItemSuggestion(x.getImei());
            }
        }
    }

    private void clearFormInput() {
        txtMaKh.setText("");
        txtTenKhachHang.setText("");
        txtTongTien.setText("");
        txtTienKhachDua.setText("");
        txtTienKhachDuaKetHop.setText("");
        txtNganHangKetHop.setText("");
        txtMaGiaoDich.setText("");
        txtMaGiaoDichKetHop.setText("");
        txtSdtKhachHang.setText("");
        txtTimKiemKhachHang.setText("");
        txtTimKiemSanPham.setText("");
        txtNganHang.setText("");
        txtTienThua.setText("");
        txtTienThuaKetHop.setText("");
        txtTienThuaNganHang.setText("");
    }

    private void loadTableGioHang() {
        DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
        model.setRowCount(0);
        int count = 1;
        for (BhChiTietDienThoaiDto x : mapGioHang.values()) {
            model.addRow(new Object[]{
                1, x.getMa(), x.getTen(), x.getImei(), x.getHang(),
                x.getMauSac(), x.getBoNho() + " GB",
                x.getTinhTrang() == 0 ? "Mới" : "cũ",
                moneyFormat.format(x.getDonGia()) + "VNĐ", moneyFormat.format(x.getGiaBan()) + "VNĐ"
            });
            count += 1;
        }
        tinhTongTien();
    }

    private void tinhTongTien() {
        int size = tbGioHang.getRowCount();
        BigDecimal tongTien = new BigDecimal(0);
        for (int i = 0; i < size; i++) {
            BigDecimal thanhTien = new BigDecimal((tbGioHang.getValueAt(i, 9) + "").replace(".", "").replace("VNĐ", ""));
            tongTien = tongTien.add(thanhTien);
        }
        if (tongTien.compareTo(BigDecimal.ZERO) == 0) {
            txtTongTien.setText("");
            return;
        }
        txtTongTien.setText(moneyFormat.format(tongTien).toString() + "VNĐ");
    }

    private void searchKhachHang(String keyWord) {
//        String keyWord = txtTimKiemKh.getText().trim();
        if (keyWord.isEmpty()) {
            return;
        }
        if (!keyWord.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng");
            return;
        }
        KhachHang khachHangFind = khachHangService.findBySdt(keyWord);
        if (khachHangFind == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtDetailMaKh.setText(khachHangFind.getMa());
        txtDetailTenKh.setText(khachHangFind.getHoTen());
        txtDetailSdt.setText(khachHangFind.getSdt());
        Date ngaySinh = khachHangFind.getNgaySinh();
        txtDetailNgaySinh.setText(sdf.format(ngaySinh));
        txtDetailDiaChi.setText(khachHangFind.getDiaChi());
        if (khachHangFind.getGioiTinh() == 0) {
            rdDetailNam.setSelected(true);
        } else {
            rdDetailNu.setSelected(true);
        }
        khachHang = khachHangFind;
    }

    private void searchSanPham() {
        String keyWord = (String) txtTimKiemSanPham.getText();
        if (keyWord.isEmpty() || keyWord == null) {
            return;
        }
        if (!keyWord.matches("\\d+")) {
            //****************alert mới
//            Notification panel = new Notification((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Vui lòng nhập IMEI của điện thoại");
//            panel.showNotification();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập IMEI của điện thoại");
            return;
        }
        BhChiTietDienThoaiDto result = banHangService.bhFindByImei(keyWord);
        if (result == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
            return;
        }
        DienThoaiKhuyenMai dtkm = banHangService.getGiamGia(result.getId());
        if (dtkm != null) {
            jpnGiamGia.setVisible(true);
            result.setGiaBan(dtkm.getGiaConLai());
            txtGiaBan.setText(moneyFormat.format(dtkm.getGiaConLai()).toString());
        } else {
            result.setGiaBan(result.getDonGia());
            jpnGiamGia.setVisible(false);
        }
        txtMaDienThoai.setText(result.getMa());
        txtTenDienThoai.setText(result.getTen());
        txtIMEI.setText(result.getImei());
        txtHang.setText(result.getHang());
        txtMauSac.setText(result.getMauSac());
        txtTinhTrang.setText(result.getTinhTrang() == 100 ? "Mới" : "Cũ - " + result.getTinhTrang() + "%");
        txtBoNho.setText(result.getBoNho() + "GB");
        txtDonGia.setText(moneyFormat.format(result.getDonGia()).toString());
        txtMoTa.setText(result.getMoTa() == null ? "" : result.getMoTa());
        ctdtFind = result;
    }

    private void tinhTienThuaThanhToanKetHop() {
        if (txtTongTien.getText().length() == 0) {
            return;
        }
        String tienKhachDuaStr = txtTienKhachDuaKetHop.getText().trim();
        if (tienKhachDuaStr.equals("")) {
            txtTienThuaKetHop.setText("");
            return;
        }
        String nganHangStr = txtNganHangKetHop.getText().trim();
        if (nganHangStr.equals("")) {
            txtTienThuaKetHop.setText("");
            return;
        }
        BigDecimal tienKhachDua = null;
        BigDecimal nganHang = null;
        try {
            tienKhachDua = new BigDecimal(tienKhachDuaStr);
            if (tienKhachDua.compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa phải lớn hơn 0");
                return;
            }
            nganHang = new BigDecimal(nganHangStr);
            if (nganHang.compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Tiền ngân hàng phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền là số");
            e.printStackTrace();
            return;
        }
        String tongTienStr = txtTongTien.getText().replace(".", "").replace("VNĐ", "");
        if (tongTienStr.equals("") || tongTienStr.equals("0")) {
            return;
        }
        BigDecimal tongTien = new BigDecimal(tongTienStr);
        BigDecimal tienThua = tienKhachDua.add(nganHang).subtract(tongTien);
        if (tienThua.compareTo(BigDecimal.ZERO) == 1) {
            txtTienThuaKetHop.setText(moneyFormat.format(tienThua) + "VNĐ");
        } else {
            txtTienThuaKetHop.setText("");
        }
    }

    private boolean loadTableGioHangTreo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy kk:mm");
        lstGioHangTreo = gioHangService.getAllResponse();
        if (lstGioHangTreo.isEmpty()) {
            return false;
        }
        DefaultTableModel model = (DefaultTableModel) tbGioHangTreo.getModel();
        model.setRowCount(0);
        int count = 1;
        for (GioHangDto x : lstGioHangTreo) {
            if (gioHangHienTai != null) {
                if (x.getIdGioHang().compareTo(gioHangHienTai.getId()) == 0) {
                    continue;
                }
            }
            model.addRow(new Object[]{
                count, x.getMaGioHang(),
                x.getIdKhachHang() == null ? "-" : x.getTenKhachHang(),
                x.getSdtKhachHang() == null ? "-" : x.getSdtKhachHang(),
                x.getMaNhanVien(),
                sdf.format(x.getNgayTao()),
                x.getLyDo() == null || x.getLyDo().equals("") ? "-" : x.getLyDo()
            });
            count += 1;
        }
        return true;
    }

    private void loadTableGioHangChiTietTreo() {

        DefaultTableModel model = (DefaultTableModel) tbGioHangChiTietTreo.getModel();
        model.setRowCount(0);
        int count = 1;
        for (BhChiTietDienThoaiDto x : lstGioHangChiTietTreo) {
            model.addRow(new Object[]{
                1, x.getMa(), x.getTen(), x.getImei(), x.getHang(),
                x.getMauSac(), x.getBoNho() + " GB",
                x.getTinhTrang() == 0 ? "Mới" : "cũ",});
            count += 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlDetailKhachHang = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        txtDetailMaKh = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtDetailTenKh = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtDetailSdt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtDetailNgaySinh = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtDetailEmail = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDetailDiaChi = new javax.swing.JTextArea();
        btnDongChiTietKhachHang = new pro1041.team_3.swing.ButtonCustom();
        btnThemKhVaoGh = new pro1041.team_3.swing.ButtonCustom();
        rdDetailNam = new pro1041.team_3.swing.RadioButtonCustom();
        rdDetailNu = new pro1041.team_3.swing.RadioButtonCustom();
        grGender = new javax.swing.ButtonGroup();
        dlLyDo = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLyDo = new javax.swing.JTextArea();
        btnLyDoTreo = new pro1041.team_3.swing.ButtonCustom();
        dlGioHangTreo = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jspTbGioHangChiTietTreo = new javax.swing.JScrollPane();
        tbGioHangChiTietTreo = new pro1041.team_3.swing.config.Table();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jspTbGioHangtreo = new javax.swing.JScrollPane();
        tbGioHangTreo = new pro1041.team_3.swing.config.Table();
        jLabel31 = new javax.swing.JLabel();
        btnXoaGioHangTreo = new pro1041.team_3.swing.ButtonCustom();
        btnGoiLaiGioHang = new pro1041.team_3.swing.ButtonCustom();
        btnCloseShowGioHangTreo = new pro1041.team_3.swing.ButtonCustom();
        jLabel32 = new javax.swing.JLabel();
        cbbTimKiemGioHangTreo = new pro1041.team_3.swing.ComboBoxSuggestion<>();
        dlThemKhachHang = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        txtThemSdtKh = new pro1041.team_3.swing.TextField();
        txtThemTenKh = new pro1041.team_3.swing.TextField();
        jPanel23 = new javax.swing.JPanel();
        rdNamThemKh = new pro1041.team_3.swing.RadioButtonCustom();
        rdNuThemKh = new pro1041.team_3.swing.RadioButtonCustom();
        txtThemNgaySinhKh = new pro1041.team_3.swing.TextField();
        txtThemEmailKh = new pro1041.team_3.swing.TextField();
        btnThemKhachHang = new pro1041.team_3.swing.ButtonCustom();
        textAreaScroll2 = new pro1041.team_3.swing.config.TextAreaScroll();
        txtThemDiaChiKh = new pro1041.team_3.swing.TextArea();
        themNgaySinhKh = new pro1041.team_3.swing.DateChooser();
        grThemGioiTinhKh = new javax.swing.ButtonGroup();
        dlEditDienThoai = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new pro1041.team_3.swing.config.Table();
        dlScanQr = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jpnWebcam = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jspTbGioHang = new javax.swing.JScrollPane();
        tbGioHang = new pro1041.team_3.swing.config.Table();
        jPanel2 = new javax.swing.JPanel();
        btnTimKiemKh = new javax.swing.JLabel();
        cbbHtThanhToan = new pro1041.team_3.swing.Combobox();
        txtTimKiemKhachHang = new pro1041.team_3.swing.TextFieldSuggestion();
        txtMaKh = new pro1041.team_3.swing.TextField();
        txtTenKhachHang = new pro1041.team_3.swing.TextField();
        txtSdtKhachHang = new pro1041.team_3.swing.TextField();
        txtTongTien = new pro1041.team_3.swing.TextField();
        HinhThucThanhToan = new javax.swing.JPanel();
        tienMatJPanel = new javax.swing.JPanel();
        buttonCustom3 = new pro1041.team_3.swing.ButtonCustom();
        txtTienKhachDua = new pro1041.team_3.swing.TextField();
        txtTienThua = new pro1041.team_3.swing.TextField();
        nganHangJPanel = new javax.swing.JPanel();
        txtMaGiaoDich = new pro1041.team_3.swing.TextField();
        txtNganHang = new pro1041.team_3.swing.TextField();
        txtTienThuaNganHang = new pro1041.team_3.swing.TextField();
        ketHopJPanel = new javax.swing.JPanel();
        txtTienKhachDuaKetHop = new pro1041.team_3.swing.TextField();
        txtNganHangKetHop = new pro1041.team_3.swing.TextField();
        txtMaGiaoDichKetHop = new pro1041.team_3.swing.TextField();
        txtTienThuaKetHop = new pro1041.team_3.swing.TextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JLabel();
        txtTimKiemSanPham = new pro1041.team_3.swing.TextFieldSuggestion();
        btnScanQr = new pro1041.team_3.swing.ButtonCustom();
        jpnGiamGia = new javax.swing.JPanel();
        txtGiaBan = new pro1041.team_3.swing.TextField();
        btnThemVaoGioHang = new pro1041.team_3.swing.ButtonCustom();
        txtMaDienThoai = new pro1041.team_3.swing.TextField();
        txtMauSac = new pro1041.team_3.swing.TextField();
        txtBoNho = new pro1041.team_3.swing.TextField();
        txtTenDienThoai = new pro1041.team_3.swing.TextField();
        txtTinhTrang = new pro1041.team_3.swing.TextField();
        txtIMEI = new pro1041.team_3.swing.TextField();
        txtDonGia = new pro1041.team_3.swing.TextField();
        txtHang = new pro1041.team_3.swing.TextField();
        textAreaScroll1 = new pro1041.team_3.swing.config.TextAreaScroll();
        txtMoTa = new pro1041.team_3.swing.TextArea();
        jPanel5 = new javax.swing.JPanel();
        btnThanhToan = new pro1041.team_3.swing.ButtonCustom();
        btnTreoGioHang = new pro1041.team_3.swing.ButtonCustom();
        btnShowGioHangTreo = new pro1041.team_3.swing.ButtonCustom();
        btnXoaTrongGioHang = new pro1041.team_3.swing.ButtonCustom();
        btnShowDlThemKhachHang = new pro1041.team_3.swing.ButtonCustom();
        btnHuyGioHangHienTai = new pro1041.team_3.swing.ButtonCustom();

        dlDetailKhachHang.setSize(new java.awt.Dimension(375, 430));
        dlDetailKhachHang.setType(java.awt.Window.Type.POPUP);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray), "Thông tin khách hàng"));
        jPanel6.setFocusTraversalPolicyProvider(true);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDetailMaKh.setEditable(false);
        txtDetailMaKh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel6.add(txtDetailMaKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 260, -1));

        jLabel23.setText("Mã KH");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel24.setText("Tên KH");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txtDetailTenKh.setEditable(false);
        txtDetailTenKh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel6.add(txtDetailTenKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 260, -1));

        jLabel25.setText("SĐT");
        jPanel6.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtDetailSdt.setEditable(false);
        txtDetailSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel6.add(txtDetailSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 260, -1));

        jLabel26.setText("Ngày sinh");
        jPanel6.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txtDetailNgaySinh.setEditable(false);
        txtDetailNgaySinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel6.add(txtDetailNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 260, -1));

        jLabel27.setText("Giới tính");
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel28.setText("Email");
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        txtDetailEmail.setEditable(false);
        txtDetailEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel6.add(txtDetailEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 260, -1));

        jLabel29.setText("Địa chỉ");
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        txtDetailDiaChi.setEditable(false);
        txtDetailDiaChi.setColumns(20);
        txtDetailDiaChi.setRows(5);
        txtDetailDiaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        txtDetailDiaChi.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(txtDetailDiaChi);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 260, -1));

        btnDongChiTietKhachHang.setBackground(new java.awt.Color(153, 153, 153));
        btnDongChiTietKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnDongChiTietKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        btnDongChiTietKhachHang.setText("Quay lại");
        btnDongChiTietKhachHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnDongChiTietKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongChiTietKhachHangActionPerformed(evt);
            }
        });
        jPanel6.add(btnDongChiTietKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, 45));

        btnThemKhVaoGh.setBackground(new java.awt.Color(1, 181, 204));
        btnThemKhVaoGh.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKhVaoGh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/addUser.png"))); // NOI18N
        btnThemKhVaoGh.setText("Thêm");
        btnThemKhVaoGh.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThemKhVaoGh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhVaoGhActionPerformed(evt);
            }
        });
        jPanel6.add(btnThemKhVaoGh, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 110, 45));

        grGender.add(rdDetailNam);
        rdDetailNam.setText("Nam");
        rdDetailNam.setEnabled(false);
        jPanel6.add(rdDetailNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        grGender.add(rdDetailNu);
        rdDetailNu.setText("Nữ");
        rdDetailNu.setEnabled(false);
        jPanel6.add(rdDetailNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        javax.swing.GroupLayout dlDetailKhachHangLayout = new javax.swing.GroupLayout(dlDetailKhachHang.getContentPane());
        dlDetailKhachHang.getContentPane().setLayout(dlDetailKhachHangLayout);
        dlDetailKhachHangLayout.setHorizontalGroup(
            dlDetailKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlDetailKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );
        dlDetailKhachHangLayout.setVerticalGroup(
            dlDetailKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlDetailKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        dlLyDo.setBackground(new java.awt.Color(255, 255, 255));
        dlLyDo.setResizable(false);
        dlLyDo.setSize(new java.awt.Dimension(410, 260));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLyDo.setColumns(20);
        txtLyDo.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        txtLyDo.setRows(5);
        txtLyDo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray), "Lý do"));
        jScrollPane3.setViewportView(txtLyDo);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 376, 160));

        btnLyDoTreo.setBackground(new java.awt.Color(1, 181, 204));
        btnLyDoTreo.setForeground(new java.awt.Color(255, 255, 255));
        btnLyDoTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/change.png"))); // NOI18N
        btnLyDoTreo.setText("Treo GH");
        btnLyDoTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnLyDoTreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLyDoTreoActionPerformed(evt);
            }
        });
        jPanel3.add(btnLyDoTreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, 40));

        javax.swing.GroupLayout dlLyDoLayout = new javax.swing.GroupLayout(dlLyDo.getContentPane());
        dlLyDo.getContentPane().setLayout(dlLyDoLayout);
        dlLyDoLayout.setHorizontalGroup(
            dlLyDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
        dlLyDoLayout.setVerticalGroup(
            dlLyDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        dlGioHangTreo.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        dlGioHangTreo.setSize(new java.awt.Dimension(820, 670));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(476, 600));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbGioHangChiTietTreo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "IMEI", "Hãng", "Màu sắc", "Bộ nhớ", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGioHangChiTietTreo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbGioHangChiTietTreo.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        tbGioHangChiTietTreo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbGioHangChiTietTreo.getTableHeader().setReorderingAllowed(false);
        jspTbGioHangChiTietTreo.setViewportView(tbGioHangChiTietTreo);
        if (tbGioHangChiTietTreo.getColumnModel().getColumnCount() > 0) {
            tbGioHangChiTietTreo.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbGioHangChiTietTreo.getColumnModel().getColumn(1).setPreferredWidth(15);
            tbGioHangChiTietTreo.getColumnModel().getColumn(2).setPreferredWidth(80);
            tbGioHangChiTietTreo.getColumnModel().getColumn(5).setPreferredWidth(20);
            tbGioHangChiTietTreo.getColumnModel().getColumn(6).setPreferredWidth(20);
            tbGioHangChiTietTreo.getColumnModel().getColumn(7).setPreferredWidth(10);
        }

        jPanel8.add(jspTbGioHangChiTietTreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 760, 230));

        jLabel9.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        jLabel9.setText("Chi tiết giỏ hàng");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 780, 260));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbGioHangTreo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã GH", "Tên KH", "SĐT KH", "Mã NV", "Ngày tạo", "Lý do"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGioHangTreo.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        tbGioHangTreo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbGioHangTreo.getTableHeader().setReorderingAllowed(false);
        tbGioHangTreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGioHangTreoMouseClicked(evt);
            }
        });
        jspTbGioHangtreo.setViewportView(tbGioHangTreo);
        if (tbGioHangTreo.getColumnModel().getColumnCount() > 0) {
            tbGioHangTreo.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbGioHangTreo.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel9.add(jspTbGioHangtreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 760, 270));

        jLabel31.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        jLabel31.setText("Danh sách giỏ hàng treo");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, -1));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 300));

        btnXoaGioHangTreo.setBackground(new java.awt.Color(255, 0, 51));
        btnXoaGioHangTreo.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaGioHangTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/delete.png"))); // NOI18N
        btnXoaGioHangTreo.setText("Xóa GH");
        btnXoaGioHangTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnXoaGioHangTreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioHangTreoActionPerformed(evt);
            }
        });
        jPanel7.add(btnXoaGioHangTreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 110, 45));

        btnGoiLaiGioHang.setBackground(new java.awt.Color(1, 181, 204));
        btnGoiLaiGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnGoiLaiGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/change.png"))); // NOI18N
        btnGoiLaiGioHang.setText("Gọi lại GH");
        btnGoiLaiGioHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnGoiLaiGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiLaiGioHangActionPerformed(evt);
            }
        });
        jPanel7.add(btnGoiLaiGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 120, 45));

        btnCloseShowGioHangTreo.setBackground(new java.awt.Color(153, 153, 153));
        btnCloseShowGioHangTreo.setForeground(new java.awt.Color(255, 255, 255));
        btnCloseShowGioHangTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        btnCloseShowGioHangTreo.setText("Quay lại");
        btnCloseShowGioHangTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnCloseShowGioHangTreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseShowGioHangTreoActionPerformed(evt);
            }
        });
        jPanel7.add(btnCloseShowGioHangTreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 110, 45));

        jLabel32.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        jLabel32.setText("Tìm kiếm");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        cbbTimKiemGioHangTreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemGioHangTreoActionPerformed(evt);
            }
        });
        jPanel7.add(cbbTimKiemGioHangTreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 28, 220, 30));

        javax.swing.GroupLayout dlGioHangTreoLayout = new javax.swing.GroupLayout(dlGioHangTreo.getContentPane());
        dlGioHangTreo.getContentPane().setLayout(dlGioHangTreoLayout);
        dlGioHangTreoLayout.setHorizontalGroup(
            dlGioHangTreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        dlGioHangTreoLayout.setVerticalGroup(
            dlGioHangTreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dlThemKhachHang.setSize(new java.awt.Dimension(300, 510));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtThemSdtKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemSdtKh.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThemSdtKh.setLabelText("Số điện thoại");
        jPanel22.add(txtThemSdtKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 240, -1));

        txtThemTenKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemTenKh.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThemTenKh.setLabelText("Họ và tên");
        jPanel22.add(txtThemTenKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray), "Giới tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 14), new java.awt.Color(1, 132, 203))); // NOI18N
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        grThemGioiTinhKh.add(rdNamThemKh);
        rdNamThemKh.setText("Nam");
        jPanel23.add(rdNamThemKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        grThemGioiTinhKh.add(rdNuThemKh);
        rdNuThemKh.setText("Nữ");
        jPanel23.add(rdNuThemKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 240, 60));

        txtThemNgaySinhKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemNgaySinhKh.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThemNgaySinhKh.setLabelText("Ngày sinh");
        jPanel22.add(txtThemNgaySinhKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, -1));

        txtThemEmailKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemEmailKh.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThemEmailKh.setLabelText("Email");
        jPanel22.add(txtThemEmailKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 240, -1));

        btnThemKhachHang.setBackground(new java.awt.Color(1, 181, 204));
        btnThemKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/addUser.png"))); // NOI18N
        btnThemKhachHang.setText("Thêm KH");
        btnThemKhachHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachHangActionPerformed(evt);
            }
        });
        jPanel22.add(btnThemKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, -1, -1));

        textAreaScroll2.setBackground(new java.awt.Color(255, 255, 255));
        textAreaScroll2.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        textAreaScroll2.setLabelColor(new java.awt.Color(1, 132, 203));
        textAreaScroll2.setLabelText("Mô tả");
        textAreaScroll2.setLostFocusColor(new java.awt.Color(3, 155, 216));

        txtThemDiaChiKh.setColumns(20);
        txtThemDiaChiKh.setRows(5);
        txtThemDiaChiKh.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        textAreaScroll2.setViewportView(txtThemDiaChiKh);

        jPanel22.add(textAreaScroll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 110));

        javax.swing.GroupLayout dlThemKhachHangLayout = new javax.swing.GroupLayout(dlThemKhachHang.getContentPane());
        dlThemKhachHang.getContentPane().setLayout(dlThemKhachHangLayout);
        dlThemKhachHangLayout.setHorizontalGroup(
            dlThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        dlThemKhachHangLayout.setVerticalGroup(
            dlThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        themNgaySinhKh.setTextRefernce(txtThemNgaySinhKh);

        dlEditDienThoai.setSize(new java.awt.Dimension(750, 590));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlEditDienThoaiLayout = new javax.swing.GroupLayout(dlEditDienThoai.getContentPane());
        dlEditDienThoai.getContentPane().setLayout(dlEditDienThoaiLayout);
        dlEditDienThoaiLayout.setHorizontalGroup(
            dlEditDienThoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlEditDienThoaiLayout.setVerticalGroup(
            dlEditDienThoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dlScanQr.setSize(new java.awt.Dimension(525, 440));
        dlScanQr.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dlScanQrWindowClosing(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jpnWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Nunito", 1, 24)); // NOI18N
        jLabel1.setText("Scan here");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jpnWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlScanQrLayout = new javax.swing.GroupLayout(dlScanQr.getContentPane());
        dlScanQr.getContentPane().setLayout(dlScanQrLayout);
        dlScanQrLayout.setHorizontalGroup(
            dlScanQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlScanQrLayout.setVerticalGroup(
            dlScanQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(250, 250, 250));
        setMinimumSize(new java.awt.Dimension(1160, 720));
        setPreferredSize(new java.awt.Dimension(1160, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "IMEI", "Hãng", "Màu sắc", "Bộ nhớ", "Tình trạng", "Đơn giá", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGioHang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbGioHang.setFont(new java.awt.Font("Nunito", 0, 14)); // NOI18N
        jspTbGioHang.setViewportView(tbGioHang);
        if (tbGioHang.getColumnModel().getColumnCount() > 0) {
            tbGioHang.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbGioHang.getColumnModel().getColumn(1).setPreferredWidth(15);
            tbGioHang.getColumnModel().getColumn(2).setPreferredWidth(80);
            tbGioHang.getColumnModel().getColumn(5).setPreferredWidth(20);
            tbGioHang.getColumnModel().getColumn(6).setPreferredWidth(20);
            tbGioHang.getColumnModel().getColumn(7).setPreferredWidth(10);
        }

        add(jspTbGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 1100, 310));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Hóa đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTimKiemKh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTimKiemKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/customSearch.png"))); // NOI18N
        btnTimKiemKh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTimKiemKh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemKhMouseClicked(evt);
            }
        });
        jPanel2.add(btnTimKiemKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 30, 30));

        cbbHtThanhToan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền mặt", "Ngân hàng", "Kết hợp" }));
        cbbHtThanhToan.setSelectedIndex(-1);
        cbbHtThanhToan.setFocusLostColor(new java.awt.Color(1, 132, 203));
        cbbHtThanhToan.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        cbbHtThanhToan.setLabeText("HT Thanh toán");
        cbbHtThanhToan.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbHtThanhToan.setLineColor(new java.awt.Color(1, 132, 203));
        cbbHtThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHtThanhToanActionPerformed(evt);
            }
        });
        jPanel2.add(cbbHtThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 190, 60));

        txtTimKiemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKhachHangActionPerformed(evt);
            }
        });
        jPanel2.add(txtTimKiemKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 200, 40));

        txtMaKh.setEditable(false);
        txtMaKh.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtMaKh.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtMaKh.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaKh.setLabelText("Mã KH");
        jPanel2.add(txtMaKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 190, -1));

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtTenKhachHang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTenKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenKhachHang.setLabelText("Tên khách hàng");
        jPanel2.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 260, -1));

        txtSdtKhachHang.setEditable(false);
        txtSdtKhachHang.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtSdtKhachHang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtSdtKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtSdtKhachHang.setLabelText("SĐT");
        jPanel2.add(txtSdtKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 190, -1));

        txtTongTien.setEditable(false);
        txtTongTien.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtTongTien.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTongTien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTongTien.setLabelText("Tổng tiền");
        jPanel2.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 190, -1));

        HinhThucThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        HinhThucThanhToan.setMinimumSize(new java.awt.Dimension(100, 100));
        HinhThucThanhToan.setLayout(new java.awt.CardLayout());

        tienMatJPanel.setBackground(new java.awt.Color(255, 255, 255));
        tienMatJPanel.setPreferredSize(new java.awt.Dimension(300, 190));
        tienMatJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonCustom3.setBackground(new java.awt.Color(1, 181, 204));
        buttonCustom3.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom3.setText("buttonCustom1");
        buttonCustom3.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        tienMatJPanel.add(buttonCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 120, -1));

        txtTienKhachDua.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtTienKhachDua.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTienKhachDua.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienKhachDua.setLabelText("Tiền khách đưa");
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });
        tienMatJPanel.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 190, -1));

        txtTienThua.setEditable(false);
        txtTienThua.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtTienThua.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTienThua.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienThua.setLabelText("Tiền thừa");
        tienMatJPanel.add(txtTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 190, -1));

        HinhThucThanhToan.add(tienMatJPanel, "card2");

        nganHangJPanel.setBackground(new java.awt.Color(255, 255, 255));
        nganHangJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaGiaoDich.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtMaGiaoDich.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtMaGiaoDich.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaGiaoDich.setLabelText("Mã giao dịch");
        nganHangJPanel.add(txtMaGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 190, -1));

        txtNganHang.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtNganHang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtNganHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNganHang.setLabelText("Tiền chuyển khoản");
        txtNganHang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNganHangCaretUpdate(evt);
            }
        });
        nganHangJPanel.add(txtNganHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, -1));

        txtTienThuaNganHang.setEditable(false);
        txtTienThuaNganHang.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtTienThuaNganHang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTienThuaNganHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienThuaNganHang.setLabelText("Tiền thừa");
        nganHangJPanel.add(txtTienThuaNganHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, -1));

        HinhThucThanhToan.add(nganHangJPanel, "card3");

        ketHopJPanel.setBackground(new java.awt.Color(255, 255, 255));
        ketHopJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTienKhachDuaKetHop.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtTienKhachDuaKetHop.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTienKhachDuaKetHop.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienKhachDuaKetHop.setLabelText("Tiền mặt");
        txtTienKhachDuaKetHop.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaKetHopCaretUpdate(evt);
            }
        });
        ketHopJPanel.add(txtTienKhachDuaKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, -1));

        txtNganHangKetHop.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtNganHangKetHop.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtNganHangKetHop.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNganHangKetHop.setLabelText("Ngân hàng");
        txtNganHangKetHop.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNganHangKetHopCaretUpdate(evt);
            }
        });
        ketHopJPanel.add(txtNganHangKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, -1));

        txtMaGiaoDichKetHop.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtMaGiaoDichKetHop.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtMaGiaoDichKetHop.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaGiaoDichKetHop.setLabelText("Mã giao dịch");
        ketHopJPanel.add(txtMaGiaoDichKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 190, -1));

        txtTienThuaKetHop.setEditable(false);
        txtTienThuaKetHop.setFocusLostColor(new java.awt.Color(1, 132, 203));
        txtTienThuaKetHop.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTienThuaKetHop.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienThuaKetHop.setLabelText("Tiền thừa");
        ketHopJPanel.add(txtTienThuaKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 190, -1));

        HinhThucThanhToan.add(ketHopJPanel, "card4");

        jPanel2.add(HinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 400, 140));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 420, 420));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/customSearch.png"))); // NOI18N
        btnTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        jPanel4.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 30, 30));

        txtTimKiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSanPhamActionPerformed(evt);
            }
        });
        jPanel4.add(txtTimKiemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 220, 40));

        btnScanQr.setBackground(new java.awt.Color(1, 181, 204));
        btnScanQr.setForeground(new java.awt.Color(255, 255, 255));
        btnScanQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/scanQr.png"))); // NOI18N
        btnScanQr.setText("Scan QR");
        btnScanQr.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnScanQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanQrActionPerformed(evt);
            }
        });
        jPanel4.add(btnScanQr, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 410, 70));

        jpnGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        jpnGiamGia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtGiaBan.setEditable(false);
        txtGiaBan.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtGiaBan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtGiaBan.setLabelText("Giá bán");
        jpnGiamGia.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 220, -1));

        jPanel1.add(jpnGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 240, 50));

        btnThemVaoGioHang.setBackground(new java.awt.Color(1, 181, 204));
        btnThemVaoGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVaoGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/addCart.png"))); // NOI18N
        btnThemVaoGioHang.setText("Thêm vào GH");
        btnThemVaoGioHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThemVaoGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoGioHangActionPerformed(evt);
            }
        });
        jPanel1.add(btnThemVaoGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 150, -1));

        txtMaDienThoai.setEditable(false);
        txtMaDienThoai.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtMaDienThoai.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaDienThoai.setLabelText("Mã");
        jPanel1.add(txtMaDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 220, -1));

        txtMauSac.setEditable(false);
        txtMauSac.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtMauSac.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMauSac.setLabelText("Màu sắc");
        jPanel1.add(txtMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 220, -1));

        txtBoNho.setEditable(false);
        txtBoNho.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtBoNho.setLabelColor(new java.awt.Color(1, 132, 203));
        txtBoNho.setLabelText("Bộ nhớ");
        jPanel1.add(txtBoNho, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 220, -1));

        txtTenDienThoai.setEditable(false);
        txtTenDienThoai.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTenDienThoai.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenDienThoai.setLabelText("Tên");
        jPanel1.add(txtTenDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 220, -1));

        txtTinhTrang.setEditable(false);
        txtTinhTrang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtTinhTrang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTinhTrang.setLabelText("Tình trạng");
        jPanel1.add(txtTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 220, -1));

        txtIMEI.setEditable(false);
        txtIMEI.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtIMEI.setLabelColor(new java.awt.Color(1, 132, 203));
        txtIMEI.setLabelText("IMEI");
        jPanel1.add(txtIMEI, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 220, -1));

        txtDonGia.setEditable(false);
        txtDonGia.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtDonGia.setLabelColor(new java.awt.Color(1, 132, 203));
        txtDonGia.setLabelText("Đơn giá");
        jPanel1.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 220, -1));

        txtHang.setEditable(false);
        txtHang.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        txtHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtHang.setLabelText("Hãng");
        jPanel1.add(txtHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 220, -1));

        textAreaScroll1.setBackground(new java.awt.Color(255, 255, 255));
        textAreaScroll1.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        textAreaScroll1.setLabelColor(new java.awt.Color(1, 132, 203));
        textAreaScroll1.setLabelText("Mô tả");
        textAreaScroll1.setLostFocusColor(new java.awt.Color(3, 155, 216));

        txtMoTa.setEditable(false);
        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        txtMoTa.setFocusable(false);
        txtMoTa.setFont(new java.awt.Font("Nunito Light", 1, 14)); // NOI18N
        textAreaScroll1.setViewportView(txtMoTa);

        jPanel1.add(textAreaScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 240, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 500, 420));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThanhToan.setBackground(new java.awt.Color(255, 0, 0));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/pay.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel5.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 50));

        btnTreoGioHang.setBackground(new java.awt.Color(1, 181, 204));
        btnTreoGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnTreoGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/waiting.png"))); // NOI18N
        btnTreoGioHang.setText("Treo GH");
        btnTreoGioHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnTreoGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreoGioHangActionPerformed(evt);
            }
        });
        jPanel5.add(btnTreoGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 140, -1));

        btnShowGioHangTreo.setBackground(new java.awt.Color(1, 181, 204));
        btnShowGioHangTreo.setForeground(new java.awt.Color(255, 255, 255));
        btnShowGioHangTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/change.png"))); // NOI18N
        btnShowGioHangTreo.setText("Gọi GH treo");
        btnShowGioHangTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnShowGioHangTreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowGioHangTreoActionPerformed(evt);
            }
        });
        jPanel5.add(btnShowGioHangTreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, -1));

        btnXoaTrongGioHang.setBackground(new java.awt.Color(204, 0, 0));
        btnXoaTrongGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTrongGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/delete.png"))); // NOI18N
        btnXoaTrongGioHang.setText("Xóa DT");
        btnXoaTrongGioHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnXoaTrongGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrongGioHangActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoaTrongGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 140, -1));

        btnShowDlThemKhachHang.setBackground(new java.awt.Color(1, 181, 204));
        btnShowDlThemKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnShowDlThemKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/addUser.png"))); // NOI18N
        btnShowDlThemKhachHang.setText("Thêm KH");
        btnShowDlThemKhachHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnShowDlThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowDlThemKhachHangActionPerformed(evt);
            }
        });
        jPanel5.add(btnShowDlThemKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 140, -1));

        btnHuyGioHangHienTai.setBackground(new java.awt.Color(204, 0, 0));
        btnHuyGioHangHienTai.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyGioHangHienTai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/deleteCart.png"))); // NOI18N
        btnHuyGioHangHienTai.setText("Xóa tất cả");
        btnHuyGioHangHienTai.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnHuyGioHangHienTai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyGioHangHienTaiActionPerformed(evt);
            }
        });
        jPanel5.add(btnHuyGioHangHienTai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 140, -1));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 27, 160, 390));
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // BTN Tìm kiếm sản phẩm
        searchSanPham();
    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void tbGioHangTreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangTreoMouseClicked
        // TB giỏ hàng treo click
        int row = tbGioHangTreo.getSelectedRow();
        GioHangDto gioHangTreo = lstGioHangTreo.get(row);
        lstGioHangChiTietTreo = gioHangChiTietService.getAllByIdGioHang(gioHangTreo.getIdGioHang());
        loadTableGioHangChiTietTreo();
    }//GEN-LAST:event_tbGioHangTreoMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // BTN Thanh toán
        if (mapGioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời thêm điện thoại vào để thanh toán");
            return;
        }
        if (khachHang == null) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thông tin khách hàng vào hóa đơn", "Xác nhận", JOptionPane.OK_OPTION) == 0) {
                return;
            }
        }
        String tongTienStr = txtTongTien.getText().replace(".", "").replace("VNĐ", "");
        BigDecimal tongTien = new BigDecimal(tongTienStr);
        int hinhThucThanhToan = cbbHtThanhToan.getSelectedIndex();
        BigDecimal tienMat = null;
        BigDecimal nganHang = null;
        String maGiaoDich = null;
        if (hinhThucThanhToan == 0) {
            String tienMatStr = txtTienKhachDua.getText().trim();
            if (tienMatStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mời nhập tiền khách thanh toán");
                return;
            }
            tienMat = new BigDecimal(tienMatStr);
        } else if (hinhThucThanhToan == 1) {
            maGiaoDich = txtMaGiaoDich.getText().trim();
            String nganHangStr = txtNganHang.getText().trim();
            if (maGiaoDich.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã giao dịch không được để trống");
                return;
            }
            if (nganHangStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mời nhập số tiền khách thanh toán bằng ngân hàng");
                return;
            }
            nganHang = new BigDecimal(nganHangStr);
        } else {
            String tienMatKetHopStr = txtTienKhachDuaKetHop.getText().trim();
            String nganHangStr = txtNganHangKetHop.getText().trim();
            maGiaoDich = txtMaGiaoDichKetHop.getText().trim();
            if (maGiaoDich.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã giao dịch không được để trống");
                return;
            }
            if (tienMatKetHopStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mời nhập số tiền khách thanh toán bằng tiền mặt");
                return;
            }
            if (nganHangStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mời nhập số tiền khách thanh toán bằng ngân hàng");
                return;
            }
            tienMat = new BigDecimal(tienMatKetHopStr);
            nganHang = new BigDecimal(nganHangStr);
            if (maGiaoDich.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã giao dịch không được để trống");
                return;
            }
        }
        List<HoaDonRequest> lstHoaDonChiTiet = new ArrayList<>();
        for (BhChiTietDienThoaiDto x : mapGioHang.values()) {
            HoaDonRequest hdct = new HoaDonRequest();
            hdct.setIdChiTietDienThoai(x.getId());
            hdct.setKhachHang(khachHang);
            hdct.setNhanVien(nhanVienHienTai);
            hdct.setDonGia(x.getDonGia());
            hdct.setGiaBan(x.getGiaBan());
            hdct.setHinhThucThanhToan(hinhThucThanhToan);
            hdct.setTongTien(tongTien);
            hdct.setTienMat(tienMat);
            hdct.setNganHang(nganHang);
            hdct.setMaGiaoDich(maGiaoDich);
            lstHoaDonChiTiet.add(hdct);
        }
        String path = null;
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hóa đơn dạng PDF không?", "Xuất hóa đơn", JOptionPane.OK_OPTION) == 0) {
            JnaFileChooser jfc = new JnaFileChooser();
            jfc.setMode(JnaFileChooser.Mode.Directories);
            if (jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
                path = jfc.getSelectedFile().getAbsolutePath();
            }
        }
        String mess = banHangService.thanhToan(lstHoaDonChiTiet, path);
        if (mess.equals("Thanh toán thành công")) {
            gioHangHienTai = null;
            mapGioHang.clear();
            loadTableGioHang();
            khachHang = null;
            ctdtFind = null;
            clearFormInput();
            if (gioHangHienTai != null) {
                Boolean check = gioHangChiTietService.deteleByIdGioHang(gioHangHienTai.getId());
                String messDelete = gioHangService.delete(gioHangHienTai.getId());
                if (!check && !messDelete.equals("Xóa giỏ hàng treo thành công")) {
                    JOptionPane.showMessageDialog(this, "Lỗi hệ thống. \nKhông thể xóa giỏ hàng treo");
                    return;
                }
            }
            Notification panel = new Notification((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Thanh toán thành công");
            panel.showNotification();
            return;
        }
        JOptionPane.showMessageDialog(this, mess);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTreoGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTreoGioHangActionPerformed
        //BTN Treo giỏ hàng     
        if (mapGioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong giỏ hàng");
            return;
        }
        dlLyDo.setLocationRelativeTo(null);
        dlLyDo.setVisible(true);
    }//GEN-LAST:event_btnTreoGioHangActionPerformed

    private void btnShowGioHangTreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowGioHangTreoActionPerformed
        // BTN Show ds giỏ hàng treo
        dlGioHangTreo.setLocationRelativeTo(null);
        DefaultTableModel model = (DefaultTableModel) tbGioHangChiTietTreo.getModel();
        model.setRowCount(0);
        boolean check = loadTableGioHangTreo();
        if (check) {
            cbbTimKiemGioHangTreo.removeAllItems();
            cbbTimKiemGioHangTreo.addItem("");
            for (GioHangDto x : lstGioHangTreo) {
                cbbTimKiemGioHangTreo.addItem(x.getMaGioHang());
                if (x.getMaKhachHang() != null) {
                    cbbTimKiemGioHangTreo.addItem(x.getSdtKhachHang());
                    cbbTimKiemGioHangTreo.addItem(x.getTenKhachHang());
                }
                if (x.getMaNhanVien() != null) {
                    cbbTimKiemGioHangTreo.addItem(x.getMaNhanVien());
                }
            }

            dlGioHangTreo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Không có giỏ hàng nào đang treo");
            dlGioHangTreo.setVisible(false);
        }
    }//GEN-LAST:event_btnShowGioHangTreoActionPerformed

    private void btnXoaTrongGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrongGioHangActionPerformed
        // BTN xóa 1 SP trong giỏ hàng
        int row = tbGioHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một sản phẩm trong giỏ hàng để xóa");
            return;
        }
        String imeiSelected = tbGioHang.getValueAt(row, 3) + "";
        for (Map.Entry<UUID, BhChiTietDienThoaiDto> x : mapGioHang.entrySet()) {
            if (x.getValue().getImei().equals(imeiSelected)) {
                mapGioHang.remove(x.getKey());
            }
        }
        System.out.println(mapGioHang.size());
        loadTableGioHang();
    }//GEN-LAST:event_btnXoaTrongGioHangActionPerformed

    private void btnShowDlThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowDlThemKhachHangActionPerformed
        // BTN Show Dialog Thêm khách hàng
        dlThemKhachHang.setLocationRelativeTo(null);
        dlThemKhachHang.setVisible(true);
    }//GEN-LAST:event_btnShowDlThemKhachHangActionPerformed

    private void btnThemVaoGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoGioHangActionPerformed
        // BTN Thêm vào giỏ hàng
        if (ctdtFind == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập IMEI sản phẩm và tìm kiếm");
            //*****************alert mới
//            MessageAlert alert = new MessageAlert((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), false , "Vui lòng nhập IMEI sản phẩm và tìm kiếm");
//            alert.showAlert();
            return;
        }
        if (ctdtFind.getTrangThai() == 1) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã bán");
            return;
        }
        if (ctdtFind.getTrangThai() == 2) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đang lỗi");
            return;
        }
        if (mapGioHang.containsKey(ctdtFind.getId())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại trong giỏ hàng");
            return;
        }
        List<GioHangDto> lstGioHangFind = banHangService.getGioHangByIdSp(ctdtFind.getId());
        if (!lstGioHangFind.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy kk:mm");
            String mess = "Sản phẩm này đang nằm trong giỏ hàng treo:\n";
            for (GioHangDto x : lstGioHangFind) {
                mess += "Mã giỏ hàng: " + x.getMaGioHang()
                        + "\nTên KH: " + x.getTenKhachHang()
                        + "\nNhân viên treo: " + x.getMaNhanVien()
                        + "\nThời gian treo: " + sdf.format(x.getNgayTao())
                        + "\n-------------------\n";
            }
            if (JOptionPane.showConfirmDialog(this, mess, "Thêm sản phẩm vào giỏ hàng", JOptionPane.OK_OPTION) != 0) {
                return;
            }
        }
        txtMaDienThoai.setText("");
        txtTenDienThoai.setText("");
        txtIMEI.setText("");
        txtGiaBan.setText("");
        txtMauSac.setText("");
        txtHang.setText("");
        txtTinhTrang.setText("");
        txtBoNho.setText("");
        txtDonGia.setText("");
        txtMoTa.setText("");
        txtTimKiemSanPham.setText("");
        mapGioHang.put(ctdtFind.getId(), ctdtFind);
        loadTableGioHang();
    }//GEN-LAST:event_btnThemVaoGioHangActionPerformed

    private void btnCloseShowGioHangTreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseShowGioHangTreoActionPerformed
        // BTN Close Ds Giỏ hàng treo
        dlGioHangTreo.setVisible(false);
    }//GEN-LAST:event_btnCloseShowGioHangTreoActionPerformed

    private void btnXoaGioHangTreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioHangTreoActionPerformed
        // BTN xóa giỏ hàng treo
        int row = tbGioHangTreo.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một giỏ hàng để xóa");
            return;
        }
        GioHangDto gioHangTreo = lstGioHangTreo.get(row);
        if (!gioHangChiTietService.deteleByIdGioHang(gioHangTreo.getIdGioHang())) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
            return;
        }
        String messXoaGioHangTreo = gioHangService.delete(gioHangTreo.getIdGioHang());
        JOptionPane.showMessageDialog(this, messXoaGioHangTreo);
        if (messXoaGioHangTreo.equals("Xóa giỏ hàng treo thành công")) {
            loadTableGioHangTreo();
            DefaultTableModel model = (DefaultTableModel) tbGioHangChiTietTreo.getModel();
            model.setRowCount(0);
        }
    }//GEN-LAST:event_btnXoaGioHangTreoActionPerformed

    private void btnGoiLaiGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiLaiGioHangActionPerformed
        // BTN Gọi lại giỏ hàng treo
        if (!mapGioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Đang có sản phẩm trong giỏ hàng");
            return;
        }
        int row = tbGioHangTreo.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một giỏ hàng");
            return;
        }
        GioHangDto gioHang = lstGioHangTreo.get(row);
        mapGioHang.clear();
        List<BhChiTietDienThoaiDto> lstDienThoai = gioHangChiTietService.getAllByIdGioHang(gioHang.getIdGioHang());
        for (BhChiTietDienThoaiDto x : lstDienThoai) {
            if (x.getTrangThai() == 1) {
                HoaDonDto hoaDonFind = banHangService.getHoaDonByIdSp(x.getId());
                JOptionPane.showMessageDialog(this, "Điện thoại: " + x.getTen()
                        + "\nIMEI: " + x.getImei()
                        + "\nHãng: " + x.getHang()
                        + "\nMàu sắc: " + x.getMauSac()
                        + "\nĐã được bán trong hóa đơn: "
                        + "\nMã hóa đơn: " + hoaDonFind.getMaHoaDon()
                        + "\nNhân viên thanh toán: " + hoaDonFind.getMaNhanVien() + "-" + hoaDonFind.getTenNhanVien());

                continue;
            }
            mapGioHang.put(x.getId(), x);
        }
        gioHangHienTai = new GioHang();
        gioHangHienTai.setId(gioHang.getIdGioHang());
        dlGioHangTreo.setVisible(false);
        if (mapGioHang.isEmpty()) {
            Boolean check = gioHangChiTietService.deteleByIdGioHang(gioHangHienTai.getId());
            String messDelete = gioHangService.delete(gioHangHienTai.getId());
            if (!check && !messDelete.equals("Xóa giỏ hàng treo thành công")) {
                JOptionPane.showMessageDialog(this, "Lỗi hệ thống. \nKhông thể xóa giỏ hàng treo");
                return;
            }
            gioHangHienTai = null;
            return;
        }
        if (gioHang.getIdKhachHang() != null) {
            khachHang = new KhachHang();
            khachHang.setId(gioHang.getIdKhachHang());
            txtMaKh.setText(gioHang.getMaKhachHang());
            txtTenKhachHang.setText(gioHang.getTenKhachHang());
            txtSdtKhachHang.setText(gioHang.getSdtKhachHang());
        }
        loadTableGioHang();
    }//GEN-LAST:event_btnGoiLaiGioHangActionPerformed

    private void btnLyDoTreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLyDoTreoActionPerformed
        // BTN treo - nhập lý do xong
        dlLyDo.setVisible(false);
        List<GioHangRequest> lstSp = new ArrayList<>();
        for (BhChiTietDienThoaiDto x : mapGioHang.values()) {
            GioHangRequest ghct = new GioHangRequest();
            ghct.setIdChiTietDienThoai(x.getId());
            ghct.setKhachHang(khachHang);
            ghct.setNhanVien(nhanVienHienTai);
            ghct.setLyDo(txtLyDo.getText().trim());
            lstSp.add(ghct);
        }
        String mess = banHangService.treoHoaDon(lstSp);
        if (mess.equals("Treo giỏ hàng thành công")) {
            mapGioHang.clear();
            loadTableGioHang();
            khachHang = null;
            clearFormInput();
        }
        JOptionPane.showMessageDialog(this, mess);
    }//GEN-LAST:event_btnLyDoTreoActionPerformed

    private void btnDongChiTietKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongChiTietKhachHangActionPerformed
        // BTN close Detail
        dlDetailKhachHang.setVisible(false);
        khachHang = null;
    }//GEN-LAST:event_btnDongChiTietKhachHangActionPerformed

    private void btnThemKhVaoGhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhVaoGhActionPerformed
        // BTN thêm khách hàng
        txtMaKh.setText(khachHang.getMa());
        txtTenKhachHang.setText(khachHang.getHoTen());
        txtSdtKhachHang.setText(khachHang.getSdt());
        txtTimKiemKhachHang.setText("");
        dlDetailKhachHang.setVisible(false);
    }//GEN-LAST:event_btnThemKhVaoGhActionPerformed

    private void btnThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachHangActionPerformed
        KhachHang khachHangMoi = new KhachHang();
        try {
            // BTN Thêm khách hàng
            SimpleDateFormat sdfNgaySinh = new SimpleDateFormat("dd-MM-yyyy");
            String ngaySinhStr = txtThemNgaySinhKh.getText();
            khachHangMoi.setHoTen(txtThemTenKh.getText().trim());
            Date ngaySinh = sdfNgaySinh.parse(ngaySinhStr);
            khachHangMoi.setNgaySinh(ngaySinh);
            if (rdNamThemKh.isSelected()) {
                khachHangMoi.setGioiTinh(0);
            } else if (rdNuThemKh.isSelected()) {
                khachHangMoi.setGioiTinh(1);
            } else {
                JOptionPane.showMessageDialog(this, "Mời chọn giới tính");
                return;
            }
            khachHangMoi.setSdt(txtThemSdtKh.getText().trim());
            khachHangMoi.setDiaChi(txtThemDiaChiKh.getText().trim());
            khachHangMoi.setEmail(txtThemEmailKh.getText().trim());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi ngày sinh");
            return;
        }
        String mess = khachHangService.insert(khachHangMoi);
        if (mess.equals("Thêm thành công")) {
            dlThemKhachHang.setVisible(false);
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khách hàng này vào giỏ hàng hiện tại?", "Confirm", JOptionPane.OK_OPTION);
            System.out.println(check);
            if (check == 0) {
                searchKhachHang(khachHangMoi.getSdt());
                txtTenKhachHang.setText(khachHang.getHoTen());
                txtSdtKhachHang.setText(khachHang.getSdt());
                txtMaKh.setText(khachHang.getMa());
                txtThemTenKh.setText("");
                txtThemSdtKh.setText("");
                txtThemNgaySinhKh.setText("");
                txtThemEmailKh.setText("");
                txtThemDiaChiKh.setText("");
                grThemGioiTinhKh.clearSelection();
                txtTimKiemKhachHang.setText("");
            } else {
                dlThemKhachHang.setVisible(false);
            }
            Notification panel = new Notification((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Thêm khách hàng thành công");
            panel.showNotification();
        } else {
            JOptionPane.showMessageDialog(this, mess);
        }
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private void cbbTimKiemGioHangTreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemGioHangTreoActionPerformed
        //Cbb tìm kiếm giỏ hàng treo selected
        String selected = (String) cbbTimKiemGioHangTreo.getSelectedItem();
        if (selected == null) {
            return;
        }
        for (int row = 0; row < tbGioHangTreo.getRowCount(); row++) {
            String maGh = (String) tbGioHangTreo.getValueAt(row, 1);
            String sdtKh = (String) tbGioHangTreo.getValueAt(row, 3);
            String tenKh = (String) tbGioHangTreo.getValueAt(row, 2);
            if (selected.equals(maGh) || sdtKh.equals(selected) || tenKh.equals(selected)) {
                tbGioHangTreo.changeSelection(row, 1, false, false);
                GioHangDto gioHangTreo = lstGioHangTreo.get(row);
                lstGioHangChiTietTreo = gioHangChiTietService.getAllByIdGioHang(gioHangTreo.getIdGioHang());
                loadTableGioHangChiTietTreo();
            }
        }
    }//GEN-LAST:event_cbbTimKiemGioHangTreoActionPerformed

    private void txtTimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamActionPerformed
        // TXT tìm kiếm sản phẩm
        searchSanPham();
    }//GEN-LAST:event_txtTimKiemSanPhamActionPerformed

    private void txtTimKiemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKhachHangActionPerformed
        // TXT Tìm kiếm khách hàng
        searchKhachHang(txtTimKiemKhachHang.getText().trim());
        dlDetailKhachHang.setLocationRelativeTo(this);
        dlDetailKhachHang.setVisible(true);
    }//GEN-LAST:event_txtTimKiemKhachHangActionPerformed

    private void cbbHtThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHtThanhToanActionPerformed
        // cbb Hình thức thanh toán
        int selected = cbbHtThanhToan.getSelectedIndex();
        if (selected == 0) {
            tienMatJPanel.setVisible(true);
            nganHangJPanel.setVisible(false);
            ketHopJPanel.setVisible(false);
        } else if (selected == 1) {
            tienMatJPanel.setVisible(false);
            nganHangJPanel.setVisible(true);
            ketHopJPanel.setVisible(false);
        } else if (selected == 2) {
            tienMatJPanel.setVisible(false);
            nganHangJPanel.setVisible(false);
            ketHopJPanel.setVisible(true);
        }
    }//GEN-LAST:event_cbbHtThanhToanActionPerformed

    private void btnTimKiemKhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemKhMouseClicked
        // TxtTimKiemKhachHang
        searchKhachHang(txtTimKiemKhachHang.getText().trim());
        dlDetailKhachHang.setLocationRelativeTo(this);
        dlDetailKhachHang.setVisible(true);
    }//GEN-LAST:event_btnTimKiemKhMouseClicked

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        //TXT Tiền khách đưa type
        if (txtTongTien.getText().length() == 0) {
            return;
        }
        String tienKhachDuaStr = txtTienKhachDua.getText().trim();
        if (tienKhachDuaStr.equals("")) {
            txtTienThua.setText("");
            return;
        }
        BigDecimal tienKhachDua = null;
        try {
            tienKhachDua = new BigDecimal(tienKhachDuaStr);
            if (tienKhachDua.compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa là số");
            e.printStackTrace();
            return;
        }
        String tongTienStr = txtTongTien.getText().replace(".", "").replace("VNĐ", "");
        if (tongTienStr.equals("") || tongTienStr.equals("0")) {
            return;
        }
        BigDecimal tongTien = new BigDecimal(tongTienStr);
        BigDecimal tienThua = tienKhachDua.subtract(tongTien);
        if (tienThua.compareTo(BigDecimal.ZERO) == 1) {
            txtTienThua.setText(moneyFormat.format(tienThua) + "VNĐ");
        } else {
            txtTienThua.setText("");
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtTienKhachDuaKetHopCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKetHopCaretUpdate
        // TXT tiền mặt (kết hợp) Type
        tinhTienThuaThanhToanKetHop();
    }//GEN-LAST:event_txtTienKhachDuaKetHopCaretUpdate

    private void txtNganHangKetHopCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNganHangKetHopCaretUpdate
        // TXT Ngân hàng (kết hợp) Type
        tinhTienThuaThanhToanKetHop();
    }//GEN-LAST:event_txtNganHangKetHopCaretUpdate

    private void txtNganHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNganHangCaretUpdate
        //TXT Tiền ngân hàng type
        if (txtTongTien.getText().length() == 0) {
            return;
        }
        String tienNganHangStr = txtNganHang.getText().trim();
        if (tienNganHangStr.equals("")) {
            txtTienThua.setText("");
            return;
        }
        BigDecimal tienNganHang = null;
        try {
            tienNganHang = new BigDecimal(tienNganHangStr);
            if (tienNganHang.compareTo(BigDecimal.ZERO) == -1) {
                JOptionPane.showMessageDialog(this, "Số tiền khách chuyển khoản phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số tiền khách chuyển khoản là số");
            e.printStackTrace();
            return;
        }
        String tongTienStr = txtTongTien.getText().replace(".", "").replace("VNĐ", "");
        if (tongTienStr.equals("") || tongTienStr.equals("0")) {
            return;
        }
        BigDecimal tongTien = new BigDecimal(tongTienStr);
        BigDecimal tienThua = tienNganHang.subtract(tongTien);
        if (tienThua.compareTo(BigDecimal.ZERO) == 1) {
            txtTienThuaNganHang.setText(moneyFormat.format(tienThua) + "VNĐ");
        } else {
            txtTienThuaNganHang.setText("");
        }
    }//GEN-LAST:event_txtNganHangCaretUpdate

    private void btnHuyGioHangHienTaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyGioHangHienTaiActionPerformed
        // BTN xóa tất cả SP trong giỏ hàng
        if (mapGioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong giỏ hàng");
            return;
        }
        mapGioHang.clear();
        loadTableGioHang();
    }//GEN-LAST:event_btnHuyGioHangHienTaiActionPerformed

    private void btnScanQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanQrActionPerformed
        // BTN Scan QR
        if (webcam != null) {
            if (webcam.isOpen()) {
                webcam.close();
                capture.stop();
                dlScanQr.setVisible(false);
            }
        }
        initWebcam();
        captureThread();
        dlScanQr.setVisible(true);
        dlScanQr.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnScanQrActionPerformed

    private void dlScanQrWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dlScanQrWindowClosing
        // TODO add your handling code here:
        webcam.close();
        capture.stop();
    }//GEN-LAST:event_dlScanQrWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HinhThucThanhToan;
    private pro1041.team_3.swing.ButtonCustom btnCloseShowGioHangTreo;
    private pro1041.team_3.swing.ButtonCustom btnDongChiTietKhachHang;
    private pro1041.team_3.swing.ButtonCustom btnGoiLaiGioHang;
    private pro1041.team_3.swing.ButtonCustom btnHuyGioHangHienTai;
    private pro1041.team_3.swing.ButtonCustom btnLyDoTreo;
    private pro1041.team_3.swing.ButtonCustom btnScanQr;
    private pro1041.team_3.swing.ButtonCustom btnShowDlThemKhachHang;
    private pro1041.team_3.swing.ButtonCustom btnShowGioHangTreo;
    private pro1041.team_3.swing.ButtonCustom btnThanhToan;
    private pro1041.team_3.swing.ButtonCustom btnThemKhVaoGh;
    private pro1041.team_3.swing.ButtonCustom btnThemKhachHang;
    private pro1041.team_3.swing.ButtonCustom btnThemVaoGioHang;
    private javax.swing.JLabel btnTimKiem;
    private javax.swing.JLabel btnTimKiemKh;
    private pro1041.team_3.swing.ButtonCustom btnTreoGioHang;
    private pro1041.team_3.swing.ButtonCustom btnXoaGioHangTreo;
    private pro1041.team_3.swing.ButtonCustom btnXoaTrongGioHang;
    private pro1041.team_3.swing.ButtonCustom buttonCustom3;
    private pro1041.team_3.swing.Combobox cbbHtThanhToan;
    private pro1041.team_3.swing.ComboBoxSuggestion<String> cbbTimKiemGioHangTreo;
    private javax.swing.JDialog dlDetailKhachHang;
    private javax.swing.JDialog dlEditDienThoai;
    private javax.swing.JDialog dlGioHangTreo;
    private javax.swing.JDialog dlLyDo;
    private javax.swing.JDialog dlScanQr;
    private javax.swing.JDialog dlThemKhachHang;
    private javax.swing.ButtonGroup grGender;
    private javax.swing.ButtonGroup grThemGioiTinhKh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpnGiamGia;
    private javax.swing.JPanel jpnWebcam;
    private javax.swing.JScrollPane jspTbGioHang;
    private javax.swing.JScrollPane jspTbGioHangChiTietTreo;
    private javax.swing.JScrollPane jspTbGioHangtreo;
    private javax.swing.JPanel ketHopJPanel;
    private javax.swing.JPanel nganHangJPanel;
    private pro1041.team_3.swing.RadioButtonCustom rdDetailNam;
    private pro1041.team_3.swing.RadioButtonCustom rdDetailNu;
    private pro1041.team_3.swing.RadioButtonCustom rdNamThemKh;
    private pro1041.team_3.swing.RadioButtonCustom rdNuThemKh;
    private pro1041.team_3.swing.config.Table table1;
    private pro1041.team_3.swing.config.Table tbGioHang;
    private pro1041.team_3.swing.config.Table tbGioHangChiTietTreo;
    private pro1041.team_3.swing.config.Table tbGioHangTreo;
    private pro1041.team_3.swing.config.TextAreaScroll textAreaScroll1;
    private pro1041.team_3.swing.config.TextAreaScroll textAreaScroll2;
    private pro1041.team_3.swing.DateChooser themNgaySinhKh;
    private javax.swing.JPanel tienMatJPanel;
    private pro1041.team_3.swing.TextField txtBoNho;
    private javax.swing.JTextArea txtDetailDiaChi;
    private javax.swing.JTextField txtDetailEmail;
    private javax.swing.JTextField txtDetailMaKh;
    private javax.swing.JTextField txtDetailNgaySinh;
    private javax.swing.JTextField txtDetailSdt;
    private javax.swing.JTextField txtDetailTenKh;
    private pro1041.team_3.swing.TextField txtDonGia;
    private pro1041.team_3.swing.TextField txtGiaBan;
    private pro1041.team_3.swing.TextField txtHang;
    private pro1041.team_3.swing.TextField txtIMEI;
    private javax.swing.JTextArea txtLyDo;
    private pro1041.team_3.swing.TextField txtMaDienThoai;
    private pro1041.team_3.swing.TextField txtMaGiaoDich;
    private pro1041.team_3.swing.TextField txtMaGiaoDichKetHop;
    private pro1041.team_3.swing.TextField txtMaKh;
    private pro1041.team_3.swing.TextField txtMauSac;
    private pro1041.team_3.swing.TextArea txtMoTa;
    private pro1041.team_3.swing.TextField txtNganHang;
    private pro1041.team_3.swing.TextField txtNganHangKetHop;
    private pro1041.team_3.swing.TextField txtSdtKhachHang;
    private pro1041.team_3.swing.TextField txtTenDienThoai;
    private pro1041.team_3.swing.TextField txtTenKhachHang;
    private pro1041.team_3.swing.TextArea txtThemDiaChiKh;
    private pro1041.team_3.swing.TextField txtThemEmailKh;
    private pro1041.team_3.swing.TextField txtThemNgaySinhKh;
    private pro1041.team_3.swing.TextField txtThemSdtKh;
    private pro1041.team_3.swing.TextField txtThemTenKh;
    private pro1041.team_3.swing.TextField txtTienKhachDua;
    private pro1041.team_3.swing.TextField txtTienKhachDuaKetHop;
    private pro1041.team_3.swing.TextField txtTienThua;
    private pro1041.team_3.swing.TextField txtTienThuaKetHop;
    private pro1041.team_3.swing.TextField txtTienThuaNganHang;
    private pro1041.team_3.swing.TextFieldSuggestion txtTimKiemKhachHang;
    private pro1041.team_3.swing.TextFieldSuggestion txtTimKiemSanPham;
    private pro1041.team_3.swing.TextField txtTinhTrang;
    private pro1041.team_3.swing.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
