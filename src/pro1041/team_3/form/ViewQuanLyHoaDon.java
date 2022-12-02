package pro1041.team_3.form;

import pro1041.team_3.chart.ModelChartLine;
import pro1041.team_3.chart.ModelChartPie;
import pro1041.team_3.model.ModelStaff;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.dto.HoaDonChiTietDto;
import pro1041.team_3.dto.HoaDonDto;
import pro1041.team_3.service.HoaDonChiTietService;
import pro1041.team_3.service.HoaDonService;
import pro1041.team_3.service.impl.HoaDonChiTietServiceImpl;
import pro1041.team_3.service.impl.HoaDonServiceImpl;

public class ViewQuanLyHoaDon extends javax.swing.JPanel {

    private HoaDonService hoaDonService;
    private HoaDonChiTietService hoaDonChiTietService;
    private List<HoaDonDto> lstHoaDon;
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
        loadTableHoaDon();
    }

    private void loadTableHoaDon() {
        lstHoaDon = hoaDonService.getAllResponse();
        if (lstHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
        model.setRowCount(0);
        int count = 1;
        for (HoaDonDto x : lstHoaDon) {
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
        jspTbHoaDon = new javax.swing.JScrollPane();
        tbHoaDon = new pro1041.team_3.swing.config.Table();
        jPanel1 = new javax.swing.JPanel();
        txtTongTien = new pro1041.team_3.swing.TextField();
        txtMaHoaDon = new pro1041.team_3.swing.TextField();
        txtMaKhachHang = new pro1041.team_3.swing.TextField();
        txtTenKhachHang = new pro1041.team_3.swing.TextField();
        txtSdtKhachHang = new pro1041.team_3.swing.TextField();
        txtMaNhanVien = new pro1041.team_3.swing.TextField();
        txtTenNhanVien = new pro1041.team_3.swing.TextField();
        txtNgayThanhToan = new pro1041.team_3.swing.TextField();
        txtHinhThucThanhToan = new pro1041.team_3.swing.TextField();
        txtTienMat = new pro1041.team_3.swing.TextField();
        txtNganHang = new pro1041.team_3.swing.TextField();
        txtMaGiaoDich = new pro1041.team_3.swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        btnXemChiTiet = new pro1041.team_3.swing.ButtonCustom();
        textField1 = new pro1041.team_3.swing.TextField();

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
        jPanel1.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 230, -1));

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaHoaDon.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaHoaDon.setLabelText("Mã hóa đơn");
        jPanel1.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, -1));

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaKhachHang.setLabelText("Mã khách hàng");
        jPanel1.add(txtMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 230, -1));

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTenKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenKhachHang.setLabelText("Tên khách hàng");
        jPanel1.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 230, -1));

        txtSdtKhachHang.setEditable(false);
        txtSdtKhachHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtSdtKhachHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtSdtKhachHang.setLabelText("SĐT khách hàng");
        jPanel1.add(txtSdtKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 230, -1));

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaNhanVien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaNhanVien.setLabelText("Mã nhân viên");
        jPanel1.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 230, -1));

        txtTenNhanVien.setEditable(false);
        txtTenNhanVien.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTenNhanVien.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTenNhanVien.setLabelText("Tên nhân viên");
        jPanel1.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 230, -1));

        txtNgayThanhToan.setEditable(false);
        txtNgayThanhToan.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtNgayThanhToan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNgayThanhToan.setLabelText("Ngày thanh toán");
        jPanel1.add(txtNgayThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 230, -1));

        txtHinhThucThanhToan.setEditable(false);
        txtHinhThucThanhToan.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtHinhThucThanhToan.setLabelColor(new java.awt.Color(1, 132, 203));
        txtHinhThucThanhToan.setLabelText("Hình thức thanh toán");
        jPanel1.add(txtHinhThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 230, -1));

        txtTienMat.setEditable(false);
        txtTienMat.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtTienMat.setLabelColor(new java.awt.Color(1, 132, 203));
        txtTienMat.setLabelText("Tiền mặt");
        jPanel1.add(txtTienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 230, -1));

        txtNganHang.setEditable(false);
        txtNganHang.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtNganHang.setLabelColor(new java.awt.Color(1, 132, 203));
        txtNganHang.setLabelText("Ngân hàng");
        jPanel1.add(txtNganHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 230, -1));

        txtMaGiaoDich.setEditable(false);
        txtMaGiaoDich.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        txtMaGiaoDich.setLabelColor(new java.awt.Color(1, 132, 203));
        txtMaGiaoDich.setLabelText("Mã giao dịch");
        jPanel1.add(txtMaGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 230, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 760, 240));

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
        jPanel2.add(btnXemChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 116, -1));

        textField1.setText("textField1");
        textField1.setFont(new java.awt.Font("Nunito SemiBold", 0, 12)); // NOI18N
        textField1.setLabelColor(new java.awt.Color(1, 132, 203));
        jPanel2.add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 310, 230));
    }// </editor-fold>//GEN-END:initComponents

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        //TABLE Hóa đơn Click
        int row = tbHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnXemChiTiet;
    private javax.swing.JDialog dlHoaDonChiTiet;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jspTbHoaDon;
    private javax.swing.JScrollPane jspTbHoaDonChiTiet;
    private pro1041.team_3.swing.config.Table tbHoaDon;
    private pro1041.team_3.swing.config.Table tbHoaDonChiTiet;
    private pro1041.team_3.swing.TextField textField1;
    private pro1041.team_3.swing.TextField txtHinhThucThanhToan;
    private pro1041.team_3.swing.TextField txtMaGiaoDich;
    private pro1041.team_3.swing.TextField txtMaHoaDon;
    private pro1041.team_3.swing.TextField txtMaKhachHang;
    private pro1041.team_3.swing.TextField txtMaNhanVien;
    private pro1041.team_3.swing.TextField txtNganHang;
    private pro1041.team_3.swing.TextField txtNgayThanhToan;
    private pro1041.team_3.swing.TextField txtSdtKhachHang;
    private pro1041.team_3.swing.TextField txtTenKhachHang;
    private pro1041.team_3.swing.TextField txtTenNhanVien;
    private pro1041.team_3.swing.TextField txtTienMat;
    private pro1041.team_3.swing.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
