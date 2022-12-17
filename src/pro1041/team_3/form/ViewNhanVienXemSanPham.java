package pro1041.team_3.form;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.domainModel.ChiTietDienThoai;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.domainModel.DienThoaiKhuyenMai;
import pro1041.team_3.domainModel.Hang;
import pro1041.team_3.domainModel.KhuyenMai;
import pro1041.team_3.domainModel.MauSac;
import pro1041.team_3.dto.ChiTietDienThoaiResponse;
import pro1041.team_3.dto.DienThoaiDto;
import pro1041.team_3.dto.DienThoaiKhuyenMaiDto;
import pro1041.team_3.dto.HangDto;
import pro1041.team_3.dto.MauSacDto;
import pro1041.team_3.service.impl.ChiTietDienThoaiImpl;
import pro1041.team_3.service.impl.DienThoaiKhuyenMaiServiceImpl;
import pro1041.team_3.service.impl.DienThoaiServiceImpl;
import pro1041.team_3.service.impl.HangServiceImpl;
import pro1041.team_3.service.impl.KhuyenMaiServiceImpl;
import pro1041.team_3.service.impl.MauSacServiceImpl;
import pro1041.team_3.swing.Notification;
import pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author trangdttph27721
 */
public class ViewNhanVienXemSanPham extends javax.swing.JPanel {

    private DienThoaiServiceImpl dienThoaiImpl;
    private HangServiceImpl hangImpl;
    private MauSacServiceImpl mauSacImpl;
    private ChiTietDienThoaiImpl chiTietDTImpl;
    private DienThoaiKhuyenMaiServiceImpl dienThoaiKhuyenMaiServiceImpl;
    private KhuyenMaiServiceImpl khuyenMaiServiceImpl;
    private List<ChiTietDienThoaiResponse> list = new ArrayList<>();
    private List<DienThoaiDto> listDT = new ArrayList<>();
    private List<HangDto> listHang = new ArrayList<>();
    private List<MauSacDto> listMS = new ArrayList<>();
    private String _hinhAnh = null;

    public ViewNhanVienXemSanPham() {
        initComponents();
        //Edit scroll table
        tblChiTietDienThoai.fixTable(jspTblChiTietDienThoai);
        this.txtMa.setEditable(false);
        this.dienThoaiImpl = new DienThoaiServiceImpl();
        this.hangImpl = new HangServiceImpl();
        this.mauSacImpl = new MauSacServiceImpl();
        this.chiTietDTImpl = new ChiTietDienThoaiImpl();
        this.dienThoaiKhuyenMaiServiceImpl = new DienThoaiKhuyenMaiServiceImpl();
        this.khuyenMaiServiceImpl = new KhuyenMaiServiceImpl();
        this.list = this.chiTietDTImpl.getAllResponse();
        this.tinhTrangCuPanel.setVisible(false);
        this.loadTable(list);

    }

    public void loadTable(List<ChiTietDienThoaiResponse> lst) {
        DefaultTableModel model = (DefaultTableModel) this.tblChiTietDienThoai.getModel();
        model.setRowCount(0);
        int index = 1;
        for (ChiTietDienThoaiResponse x : lst) {
            model.addRow(x.toDataRow(index));
            index++;
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rdoMoi = new javax.swing.JRadioButton();
        rdoCu = new javax.swing.JRadioButton();
        rdoLoi = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoHet = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtImei = new javax.swing.JTextField();
        txtRom = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtBaoHanh = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtRam = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tinhTrangCuPanel = new javax.swing.JPanel();
        cbbTinhTrangCu = new pro1041.team_3.swing.ComboBoxSuggestion();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbTrangThai = new pro1041.team_3.swing.Combobox();
        txtMauSac = new javax.swing.JTextField();
        txtDiennThoai = new javax.swing.JTextField();
        txtHang = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        btnTaoQr = new pro1041.team_3.swing.ButtonCustom();
        jspTblChiTietDienThoai = new javax.swing.JScrollPane();
        tblChiTietDienThoai = new pro1041.team_3.swing.config.Table();

        setBackground(new java.awt.Color(250, 255, 255));
        setPreferredSize(new java.awt.Dimension(1294, 709));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(252, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1295, 709));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel4.setText("Lọc trạng thái:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        txtTim.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTim.setToolTipText("");
        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        txtTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCaretUpdate(evt);
            }
        });
        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        jPanel1.add(txtTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 270, 30));

        jLabel21.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel21.setText("Màu sắc");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, -1, -1));

        jLabel17.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel17.setText("Trạng thái");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, -1, -1));

        buttonGroup1.add(rdoMoi);
        rdoMoi.setFont(new java.awt.Font("Nunito", 1, 13)); // NOI18N
        rdoMoi.setText("Mới");
        rdoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMoiActionPerformed(evt);
            }
        });
        jPanel1.add(rdoMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, -1, -1));

        buttonGroup1.add(rdoCu);
        rdoCu.setFont(new java.awt.Font("Nunito", 1, 13)); // NOI18N
        rdoCu.setText("Cũ");
        rdoCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCuActionPerformed(evt);
            }
        });
        jPanel1.add(rdoCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, -1, -1));

        buttonGroup2.add(rdoLoi);
        rdoLoi.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoLoi.setText("Sản phẩm lỗi");
        jPanel1.add(rdoLoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 180, -1, -1));

        jLabel19.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel19.setText("Tình trạng");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, -1, -1));

        buttonGroup2.add(rdoCon);
        rdoCon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoCon.setText("Đang bán");
        rdoCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoConActionPerformed(evt);
            }
        });
        jPanel1.add(rdoCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, -1, -1));

        buttonGroup2.add(rdoHet);
        rdoHet.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        rdoHet.setText("Đã bán");
        rdoHet.setEnabled(false);
        jPanel1.add(rdoHet, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 180, -1, -1));

        jLabel13.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel13.setText("Hãng");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, 20));

        jLabel18.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel18.setText("Điện thoại");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("VND");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel9.setText("Mã");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel10.setText("Giá bán");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel15.setText("RAM");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        txtGiaBan.setEditable(false);
        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 220, 30));

        txtImei.setEditable(false);
        txtImei.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtImei.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtImei, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 220, 30));

        txtRom.setEditable(false);
        txtRom.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 220, 30));

        jLabel11.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel11.setText("IMEI");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 40, -1));

        jLabel12.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel12.setText("ROM");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        txtBaoHanh.setEditable(false);
        txtBaoHanh.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtBaoHanh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtBaoHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 130, 30));

        jLabel14.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel14.setText("Bảo hành");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        txtRam.setEditable(false);
        txtRam.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtRam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 220, 30));

        jLabel16.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel16.setText("Mô tả");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        txtMoTa.setEditable(false);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMoTa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtMoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 220, 30));

        jLabel5.setFont(new java.awt.Font("Nunito", 1, 13)); // NOI18N
        jLabel5.setText("Tìm kiếm");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        tinhTrangCuPanel.setBackground(new java.awt.Color(252, 255, 255));
        tinhTrangCuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbbTinhTrangCu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" }));
        cbbTinhTrangCu.setEnabled(false);
        tinhTrangCuPanel.add(cbbTinhTrangCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("%");
        tinhTrangCuPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jPanel1.add(tinhTrangCuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 170, 60));

        jLabel2.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        jLabel2.setText("Tháng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, -1, -1));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", " Đang bán", " Đã bán", " Sản phẩm lỗi" }));
        cbbTrangThai.setLabeText("");
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 220, 40));

        txtMauSac.setEditable(false);
        txtMauSac.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMauSac.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 190, 30));

        txtDiennThoai.setEditable(false);
        txtDiennThoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtDiennThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtDiennThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 190, 30));

        txtHang.setEditable(false);
        txtHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 190, 30));

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(90, 183, 210)));
        jPanel1.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 220, 30));

        btnTaoQr.setBackground(new java.awt.Color(1, 181, 204));
        btnTaoQr.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pro1041/team_3/icon/scanQr.png"))); // NOI18N
        btnTaoQr.setText("Tải QR");
        btnTaoQr.setFont(new java.awt.Font("Nunito", 1, 14)); // NOI18N
        btnTaoQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoQrActionPerformed(evt);
            }
        });
        jPanel1.add(btnTaoQr, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 260, 130, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1120, 330));

        tblChiTietDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT ", "Mã", "Điện thoại", "Hãng", "Giá bán", "Màu sắc", "Imei", "Ram", "Rom", "Tình trạng", "Trạng thái", "Mô tả", "Bảo hành"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietDienThoai.setFocusTraversalPolicyProvider(true);
        tblChiTietDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblChiTietDienThoai.setInheritsPopupMenu(true);
        tblChiTietDienThoai.getTableHeader().setReorderingAllowed(false);
        tblChiTietDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietDienThoaiMouseClicked(evt);
            }
        });
        jspTblChiTietDienThoai.setViewportView(tblChiTietDienThoai);
        if (tblChiTietDienThoai.getColumnModel().getColumnCount() > 0) {
            tblChiTietDienThoai.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblChiTietDienThoai.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblChiTietDienThoai.getColumnModel().getColumn(2).setPreferredWidth(65);
            tblChiTietDienThoai.getColumnModel().getColumn(3).setPreferredWidth(45);
            tblChiTietDienThoai.getColumnModel().getColumn(4).setPreferredWidth(55);
            tblChiTietDienThoai.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblChiTietDienThoai.getColumnModel().getColumn(6).setPreferredWidth(70);
            tblChiTietDienThoai.getColumnModel().getColumn(7).setPreferredWidth(10);
            tblChiTietDienThoai.getColumnModel().getColumn(8).setPreferredWidth(10);
            tblChiTietDienThoai.getColumnModel().getColumn(9).setPreferredWidth(35);
            tblChiTietDienThoai.getColumnModel().getColumn(10).setPreferredWidth(35);
            tblChiTietDienThoai.getColumnModel().getColumn(12).setPreferredWidth(30);
        }

        add(jspTblChiTietDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 1120, 410));
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        try {
            String text = this.txtTim.getText().trim();
            if (text.length() == 0) {
                list = this.chiTietDTImpl.getAllResponse();
                this.loadTable(list);
            } else {
                list = this.chiTietDTImpl.findBy(text);
                this.loadTable(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }//GEN-LAST:event_txtTimCaretUpdate

    private void rdoConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoConActionPerformed

    private void tblChiTietDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietDienThoaiMouseClicked
        // TODO add your handling code here:
        int row = this.tblChiTietDienThoai.getSelectedRow();

//        list = this.chiTietDTImpl.getAllResponse();
        ChiTietDienThoaiResponse ctdtr = this.list.get(row);
        this.txtRom.setText(ctdtr.getBoNho() + "");
        this.txtGiaBan.setText(ctdtr.getDonGia() + "");
        this.txtImei.setText(ctdtr.getImei());
        this.txtMa.setText(ctdtr.getMaDienThoai());
        this.txtMoTa.setText(ctdtr.getMoTa() == null ? "_" : ctdtr.getMoTa());
        this.txtRam.setText(ctdtr.getRam() + "");
        this.txtBaoHanh.setText(ctdtr.getThoiGianBaoHanh() + "");
        txtDiennThoai.setText(ctdtr.getDienThoai());
        txtHang.setText(ctdtr.getHang());
        txtMauSac.setText(ctdtr.getMauSac());

        if (ctdtr.getTrangThai() == 0) {
            this.rdoCon.setSelected(true);
        } else if (ctdtr.getTrangThai() == 1) {
            this.rdoHet.setSelected(true);
        } else {
            this.rdoLoi.setSelected(true);
        }

        if (ctdtr.getTinhTrang() == 100) {
            this.rdoMoi.setSelected(true);
            this.tinhTrangCuPanel.setVisible(false);
        } else {
            this.rdoCu.setSelected(true);
            this.tinhTrangCuPanel.setVisible(true);
            this.cbbTinhTrangCu.setSelectedItem(ctdtr.getTinhTrang() + "");
        }

    }//GEN-LAST:event_tblChiTietDienThoaiMouseClicked

    private void rdoCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCuActionPerformed
        // TODO add your handling code here:
        if (this.rdoCu.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(true);
        } else if (this.rdoMoi.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(false);
        }
    }//GEN-LAST:event_rdoCuActionPerformed

    private void rdoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMoiActionPerformed
        // TODO add your handling code here:
        if (this.rdoCu.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(true);
        } else if (this.rdoMoi.isSelected() == true) {
            this.tinhTrangCuPanel.setVisible(false);
        }
    }//GEN-LAST:event_rdoMoiActionPerformed

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // CBB lọc danh sách sản phẩm theo trạng thái
        if (this.cbbTrangThai.getSelectedIndex() == 0) {
            this.list = this.chiTietDTImpl.getAllResponse();
            loadTable(list);
        } else if (this.cbbTrangThai.getSelectedIndex() == 1) {
            this.list = this.chiTietDTImpl.getAllTrangThai(0);
            loadTable(list);
        } else if (this.cbbTrangThai.getSelectedIndex() == 2) {
            this.list = this.chiTietDTImpl.getAllTrangThai(1);
            loadTable(list);
        } else if (this.cbbTrangThai.getSelectedIndex() == 3) {
            this.list = this.chiTietDTImpl.getAllTrangThai(2);
            loadTable(list);
        }
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void btnTaoQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoQrActionPerformed
        //BTN Tạo QR
        int row = tblChiTietDienThoai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn một điện thoại để tải mã QR");
            return;
        }
        JnaFileChooser jfc = new JnaFileChooser();
        jfc.setMode(JnaFileChooser.Mode.Directories);
        if (!jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
            return;
        }
        ChiTietDienThoaiResponse ctdt = list.get(row);
        String mess = chiTietDTImpl.exportQr(jfc.getSelectedFile().getAbsolutePath(), ctdt.getId());
        if (mess.equals("Tải thành công")) {
            Notification panel = new Notification((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this),
                Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Tải thành công");
            panel.showNotification();
        } else {
            JOptionPane.showMessageDialog(this, mess);
        }
    }//GEN-LAST:event_btnTaoQrActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pro1041.team_3.swing.ButtonCustom btnTaoQr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private pro1041.team_3.swing.ComboBoxSuggestion cbbTinhTrangCu;
    private pro1041.team_3.swing.Combobox cbbTrangThai;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jspTblChiTietDienThoai;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoCu;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JRadioButton rdoLoi;
    private javax.swing.JRadioButton rdoMoi;
    private pro1041.team_3.swing.config.Table tblChiTietDienThoai;
    private javax.swing.JPanel tinhTrangCuPanel;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtDiennThoai;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtHang;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRom;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
