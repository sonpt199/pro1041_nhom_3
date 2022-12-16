package pro1041.team_3.form;

import pro1041.team_3.chart.ModelChartLine;
import pro1041.team_3.chart.ModelChartPie;
import pro1041.team_3.model.ModelStaff;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.domainModel.HoaDon;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.service.HoaDonChiTietService;
import pro1041.team_3.service.HoaDonService;
import pro1041.team_3.service.impl.HoaDonChiTietServiceImpl;
import pro1041.team_3.service.impl.HoaDonServiceImpl;
import pro1041.team_3.swing.Notification;
import pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser;

public class ViewQuanLyHoaDon extends javax.swing.JPanel {

    private HoaDonService hoaDonService;
    private HoaDonChiTietService hoaDonChiTietService;
    private List<HoaDonDto> lstHoaDon = new ArrayList<>();
    SimpleDateFormat sdfNgayThanhToan;
    private DecimalFormat moneyFormat;

    public ViewQuanLyHoaDon() {
        initComponents();
        //Khởi tạo service
        hoaDonService = new HoaDonServiceImpl();
        hoaDonChiTietService = new HoaDonChiTietServiceImpl();
        //Biến toàn cục
        sdfNgayThanhToan = new SimpleDateFormat("kk:mm dd/MM/yy");
        moneyFormat = moneyFormat = new DecimalFormat("#,###");

        //Setting table
        ImageIcon iconDialog = new ImageIcon(getClass().getResource("/pro1041/team_3/icon/logoCircle.png"));
        dlHoaDonChiTiet.setLocationRelativeTo(null);
        dlHoaDonChiTiet.setTitle("Hóa đơn chi tiết");
        dlHoaDonChiTiet.setIconImage(iconDialog.getImage());
        tbHoaDonChiTiet.fixTable(jspTbHoaDonChiTiet);
        tbHoaDon.fixTable(jspTbHoaDon);
        loadTableHoaDon(hoaDonService.getAllResponse());
        tpThoiGianBatDau.now();
        tpThoiGianKetThuc.now();
//        lstHoaDon = hoaDonService.getAllResponse();
    }

    private void loadTableHoaDon(List<HoaDonDto> list) {
//        lstHoaDon = hoaDonService.getAllResponse();
//        if (list.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
//            return;
//        }
        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
        model.setRowCount(0);
        int count = 1;
        for (HoaDonDto x : list) {
            Integer httt = x.getHinhThucThanhToan();
            model.addRow(new Object[]{
                count,
                x.getMaHoaDon(),
                x.getMaKhachHang() == null || x.getMaKhachHang().equals("") ? "-" : x.getMaKhachHang(),
                x.getSdtKhachHang() == null || x.getSdtKhachHang().equals("") ? "-" : x.getSdtKhachHang(),
                x.getMaNhanVien(),
                sdfNgayThanhToan.format(x.getNgayThanhToan()),
                httt == 0 ? "Tiền mặt" : httt == 1 ? "Ngân hàng" : httt == 2 ? "Kết hợp" : "",
                moneyFormat.format(x.getTongTien()) + "VNĐ"
            });
            count += 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlHoaDonChiTiet = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jspTbHoaDonChiTiet = new javax.swing.JScrollPane();
        tbHoaDonChiTiet = new pro1041.team_3.swing.config.Table();
        tpThoiGianKetThuc = new pro1041.team_3.swing.TimePicker();
        tpThoiGianBatDau = new pro1041.team_3.swing.TimePicker();
        dlChonNgayKetThuc = new pro1041.team_3.swing.DateChooser();
        dlChonNgayBatDau = new pro1041.team_3.swing.DateChooser();
        jspTbHoaDon = new javax.swing.JScrollPane();
        tbHoaDon = new pro1041.team_3.swing.config.Table();
        jPanel1 = new javax.swing.JPanel();
        txtTongTien = new pro1041.team_3.swing.TextField();
        txtMaHoaDon = new pro1041.team_3.swing.TextField();
        txtMaKhachHang = new pro1041.team_3.swing.TextField();
        txtTenKhachHang = new pro1041.team_3.swing.TextField();
        txtSdtKhachHang = new pro1041.team_3.swing.TextField();
        txtMaNhanVien = new pro1041.team_3.swing.TextField();
        txtNgayThanhToan = new pro1041.team_3.swing.TextField();
        txtHinhThucThanhToan = new pro1041.team_3.swing.TextField();
        txtNganHang = new pro1041.team_3.swing.TextField();
        txtMaGiaoDich = new pro1041.team_3.swing.TextField();
        txtTenNhanVien = new pro1041.team_3.swing.TextField();
        txtTienMat = new pro1041.team_3.swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        btnXemChiTiet = new pro1041.team_3.swing.ButtonCustom();
        txtTimKiem = new pro1041.team_3.swing.TextField();
        txtNgayBD = new pro1041.team_3.swing.TextField();
        txtNgayKT = new pro1041.team_3.swing.TextField();
        txtThoiGianKT = new pro1041.team_3.swing.TextField();
        txtThoiGianBD = new pro1041.team_3.swing.TextField();
        cbbLocHttt = new pro1041.team_3.swing.Combobox();
        btnLocNgay = new pro1041.team_3.swing.ButtonCustom();
        txtTien1 = new pro1041.team_3.swing.TextField();
        txtTien2 = new pro1041.team_3.swing.TextField();
        btnLocTien = new pro1041.team_3.swing.ButtonCustom();
        jLabel1 = new javax.swing.JLabel();
        btnXuatPdf = new pro1041.team_3.swing.ButtonCustom();

        dlHoaDonChiTiet.setResizable(false);
        dlHoaDonChiTiet.setSize(new java.awt.Dimension(1095, 365));
        dlHoaDonChiTiet.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(1080, 345));
        jPanel3.setPreferredSize(new java.awt.Dimension(1080, 345));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHoaDonChiTiet.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbHoaDonChiTiet.getTableHeader().setReorderingAllowed(false);
        jspTbHoaDonChiTiet.setViewportView(tbHoaDonChiTiet);
        if (tbHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tbHoaDonChiTiet.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jPanel3.add(jspTbHoaDonChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1060, 310));

        dlHoaDonChiTiet.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 330));

        tpThoiGianKetThuc.setDisplayText(txtThoiGianKT);

        tpThoiGianBatDau.setDisplayText(txtThoiGianBD);

        dlChonNgayKetThuc.setTextRefernce(txtNgayKT);

        dlChonNgayBatDau.setTextRefernce(txtNgayBD);

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1105, 710));
        setPreferredSize(new java.awt.Dimension(1105, 710));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jspTbHoaDon.setBorder(new javax.swing.border.MatteBorder(null));

        tbHoaDon.setBorder(new javax.swing.border.MatteBorder(null));
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HĐ", "Mã KH", "SĐT KH", "Mã NV", "Ngày thanh toán", "HT thanh toán", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDon.getTableHeader().setReorderingAllowed(false);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jspTbHoaDon.setViewportView(tbHoaDon);
        if (tbHoaDon.getColumnModel().getColumnCount() > 0) {
            tbHoaDon.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbHoaDon.getColumnModel().getColumn(2).setPreferredWidth(20);
            tbHoaDon.getColumnModel().getColumn(4).setPreferredWidth(20);
            tbHoaDon.getColumnModel().getColumn(6).setPreferredWidth(10);
        }

        add(jspTbHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 1080, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray), "Chi tiết hóa đơn"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTongTien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTongTien.setLabelText("Tổng tiền");
        jPanel1.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 180, -1));

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaHoaDon.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaHoaDon.setLabelText("Mã hóa đơn");
        jPanel1.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 210, -1));

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaKhachHang.setLabelText("Mã khách hàng");
        jPanel1.add(txtMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 210, -1));

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTenKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenKhachHang.setLabelText("Tên khách hàng");
        jPanel1.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 180, -1));

        txtSdtKhachHang.setEditable(false);
        txtSdtKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtSdtKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtSdtKhachHang.setLabelText("SĐT khách hàng");
        jPanel1.add(txtSdtKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 210, -1));

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaNhanVien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaNhanVien.setLabelText("Mã nhân viên");
        jPanel1.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, -1));

        txtNgayThanhToan.setEditable(false);
        txtNgayThanhToan.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtNgayThanhToan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayThanhToan.setLabelText("Ngày thanh toán");
        jPanel1.add(txtNgayThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 180, -1));

        txtHinhThucThanhToan.setEditable(false);
        txtHinhThucThanhToan.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtHinhThucThanhToan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtHinhThucThanhToan.setLabelText("Hình thức thanh toán");
        jPanel1.add(txtHinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 180, -1));

        txtNganHang.setEditable(false);
        txtNganHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtNganHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNganHang.setLabelText("Ngân hàng");
        jPanel1.add(txtNganHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 180, -1));

        txtMaGiaoDich.setEditable(false);
        txtMaGiaoDich.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaGiaoDich.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaGiaoDich.setLabelText("Mã giao dịch");
        jPanel1.add(txtMaGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 180, -1));

        txtTenNhanVien.setEditable(false);
        txtTenNhanVien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTenNhanVien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenNhanVien.setLabelText("Tên nhân viên");
        jPanel1.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 180, -1));

        txtTienMat.setEditable(false);
        txtTienMat.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTienMat.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienMat.setLabelText("Tiền mặt");
        jPanel1.add(txtTienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 180, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 610, 240));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnXemChiTiet.setBackground(new java.awt.Color(1, 181, 204));
        btnXemChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnXemChiTiet.setText("Xem chi tiết");
        btnXemChiTiet.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });
        jPanel2.add(btnXemChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 116, -1));

        txtTimKiem.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTimKiem.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTimKiem.setLabelText("Tìm kiếm");
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        jPanel2.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        txtNgayBD.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtNgayBD.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayBD.setLabelText("Từ");
        txtNgayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBDActionPerformed(evt);
            }
        });
        jPanel2.add(txtNgayBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, -1));

        txtNgayKT.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtNgayKT.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayKT.setLabelText("Đến");
        jPanel2.add(txtNgayKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 140, -1));

        txtThoiGianKT.setEditable(false);
        txtThoiGianKT.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtThoiGianKT.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThoiGianKT.setLabelText("");
        txtThoiGianKT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtThoiGianKTMouseClicked(evt);
            }
        });
        jPanel2.add(txtThoiGianKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 140, -1));

        txtThoiGianBD.setEditable(false);
        txtThoiGianBD.setFont(new java.awt.Font("Nunito Light", 1, 13)); // NOI18N
        txtThoiGianBD.setLabelColor(new java.awt.Color(1, 132, 203));
        txtThoiGianBD.setLabelText("");
        txtThoiGianBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtThoiGianBDMouseClicked(evt);
            }
        });
        txtThoiGianBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThoiGianBDActionPerformed(evt);
            }
        });
        jPanel2.add(txtThoiGianBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 140, -1));

        cbbLocHttt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Tiền mặt", "Ngân hàng", "Kết hợp" }));
        cbbLocHttt.setSelectedIndex(-1);
        cbbLocHttt.setFocusLostColor(new java.awt.Color(3, 155, 216));
        cbbLocHttt.setFont(new java.awt.Font("Nunito Light", 1, 12)); // NOI18N
        cbbLocHttt.setLabeText("Hình thức thanh toán");
        cbbLocHttt.setLabelColor(new java.awt.Color(1, 132, 203));
        cbbLocHttt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocHtttActionPerformed(evt);
            }
        });
        jPanel2.add(cbbLocHttt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 140, 50));

        btnLocNgay.setBackground(new java.awt.Color(1, 181, 204));
        btnLocNgay.setForeground(new java.awt.Color(255, 255, 255));
        btnLocNgay.setText("Lọc ngày");
        btnLocNgay.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        btnLocNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocNgayActionPerformed(evt);
            }
        });
        jPanel2.add(btnLocNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 116, -1));

        txtTien1.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTien1.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTien1.setLabelText("Khoảng 2");
        txtTien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTien1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtTien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 130, -1));

        txtTien2.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTien2.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTien2.setLabelText("Khoảng 1");
        txtTien2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTien2ActionPerformed(evt);
            }
        });
        jPanel2.add(txtTien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, -1));

        btnLocTien.setBackground(new java.awt.Color(1, 181, 204));
        btnLocTien.setForeground(new java.awt.Color(255, 255, 255));
        btnLocTien.setText("Lọc tổng tiền");
        btnLocTien.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        btnLocTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocTienActionPerformed(evt);
            }
        });
        jPanel2.add(btnLocTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 116, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 20, -1));

        btnXuatPdf.setBackground(new java.awt.Color(1, 181, 204));
        btnXuatPdf.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatPdf.setText("Xuất PDF");
        btnXuatPdf.setFont(new java.awt.Font("Nunito", 1, 12)); // NOI18N
        btnXuatPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPdfActionPerformed(evt);
            }
        });
        jPanel2.add(btnXuatPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 116, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 460, 230));
    }// </editor-fold>//GEN-END:initComponents

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        //TABLE Hóa đơn Click
        int row = tbHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        lstHoaDon = hoaDonService.getAllResponse();
        HoaDonDto hoaDon = lstHoaDon.get(row);
        txtMaHoaDon.setText(hoaDon.getMaHoaDon());
        txtMaKhachHang.setText(hoaDon.getMaKhachHang() == null ? "-" : hoaDon.getMaKhachHang());
        txtTenKhachHang.setText(hoaDon.getTenKhachHang() == null ? "-" : hoaDon.getTenKhachHang());
        txtSdtKhachHang.setText(hoaDon.getSdtKhachHang() == null ? "-" : hoaDon.getSdtKhachHang());
        txtMaNhanVien.setText(hoaDon.getMaNhanVien() == null ? "" : hoaDon.getMaNhanVien());
        txtTenNhanVien.setText(hoaDon.getTenNhanVien() == null ? "" : hoaDon.getTenNhanVien());
        txtNgayThanhToan.setText(sdfNgayThanhToan.format(hoaDon.getNgayThanhToan()));
        Integer httt = hoaDon.getHinhThucThanhToan();
        txtHinhThucThanhToan.setText(httt == 0 ? "Tiền mặt" : httt == 1 ? "Ngân hàng" : httt == 2 ? "Kết hợp" : "");
        txtTienMat.setText(hoaDon.getTienMat() == null ? "-" : moneyFormat.format(hoaDon.getTienMat()) + "VNĐ");
        txtNganHang.setText(hoaDon.getNganHang() == null ? "-" : moneyFormat.format(hoaDon.getNganHang()) + "VNĐ");
        txtMaGiaoDich.setText(hoaDon.getMaGiaoDich() == null ? "-" : hoaDon.getMaGiaoDich());
        txtTongTien.setText(moneyFormat.format(hoaDon.getTongTien()) + "VNĐ");
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        // BTN Xem chi tiết
        int row = tbHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một hóa đơn để xem chi tiết");
            return;
        }
        HoaDonDto hoaDonDto = lstHoaDon.get(row);
        List<HoaDonChiTietDto> lstHdct = hoaDonChiTietService.getAllByIdHoaDon(hoaDonDto.getId());
        DefaultTableModel model = (DefaultTableModel) tbHoaDonChiTiet.getModel();
        model.setRowCount(0);
        int count = 1;
        for (HoaDonChiTietDto x : lstHdct) {
            model.addRow(new Object[]{
                count,
                x.getMa(),
                x.getTen(),
                x.getImei(),
                x.getHang(),
                x.getMauSac(),
                x.getBoNho(),
                x.getTinhTrang(),
                moneyFormat.format(x.getDonGia()) + "VNĐ",
                moneyFormat.format(x.getGiaBan()) + "VNĐ"
            });
            count += 1;
        }
        dlHoaDonChiTiet.setVisible(true);
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void txtThoiGianKTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtThoiGianKTMouseClicked
        // TXT Thời gian kết thúc đầu click
        tpThoiGianKetThuc.showPopup(this, (getWidth() - tpThoiGianKetThuc.getPreferredSize().width) / 2, (getHeight() - tpThoiGianKetThuc.getPreferredSize().height) / 2);
    }//GEN-LAST:event_txtThoiGianKTMouseClicked

    private void txtThoiGianBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtThoiGianBDMouseClicked
        // TXT Thời gian bắt đầu đầu click
        tpThoiGianBatDau.showPopup(this, (getWidth() - tpThoiGianBatDau.getPreferredSize().width) / 2, (getHeight() - tpThoiGianBatDau.getPreferredSize().height) / 2);
    }//GEN-LAST:event_txtThoiGianBDMouseClicked

    private void txtNgayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDActionPerformed

    private void cbbLocHtttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocHtttActionPerformed
        // CBB lọc hãng khi chọn Điện thoại vào KM
        int choose = cbbLocHttt.getSelectedIndex();
        if (choose == 0) {
            lstHoaDon = hoaDonService.getAllResponse();
            this.loadTableHoaDon(lstHoaDon);
        } else if (choose == 1) {
            lstHoaDon = hoaDonService.locHinhThucThanhToan(0);
            this.loadTableHoaDon(lstHoaDon);
        } else if (choose == 2) {
            lstHoaDon = hoaDonService.locHinhThucThanhToan(1);
            this.loadTableHoaDon(lstHoaDon);
        } else if (choose == 3) {
            lstHoaDon = hoaDonService.locHinhThucThanhToan(2);
            this.loadTableHoaDon(lstHoaDon);
        }
    }//GEN-LAST:event_cbbLocHtttActionPerformed

    private void txtThoiGianBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThoiGianBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThoiGianBDActionPerformed

    private void btnLocNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocNgayActionPerformed
        // TODO add your handling code here:
        String ngayBDStr = txtNgayBD.getText().trim();
        String ngayKTStr = txtNgayKT.getText().trim();
        String thoiGianBatDauStr = txtThoiGianBD.getText();
        String thoiGianKetThucStr = txtThoiGianKT.getText();
        Date ngayBatDau = null;
        Date ngayKetThuc = null;
        Date dateNow = new Date();
//        LocalDateTime time = LocalDateTime.now();
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm aa", Locale.US);
        SimpleDateFormat sdfCongChuoi = new SimpleDateFormat("kk:mm");
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy kk:mm");
        if (ngayBDStr.length() == 0 || ngayKTStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn nhập đầy đủ ngày bắt đầu và kết thúc");
            return;
        }
        try {
            Date thoiGianBatDau = sdfTime.parse(thoiGianBatDauStr);
            Date thoiGianKetThuc = sdfTime.parse(thoiGianKetThucStr);
            String ngayBatDauInput = ngayBDStr + " " + sdfCongChuoi.format(thoiGianBatDau);
            String ngayKetThucInput = ngayKTStr + " " + sdfCongChuoi.format(thoiGianKetThuc);
            ngayBatDau = sdfInput.parse(ngayBatDauInput);
            ngayKetThuc = sdfInput.parse(ngayKetThucInput);
            if (ngayBatDau.getTime() > dateNow.getTime() || ngayKetThuc.getTime() > dateNow.getTime()) {
                JOptionPane.showMessageDialog(this, "Ngày phải nhỏ hơn ngày hiện tại");
                return;
            }
            System.out.println(ngayBatDau.toLocaleString());
            System.out.println(ngayKetThuc.toLocaleString());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng ngày");
            ex.printStackTrace();
        }
        lstHoaDon = hoaDonService.locHoaDonTheoNgay(ngayBatDau, ngayKetThuc);
        if (lstHoaDon.isEmpty()) {
            List<HoaDonDto> lst = new ArrayList<>();
            loadTableHoaDon(lst);
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
            return;
        }
        loadTableHoaDon(lstHoaDon);

    }//GEN-LAST:event_btnLocNgayActionPerformed

    private void btnLocTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocTienActionPerformed
        // TODO add your handling code here:
        String tien1 = txtTien1.getText();
        String tien2 = txtTien2.getText();
        if (tien1.trim().length() == 0 || tien2.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn nhâp đầy đủ 2 khoảng tổng tiền");
            return;
        }
        long gia1 = 0;
        long gia2 = 0;
        try {
            gia2 = Long.parseLong(tien2);
            gia1 = Long.parseLong(tien1);
            if (gia1 < 0 || gia2 < 0) {
                JOptionPane.showMessageDialog(this, "Tông tiền phải > 0");
                return;
            }
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Mức giá phai la so nguyen duong");
            return;
        }
        lstHoaDon = hoaDonService.locHoaDonTheoTongTien(BigDecimal.valueOf(gia1), BigDecimal.valueOf(gia2));
        if (lstHoaDon.isEmpty()) {
            List<HoaDonDto> lst = new ArrayList<>();
            loadTableHoaDon(lst);
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
            return;
        }
        loadTableHoaDon(lstHoaDon);
    }//GEN-LAST:event_btnLocTienActionPerformed

    private void txtTien2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTien2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTien2ActionPerformed

    private void txtTien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTien1ActionPerformed

    private void btnXuatPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPdfActionPerformed
        // BTN xuất PDF
        int row = tbHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một hóa đơn để xuất PDF");
            return;
        }
        JnaFileChooser jfc = new JnaFileChooser();
        jfc.setMode(JnaFileChooser.Mode.Directories);
        if (jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
            String path = jfc.getSelectedFile().getAbsolutePath();
            HoaDonDto hoaDon = lstHoaDon.get(row);
            if (hoaDonService.exportPdf(path, hoaDon.getId())) {
                Notification panel = new Notification((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Xuất thành công");
                panel.showNotification();
            } else {
                Notification panel = new Notification((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Lỗi hệ thống. Xuất thất bại");
                panel.showNotification();
            }
        }

    }//GEN-LAST:event_btnXuatPdfActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        //TXT Tìm kiếm type
        String keyword = txtTimKiem.getText().trim();
        if (keyword.isEmpty()) {
            return;
        }
        List<HoaDonDto> lstHoaDon = hoaDonService.findHoaDon(keyword);
        if (lstHoaDon.isEmpty() || lstHoaDon == null) {
            return;
        }
        loadTableHoaDon(lstHoaDon);
    }//GEN-LAST:event_txtTimKiemCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnLocNgay;
    private pro1041.team_3.swing.ButtonCustom btnLocTien;
    private pro1041.team_3.swing.ButtonCustom btnXemChiTiet;
    private pro1041.team_3.swing.ButtonCustom btnXuatPdf;
    private pro1041.team_3.swing.Combobox cbbLocHttt;
    private pro1041.team_3.swing.DateChooser dlChonNgayBatDau;
    private pro1041.team_3.swing.DateChooser dlChonNgayKetThuc;
    private javax.swing.JDialog dlHoaDonChiTiet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jspTbHoaDon;
    private javax.swing.JScrollPane jspTbHoaDonChiTiet;
    private pro1041.team_3.swing.config.Table tbHoaDon;
    private pro1041.team_3.swing.config.Table tbHoaDonChiTiet;
    private pro1041.team_3.swing.TimePicker tpThoiGianBatDau;
    private pro1041.team_3.swing.TimePicker tpThoiGianKetThuc;
    private pro1041.team_3.swing.TextField txtHinhThucThanhToan;
    private pro1041.team_3.swing.TextField txtMaGiaoDich;
    private pro1041.team_3.swing.TextField txtMaHoaDon;
    private pro1041.team_3.swing.TextField txtMaKhachHang;
    private pro1041.team_3.swing.TextField txtMaNhanVien;
    private pro1041.team_3.swing.TextField txtNganHang;
    private pro1041.team_3.swing.TextField txtNgayBD;
    private pro1041.team_3.swing.TextField txtNgayKT;
    private pro1041.team_3.swing.TextField txtNgayThanhToan;
    private pro1041.team_3.swing.TextField txtSdtKhachHang;
    private pro1041.team_3.swing.TextField txtTenKhachHang;
    private pro1041.team_3.swing.TextField txtTenNhanVien;
    private pro1041.team_3.swing.TextField txtThoiGianBD;
    private pro1041.team_3.swing.TextField txtThoiGianKT;
    private pro1041.team_3.swing.TextField txtTien1;
    private pro1041.team_3.swing.TextField txtTien2;
    private pro1041.team_3.swing.TextField txtTienMat;
    private pro1041.team_3.swing.TextField txtTimKiem;
    private pro1041.team_3.swing.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
