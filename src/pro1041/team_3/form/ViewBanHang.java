package pro1041.team_3.form;

import java.awt.Panel;
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
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.GioHang;
import pro1041.team_3.domainModel.KhachHang;
import pro1041.team_3.domainModel.NhanVien;
import pro1041.team_3.dto.BhChiTietDienThoaiDto;
import pro1041.team_3.dto.GioHangChiTietDto;
import pro1041.team_3.dto.GioHangDto;
import pro1041.team_3.dto.GioHangRequest;
import pro1041.team_3.dto.HoaDonRequest;
import pro1041.team_3.dto.KhachHangDto;
import pro1041.team_3.service.BanHangService;
import pro1041.team_3.service.ChiTietDienThoaiService;
import pro1041.team_3.service.GioHangChiTietService;
import pro1041.team_3.service.GioHangService;
import pro1041.team_3.service.KhachHangService;
import pro1041.team_3.service.impl.BanHangServiceImpl;
import pro1041.team_3.service.impl.GioHangChiTietServiceImpl;
import pro1041.team_3.service.impl.GioHangServiceImpl;
import pro1041.team_3.service.impl.KhachHangServiceImpl;
import pro1041.team_3.swing.MessageAlert;

/**
 *
 * @author sonptph19600
 */
public class ViewBanHang extends javax.swing.JPanel {

    private KhachHangService khachHangService;
    private BanHangService banHangService;
    private GioHangChiTietService gioHangChiTietService;
    private GioHangService gioHangService;
    private Map<UUID, BhChiTietDienThoaiDto> mapGioHang;
    private BhChiTietDienThoaiDto ctdtFind;
    private GioHang gioHangHienTai;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private DecimalFormat moneyFormat;
    private List<GioHangDto> lstGioHangTreo;
    private List<BhChiTietDienThoaiDto> lstGioHangChiTietTreo;

    public ViewBanHang() {
        initComponents();
//        nhanVien = nhanVienHienTai; Set nhân viên đăng nhập hiện tại
        //Khởi tạo service
        khachHangService = new KhachHangServiceImpl();
        banHangService = new BanHangServiceImpl();
        gioHangChiTietService = new GioHangChiTietServiceImpl();
        gioHangService = new GioHangServiceImpl();
        mapGioHang = new HashMap<>();
        //Biến toàn cục
        moneyFormat = new DecimalFormat("#,###");
        gioHangHienTai = null;
        khachHang = null;
        nhanVien = null;
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

//        Setting Scrollbar cho Table
        tbGioHang.fixTable(jspTbGioHang);
        tbGioHangTreo.fixTable(jspTbGioHangtreo);
        tbGioHangChiTietTreo.fixTable(jspTbGioHangChiTietTreo);
//
//        List<KhachHangDto> lst = khachHangService.getAll();
//        for (KhachHangDto x : lst) {
//            txtTest.addItemSuggestion(x.getSdt());
//        }
    }

    private void clearFormInput() {
        txtMaKh.setText("");
        txtTenKh.setText("");
        txtTongTien.setText("");
        txtTienKhachDua.setText("");
        txtTienKhachDuaKetHop.setText("");
        txtNganHangKetHop.setText("");
        txtMaGiaoDich.setText("");
        txtMaGiaoDichKetHop.setText("");

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
        }
        txtTongTien.setText(moneyFormat.format(tongTien).toString() + "VNĐ");
    }

    private void searchKhachHang() {
        String keyWord = txtTimKiemKh.getText().trim();
        if (keyWord.isEmpty()) {
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
        dlDetailKhachHang.setLocationRelativeTo(this);
        dlDetailKhachHang.setVisible(true);
        khachHang = khachHangFind;
    }

    private void searchSanPham() {
        String keyWord = txtTimKiemSp.getText().trim();
        if (keyWord.isEmpty()) {
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
        txtMaSP.setText(result.getMa());
        txtTenSP.setText(result.getTen());
        txtIMEI.setText(result.getImei());
        txtHang.setText(result.getHang());
        txtMauSac.setText(result.getMauSac());
        txtTinhTrang.setText(result.getTinhTrang() == 0 ? "Mới" : "Cũ");
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
        if (tienThua.compareTo(BigDecimal.ZERO) == 1 || tienThua.compareTo(BigDecimal.ZERO) == 0) {
            txtTienThuaKetHop.setText(moneyFormat.format(tienThua));
        }
    }

    private void loadTableGioHangTreo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy kk:mm");
        lstGioHangTreo = gioHangService.getAllResponse();
        if (lstGioHangTreo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có giỏ hàng nào đang treo");
            dlGioHangTreo.setVisible(false);
            return;
        } else {
            dlGioHangTreo.setVisible(true);
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
                x.getMaNhanVien(),
                sdf.format(x.getNgayTao()),
                x.getLyDo() == null || x.getLyDo().equals("") ? "-" : x.getLyDo()
            });
            count += 1;
        }
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
        rdDetailNam = new javax.swing.JRadioButton();
        rdDetailNu = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        txtDetailEmail = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDetailDiaChi = new javax.swing.JTextArea();
        jPanel14 = new javax.swing.JPanel();
        btnThemKhVaoGh = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnDongChiTietKhachHang = new javax.swing.JLabel();
        grGender = new javax.swing.ButtonGroup();
        dlLyDo = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLyDo = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        btnLyDoTreo = new javax.swing.JLabel();
        dlGioHangTreo = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jspTbGioHangChiTietTreo = new javax.swing.JScrollPane();
        tbGioHangChiTietTreo = new pro1041.team_3.swing.Table();
        jPanel9 = new javax.swing.JPanel();
        jspTbGioHangtreo = new javax.swing.JScrollPane();
        tbGioHangTreo = new pro1041.team_3.swing.Table();
        jPanel18 = new javax.swing.JPanel();
        btnGoiLaiGioHang = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnCloseShowGioHangTreo = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        btnXoaGioHangTreo = new javax.swing.JLabel();
        dlThemKhachHang = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        txtThemSdtKh = new pro1041.team_3.swing.TextField();
        txtThemTenKh = new pro1041.team_3.swing.TextField();
        jPanel23 = new javax.swing.JPanel();
        rdNamThemKh = new pro1041.team_3.swing.RadioButtonCustom();
        rdNuThemKh = new pro1041.team_3.swing.RadioButtonCustom();
        txtThemNgaySinhKh = new pro1041.team_3.swing.TextField();
        txtThemEmailKh = new pro1041.team_3.swing.TextField();
        txtThemDiaChiKh = new pro1041.team_3.swing.TextField();
        jPanel24 = new javax.swing.JPanel();
        btnThemKhachHang = new javax.swing.JLabel();
        themNgaySinhKh = new pro1041.team_3.swing.DateChooser();
        grThemGioiTinhKh = new javax.swing.ButtonGroup();
        jspTbGioHang = new javax.swing.JScrollPane();
        tbGioHang = new pro1041.team_3.swing.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        HinhThucThanhToan = new javax.swing.JPanel();
        tienMatJPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        buttonCustom3 = new pro1041.team_3.swing.ButtonCustom();
        nganHangJPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtMaGiaoDich = new javax.swing.JTextField();
        ketHopJPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTienKhachDuaKetHop = new javax.swing.JTextField();
        txtNganHangKetHop = new javax.swing.JTextField();
        txtMaGiaoDichKetHop = new javax.swing.JTextField();
        txtTienThuaKetHop = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        txtMaKh = new javax.swing.JTextField();
        txtTimKiemKh = new javax.swing.JTextField();
        txtTimKiemKH = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtSdtKhachHang = new javax.swing.JTextField();
        cbbHtThanhToan = new pro1041.team_3.swing.Combobox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiemSp = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtIMEI = new javax.swing.JTextField();
        txtMauSac = new javax.swing.JTextField();
        txtBoNho = new javax.swing.JTextField();
        txtTinhTrang = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtHang = new javax.swing.JTextField();
        jpnGiamGia = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThemVaoGioHang = new pro1041.team_3.swing.ButtonCustom();
        jPanel5 = new javax.swing.JPanel();
        btnThanhToan = new pro1041.team_3.swing.ButtonCustom();
        btnTreoGioHang = new pro1041.team_3.swing.ButtonCustom();
        btnShowGioHangTreo = new pro1041.team_3.swing.ButtonCustom();
        btnXoaTrongGioHang = new pro1041.team_3.swing.ButtonCustom();
        btnShowDlThemKhachHang = new pro1041.team_3.swing.ButtonCustom();

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

        grGender.add(rdDetailNam);
        rdDetailNam.setText("Nam");
        jPanel6.add(rdDetailNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        grGender.add(rdDetailNu);
        rdDetailNu.setText("Nữ");
        jPanel6.add(rdDetailNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

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

        jPanel14.setBackground(new java.awt.Color(1, 181, 204));

        btnThemKhVaoGh.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThemKhVaoGh.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKhVaoGh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemKhVaoGh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/addCart.png"))); // NOI18N
        btnThemKhVaoGh.setText("Thêm");
        btnThemKhVaoGh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKhVaoGh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemKhVaoGhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(btnThemKhVaoGh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThemKhVaoGh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 100, 35));

        jPanel15.setBackground(new java.awt.Color(1, 181, 204));

        btnDongChiTietKhachHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnDongChiTietKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnDongChiTietKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDongChiTietKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/back.png"))); // NOI18N
        btnDongChiTietKhachHang.setText("Quay lại");
        btnDongChiTietKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDongChiTietKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDongChiTietKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(btnDongChiTietKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDongChiTietKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, -1));

        javax.swing.GroupLayout dlDetailKhachHangLayout = new javax.swing.GroupLayout(dlDetailKhachHang.getContentPane());
        dlDetailKhachHang.getContentPane().setLayout(dlDetailKhachHangLayout);
        dlDetailKhachHangLayout.setHorizontalGroup(
            dlDetailKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dlDetailKhachHangLayout.setVerticalGroup(
            dlDetailKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        dlLyDo.setBackground(new java.awt.Color(255, 255, 255));
        dlLyDo.setResizable(false);
        dlLyDo.setSize(new java.awt.Dimension(410, 260));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLyDo.setColumns(20);
        txtLyDo.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtLyDo.setRows(5);
        txtLyDo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray), "Lý do"));
        jScrollPane3.setViewportView(txtLyDo);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 376, 160));

        jPanel16.setBackground(new java.awt.Color(1, 181, 204));

        btnLyDoTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnLyDoTreo.setForeground(new java.awt.Color(255, 255, 255));
        btnLyDoTreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLyDoTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/waiting.png"))); // NOI18N
        btnLyDoTreo.setText("Treo");
        btnLyDoTreo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLyDoTreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLyDoTreoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLyDoTreo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLyDoTreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

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
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 12))); // NOI18N
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

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 780, 260));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 12))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbGioHangTreo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã GH", "Tên KH", "Mã NV", "Ngày tạo", "Lý do"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        }

        jPanel9.add(jspTbGioHangtreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 760, 270));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 300));

        jPanel18.setBackground(new java.awt.Color(1, 181, 204));

        btnGoiLaiGioHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnGoiLaiGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnGoiLaiGioHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGoiLaiGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/change.png"))); // NOI18N
        btnGoiLaiGioHang.setText("Gọi lại GH");
        btnGoiLaiGioHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoiLaiGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGoiLaiGioHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGoiLaiGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGoiLaiGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 20, 110, -1));

        jPanel19.setBackground(new java.awt.Color(1, 181, 204));

        btnCloseShowGioHangTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnCloseShowGioHangTreo.setForeground(new java.awt.Color(255, 255, 255));
        btnCloseShowGioHangTreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCloseShowGioHangTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/change.png"))); // NOI18N
        btnCloseShowGioHangTreo.setText("Gọi lại GH");
        btnCloseShowGioHangTreo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCloseShowGioHangTreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseShowGioHangTreoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCloseShowGioHangTreo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCloseShowGioHangTreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        jPanel20.setBackground(new java.awt.Color(1, 181, 204));

        btnXoaGioHangTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnXoaGioHangTreo.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaGioHangTreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoaGioHangTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/change.png"))); // NOI18N
        btnXoaGioHangTreo.setText("Gọi lại GH");
        btnXoaGioHangTreo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaGioHangTreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaGioHangTreoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXoaGioHangTreo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXoaGioHangTreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

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

        dlThemKhachHang.setSize(new java.awt.Dimension(290, 450));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtThemSdtKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemSdtKh.setLabelText("Số điện thoại");
        jPanel22.add(txtThemSdtKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 240, -1));

        txtThemTenKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemTenKh.setLabelText("Họ và tên");
        jPanel22.add(txtThemTenKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray), "Giới tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 12))); // NOI18N
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        grThemGioiTinhKh.add(rdNamThemKh);
        rdNamThemKh.setText("Nam");
        jPanel23.add(rdNamThemKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        grThemGioiTinhKh.add(rdNuThemKh);
        rdNuThemKh.setText("Nữ");
        jPanel23.add(rdNuThemKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 240, 60));

        txtThemNgaySinhKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemNgaySinhKh.setLabelText("Ngày sinh");
        jPanel22.add(txtThemNgaySinhKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, -1));

        txtThemEmailKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemEmailKh.setLabelText("Địa chỉ");
        jPanel22.add(txtThemEmailKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 240, -1));

        txtThemDiaChiKh.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtThemDiaChiKh.setLabelText("Địa chỉ");
        jPanel22.add(txtThemDiaChiKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 240, -1));

        jPanel24.setBackground(new java.awt.Color(1, 181, 204));

        btnThemKhachHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnThemKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/addUser.png"))); // NOI18N
        btnThemKhachHang.setText("Thêm");
        btnThemKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel22.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, -1, 30));

        javax.swing.GroupLayout dlThemKhachHangLayout = new javax.swing.GroupLayout(dlThemKhachHang.getContentPane());
        dlThemKhachHang.getContentPane().setLayout(dlThemKhachHangLayout);
        dlThemKhachHangLayout.setHorizontalGroup(
            dlThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        dlThemKhachHangLayout.setVerticalGroup(
            dlThemKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        themNgaySinhKh.setTextRefernce(txtThemNgaySinhKh);

        setBackground(new java.awt.Color(250, 250, 250));
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
        jspTbGioHang.setViewportView(tbGioHang);
        if (tbGioHang.getColumnModel().getColumnCount() > 0) {
            tbGioHang.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbGioHang.getColumnModel().getColumn(1).setPreferredWidth(15);
            tbGioHang.getColumnModel().getColumn(2).setPreferredWidth(80);
            tbGioHang.getColumnModel().getColumn(5).setPreferredWidth(20);
            tbGioHang.getColumnModel().getColumn(6).setPreferredWidth(20);
            tbGioHang.getColumnModel().getColumn(7).setPreferredWidth(10);
        }

        add(jspTbGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 1100, 310));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Hóa đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel3.setText("Tổng tiền (VNĐ)");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel4.setText("HT thanh toán");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        txtTongTien.setEditable(false);
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel2.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 200, 20));

        HinhThucThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        HinhThucThanhToan.setLayout(new java.awt.CardLayout());

        tienMatJPanel.setBackground(new java.awt.Color(255, 255, 255));
        tienMatJPanel.setPreferredSize(new java.awt.Dimension(300, 190));
        tienMatJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel6.setText("Tiền khách đưa");
        tienMatJPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });
        tienMatJPanel.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 200, -1));

        jLabel7.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel7.setText("Tiền thừa");
        tienMatJPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 60, -1));

        txtTienThua.setEditable(false);
        txtTienThua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        tienMatJPanel.add(txtTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 200, -1));

        buttonCustom3.setBackground(new java.awt.Color(1, 181, 204));
        buttonCustom3.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom3.setText("buttonCustom1");
        buttonCustom3.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        tienMatJPanel.add(buttonCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 120, -1));

        HinhThucThanhToan.add(tienMatJPanel, "card2");

        nganHangJPanel.setBackground(new java.awt.Color(255, 255, 255));
        nganHangJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel8.setText("Mã giao dịch");
        nganHangJPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtMaGiaoDich.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        nganHangJPanel.add(txtMaGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 190, -1));

        HinhThucThanhToan.add(nganHangJPanel, "card3");

        ketHopJPanel.setBackground(new java.awt.Color(255, 255, 255));
        ketHopJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel10.setText("Tiền mặt");
        ketHopJPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel11.setText("Ngân hàng");
        ketHopJPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel12.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel12.setText("Mã giao dịch");
        ketHopJPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel19.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel19.setText("Tiền thừa");
        ketHopJPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        txtTienKhachDuaKetHop.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        txtTienKhachDuaKetHop.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaKetHopCaretUpdate(evt);
            }
        });
        ketHopJPanel.add(txtTienKhachDuaKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 200, -1));

        txtNganHangKetHop.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        txtNganHangKetHop.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNganHangKetHopCaretUpdate(evt);
            }
        });
        ketHopJPanel.add(txtNganHangKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 200, -1));

        txtMaGiaoDichKetHop.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        ketHopJPanel.add(txtMaGiaoDichKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 200, -1));

        txtTienThuaKetHop.setEditable(false);
        txtTienThuaKetHop.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        ketHopJPanel.add(txtTienThuaKetHop, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 200, -1));

        HinhThucThanhToan.add(ketHopJPanel, "card4");

        jPanel2.add(HinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 310, 150));

        jLabel13.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel13.setText("Mã KH");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel14.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel14.setText("Tên KH");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtTenKh.setEditable(false);
        txtTenKh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel2.add(txtTenKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 200, -1));

        txtMaKh.setEditable(false);
        txtMaKh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel2.add(txtMaKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 200, -1));

        txtTimKiemKh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        txtTimKiemKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKhActionPerformed(evt);
            }
        });
        jPanel2.add(txtTimKiemKh, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 200, 20));

        txtTimKiemKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTimKiemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/customSearch.png"))); // NOI18N
        txtTimKiemKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtTimKiemKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemKHMouseClicked(evt);
            }
        });
        jPanel2.add(txtTimKiemKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 30, 30));

        jLabel30.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        jLabel30.setText("SĐT");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtSdtKhachHang.setEditable(false);
        txtSdtKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel2.add(txtSdtKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 200, -1));

        cbbHtThanhToan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền mặt", "Ngân hàng", "Kết hợp" }));
        cbbHtThanhToan.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        cbbHtThanhToan.setLabeText("");
        cbbHtThanhToan.setLineColor(new java.awt.Color(1, 181, 204));
        cbbHtThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHtThanhToanActionPerformed(evt);
            }
        });
        jPanel2.add(cbbHtThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 200, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 360, 390));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nunito", 0, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiemSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        txtTimKiemSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSpActionPerformed(evt);
            }
        });
        jPanel4.add(txtTimKiemSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, 30));

        btnTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/customSearch.png"))); // NOI18N
        btnTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        jPanel4.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 30, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 310, 60));

        jLabel5.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel5.setText("Mã SP");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel15.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel15.setText("Tên SP");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel16.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel16.setText("IMEI");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel17.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel17.setText("Hãng");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel18.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel18.setText("Màu sắc");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jLabel20.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel20.setText("Bộ nhớ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        jLabel21.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel21.setText("Tình trạng");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        jLabel22.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel22.setText("Đơn giá");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        txtMaSP.setEditable(false);
        txtMaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 180, -1));

        txtTenSP.setEditable(false);
        txtTenSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 180, -1));

        txtIMEI.setEditable(false);
        txtIMEI.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtIMEI, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 180, -1));

        txtMauSac.setEditable(false);
        txtMauSac.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 180, -1));

        txtBoNho.setEditable(false);
        txtBoNho.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtBoNho, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 180, -1));

        txtTinhTrang.setEditable(false);
        txtTinhTrang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtTinhTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 180, -1));

        txtDonGia.setEditable(false);
        txtDonGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 180, -1));

        txtHang.setEditable(false);
        txtHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));
        jPanel1.add(txtHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 180, -1));

        jpnGiamGia.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        jLabel1.setText("Giá giảm");

        txtGiaBan.setEditable(false);
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(1, 181, 204)));

        javax.swing.GroupLayout jpnGiamGiaLayout = new javax.swing.GroupLayout(jpnGiamGia);
        jpnGiamGia.setLayout(jpnGiamGiaLayout);
        jpnGiamGiaLayout.setHorizontalGroup(
            jpnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnGiamGiaLayout.setVerticalGroup(
            jpnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnGiamGiaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jpnGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, 40));

        jLabel2.setText("Mô tả");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtMoTa.setEditable(false);
        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Nunito", 0, 12)); // NOI18N
        txtMoTa.setLineWrap(true);
        txtMoTa.setRows(4);
        txtMoTa.setTabSize(4);
        jScrollPane5.setViewportView(txtMoTa);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 290, 190, -1));

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
        jPanel1.add(btnThemVaoGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 150, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 550, 390));

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
        jPanel5.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, -1));

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
        btnShowGioHangTreo.setText("Gọi GH");
        btnShowGioHangTreo.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnShowGioHangTreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowGioHangTreoActionPerformed(evt);
            }
        });
        jPanel5.add(btnShowGioHangTreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, -1));

        btnXoaTrongGioHang.setBackground(new java.awt.Color(1, 181, 204));
        btnXoaTrongGioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTrongGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/delete.png"))); // NOI18N
        btnXoaTrongGioHang.setText("Xóa");
        btnXoaTrongGioHang.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnXoaTrongGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrongGioHangActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoaTrongGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 140, -1));

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
        jPanel5.add(btnShowDlThemKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 140, -1));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 27, 160, 370));
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSpActionPerformed
        //TXT tìm kiếm sản phẩm Enter
        searchSanPham();
    }//GEN-LAST:event_txtTimKiemSpActionPerformed

    private void txtTimKiemKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemKHMouseClicked
        // TxtTimKiemKhachHang
        searchKhachHang();
    }//GEN-LAST:event_txtTimKiemKHMouseClicked

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        // BTN Tìm kiếm sản phẩm
        searchSanPham();
    }//GEN-LAST:event_btnTimKiemMouseClicked

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
            txtTienThua.setText(moneyFormat.format(tienThua));
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtNganHangKetHopCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNganHangKetHopCaretUpdate
        // TXT Ngân hàng (kết hợp) Type
        tinhTienThuaThanhToanKetHop();
    }//GEN-LAST:event_txtNganHangKetHopCaretUpdate

    private void txtTienKhachDuaKetHopCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKetHopCaretUpdate
        // TXT tiền mặt (kết hợp) Type
        tinhTienThuaThanhToanKetHop();
    }//GEN-LAST:event_txtTienKhachDuaKetHopCaretUpdate

    private void tbGioHangTreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangTreoMouseClicked
        // TB giỏ hàng treo click
        int row = tbGioHangTreo.getSelectedRow();
        GioHangDto gioHangTreo = lstGioHangTreo.get(row);
        lstGioHangChiTietTreo = gioHangChiTietService.getAllByIdGioHang(gioHangTreo.getIdGioHang());
        loadTableGioHangChiTietTreo();
    }//GEN-LAST:event_tbGioHangTreoMouseClicked

    private void btnThemKhVaoGhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKhVaoGhMouseClicked
        // BTN thêm khách hàng
        txtMaKh.setText(khachHang.getMa());
        txtTenKh.setText(khachHang.getHoTen());
        dlDetailKhachHang.setVisible(false);
    }//GEN-LAST:event_btnThemKhVaoGhMouseClicked

    private void btnDongChiTietKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDongChiTietKhachHangMouseClicked
        // BTN close Detail
        dlDetailKhachHang.setVisible(false);
        khachHang = null;
    }//GEN-LAST:event_btnDongChiTietKhachHangMouseClicked

    private void btnLyDoTreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLyDoTreoMouseClicked
        // BTN treo - nhập lý do xong
        dlLyDo.setVisible(false);
        List<GioHangRequest> lstSp = new ArrayList<>();
        for (BhChiTietDienThoaiDto x : mapGioHang.values()) {
            GioHangRequest ghct = new GioHangRequest();
            ghct.setIdChiTietDienThoai(x.getId());
            ghct.setKhachHang(khachHang);
            ghct.setNhanVien(nhanVien);
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
    }//GEN-LAST:event_btnLyDoTreoMouseClicked

    private void btnGoiLaiGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoiLaiGioHangMouseClicked
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
        if (gioHang.getIdKhachHang() != null) {
            khachHang = new KhachHang();
            khachHang.setId(gioHang.getIdKhachHang());
            txtMaKh.setText(gioHang.getMaKhachHang());
            txtTenKh.setText(gioHang.getTenKhachHang());
            txtSdtKhachHang.setText(gioHang.getSdtKhachHang());
        }
        mapGioHang.clear();
        List<BhChiTietDienThoaiDto> lstDienThoai = gioHangChiTietService.getAllByIdGioHang(gioHang.getIdGioHang());
        for (BhChiTietDienThoaiDto x : lstDienThoai) {
            if (x.getTrangThai() == 1) {
                JOptionPane.showMessageDialog(this, "Điện thoại: " + x.getTen()
                        + "\nIMEI: " + x.getImei()
                        + "\nHãng: " + x.getHang()
                        + "\nMàu sắc: " + x.getMauSac()
                        + "\nĐã được bán bán");
                continue;
            }
            mapGioHang.put(x.getId(), x);
        }
        loadTableGioHang();
        gioHangHienTai = new GioHang();
        gioHangHienTai.setId(gioHang.getIdGioHang());
        dlGioHangTreo.setVisible(false);
    }//GEN-LAST:event_btnGoiLaiGioHangMouseClicked

    private void btnCloseShowGioHangTreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseShowGioHangTreoMouseClicked
        // BTN Close Ds Giỏ hàng treo
        dlGioHangTreo.setVisible(false);
    }//GEN-LAST:event_btnCloseShowGioHangTreoMouseClicked

    private void btnXoaGioHangTreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaGioHangTreoMouseClicked
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
    }//GEN-LAST:event_btnXoaGioHangTreoMouseClicked

    private void txtTimKiemKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKhActionPerformed
        // TXT Tìm kiếm KH type
        searchKhachHang();
    }//GEN-LAST:event_txtTimKiemKhActionPerformed

    private void btnThemKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKhachHangMouseClicked
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
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khách hàng này vào giỏ hàng hiện tại?", "Confirm", JOptionPane.OK_OPTION);
            if (check == 0) {
                txtTimKiemKH.setText(khachHangMoi.getSdt());
                searchKhachHang();
            }
        }
    }//GEN-LAST:event_btnThemKhachHangMouseClicked

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
            String nganHangStr = txtTongTien.getText().trim();
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
            hdct.setNhanVien(nhanVien);
            hdct.setDonGia(x.getDonGia());
            hdct.setGiaBan(x.getGiaBan());
            hdct.setHinhThucThanhToan(hinhThucThanhToan);
            hdct.setTongTien(tongTien);
            hdct.setTienMat(tienMat);
            hdct.setNganHang(nganHang);
            hdct.setMaGiaoDich(maGiaoDich);
            lstHoaDonChiTiet.add(hdct);
        }
        String mess = banHangService.thanhToan(lstHoaDonChiTiet);
        if (mess.equals("Thanh toán thành công")) {
            gioHangHienTai = null;
            mapGioHang.clear();
            loadTableGioHang();
            khachHang = null;
            clearFormInput();
            if (gioHangHienTai != null) {
                Boolean check = gioHangChiTietService.deteleByIdGioHang(gioHangHienTai.getId());
                String messDelete = gioHangService.delete(gioHangHienTai.getId());
                if (!check && !messDelete.equals("Xóa giỏ hàng treo thành công")) {
                    JOptionPane.showMessageDialog(this, "Lỗi hệ thống. \nKhông thể xóa giỏ hàng treo");
                    return;
                }
            }
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
        // BTN Show ds HĐ treo
        DefaultTableModel model = (DefaultTableModel) tbGioHangChiTietTreo.getModel();
        model.setRowCount(0);
        loadTableGioHangTreo();
        dlGioHangTreo.setLocationRelativeTo(null);
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
//            MessageAlert alert = new MessageAlert((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), false , "Vui lòng nhập IMEI sản phẩm và tìm kiếm");
//            alert.showAlert();
            return;
        }
        if (ctdtFind.getTrangThai() == 1) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã bán");
            return;
        }
        if (mapGioHang.containsKey(ctdtFind.getId())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại trong giỏ hàng");
            return;
        }
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtIMEI.setText("");
        txtGiaBan.setText("");
        txtMauSac.setText("");
        txtHang.setText("");
        txtTinhTrang.setText("");
        txtBoNho.setText("");
        txtDonGia.setText("");
        txtMoTa.setText("");
        mapGioHang.put(ctdtFind.getId(), ctdtFind);
        loadTableGioHang();
    }//GEN-LAST:event_btnThemVaoGioHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HinhThucThanhToan;
    private javax.swing.JLabel btnCloseShowGioHangTreo;
    private javax.swing.JLabel btnDongChiTietKhachHang;
    private javax.swing.JLabel btnGoiLaiGioHang;
    private javax.swing.JLabel btnLyDoTreo;
    private pro1041.team_3.swing.ButtonCustom btnShowDlThemKhachHang;
    private pro1041.team_3.swing.ButtonCustom btnShowGioHangTreo;
    private pro1041.team_3.swing.ButtonCustom btnThanhToan;
    private javax.swing.JLabel btnThemKhVaoGh;
    private javax.swing.JLabel btnThemKhachHang;
    private pro1041.team_3.swing.ButtonCustom btnThemVaoGioHang;
    private javax.swing.JLabel btnTimKiem;
    private pro1041.team_3.swing.ButtonCustom btnTreoGioHang;
    private javax.swing.JLabel btnXoaGioHangTreo;
    private pro1041.team_3.swing.ButtonCustom btnXoaTrongGioHang;
    private pro1041.team_3.swing.ButtonCustom buttonCustom3;
    private pro1041.team_3.swing.Combobox cbbHtThanhToan;
    private javax.swing.JDialog dlDetailKhachHang;
    private javax.swing.JDialog dlGioHangTreo;
    private javax.swing.JDialog dlLyDo;
    private javax.swing.JDialog dlThemKhachHang;
    private javax.swing.ButtonGroup grGender;
    private javax.swing.ButtonGroup grThemGioiTinhKh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpnGiamGia;
    private javax.swing.JScrollPane jspTbGioHang;
    private javax.swing.JScrollPane jspTbGioHangChiTietTreo;
    private javax.swing.JScrollPane jspTbGioHangtreo;
    private javax.swing.JPanel ketHopJPanel;
    private javax.swing.JPanel nganHangJPanel;
    private javax.swing.JRadioButton rdDetailNam;
    private javax.swing.JRadioButton rdDetailNu;
    private pro1041.team_3.swing.RadioButtonCustom rdNamThemKh;
    private pro1041.team_3.swing.RadioButtonCustom rdNuThemKh;
    private pro1041.team_3.swing.Table tbGioHang;
    private pro1041.team_3.swing.Table tbGioHangChiTietTreo;
    private pro1041.team_3.swing.Table tbGioHangTreo;
    private pro1041.team_3.swing.DateChooser themNgaySinhKh;
    private javax.swing.JPanel tienMatJPanel;
    private javax.swing.JTextField txtBoNho;
    private javax.swing.JTextArea txtDetailDiaChi;
    private javax.swing.JTextField txtDetailEmail;
    private javax.swing.JTextField txtDetailMaKh;
    private javax.swing.JTextField txtDetailNgaySinh;
    private javax.swing.JTextField txtDetailSdt;
    private javax.swing.JTextField txtDetailTenKh;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtHang;
    private javax.swing.JTextField txtIMEI;
    private javax.swing.JTextArea txtLyDo;
    private javax.swing.JTextField txtMaGiaoDich;
    private javax.swing.JTextField txtMaGiaoDichKetHop;
    private javax.swing.JTextField txtMaKh;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNganHangKetHop;
    private javax.swing.JTextField txtSdtKhachHang;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTenSP;
    private pro1041.team_3.swing.TextField txtThemDiaChiKh;
    private pro1041.team_3.swing.TextField txtThemEmailKh;
    private pro1041.team_3.swing.TextField txtThemNgaySinhKh;
    private pro1041.team_3.swing.TextField txtThemSdtKh;
    private pro1041.team_3.swing.TextField txtThemTenKh;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienKhachDuaKetHop;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTienThuaKetHop;
    private javax.swing.JLabel txtTimKiemKH;
    private javax.swing.JTextField txtTimKiemKh;
    private javax.swing.JTextField txtTimKiemSp;
    private javax.swing.JTextField txtTinhTrang;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
